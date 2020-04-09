package parts.wisdom.djlkot.example.mnist1d

import ai.djl.Model
import ai.djl.basicdataset.Mnist
import ai.djl.basicmodelzoo.basic.Mlp
import ai.djl.metric.Metrics
import ai.djl.ndarray.types.Shape
import ai.djl.training.DefaultTrainingConfig
import ai.djl.training.Trainer
import ai.djl.training.dataset.Dataset
import ai.djl.training.dataset.RandomAccessDataset
import ai.djl.training.evaluator.Accuracy
import ai.djl.training.listener.TrainingListener
import ai.djl.training.loss.Loss
import java.nio.file.Paths

private const val MNIST_NUM_OUTPUTS = 10
private val MNIST_HIDDEN_SIZES = intArrayOf(128, 64)
private const val MNIST_EPOCHS = 10

private val modelName = run {
    val hiddenSizes = MNIST_HIDDEN_SIZES.joinToString(separator = "-")
    "mnist1d epochs-$MNIST_EPOCHS HIDDEN-$hiddenSizes"
}

fun main() {
    val trainingSet = prepareMnist(Dataset.Usage.TRAIN)
    val validationSet = prepareMnist(Dataset.Usage.TEST)
    val model = createModel()
    val trainer = createTrainer(model)

    trainModel(trainer, trainingSet, validationSet)

    model.save(Paths.get("models"), modelName)
}

private fun trainModel(
    trainer: Trainer,
    trainingSet: RandomAccessDataset,
    validationSet: RandomAccessDataset
) {
    (1..MNIST_EPOCHS).forEach {
        for (batch in trainer.iterateDataset(trainingSet)) batch.use {
            trainer.trainBatch(batch)
            trainer.step()
        }
        for (batch in trainer.iterateDataset(validationSet)) batch.use {
            trainer.validateBatch(batch)
        }
        trainer.endEpoch()
    }
    trainer.model.setProperty("Epoch", MNIST_EPOCHS.toString())
}

private fun createModel(): Model =
    Model.newInstance().apply {
        block = Mlp(
            Mnist.IMAGE_HEIGHT * Mnist.IMAGE_WIDTH,
            MNIST_NUM_OUTPUTS,
            MNIST_HIDDEN_SIZES
        )
    }

private fun createTrainer(model: Model): Trainer {
    val trainingConfig = DefaultTrainingConfig(Loss.softmaxCrossEntropyLoss())
        .addEvaluator(Accuracy())
        .addTrainingListeners(*TrainingListener.Defaults.logging())

    return model.newTrainer(trainingConfig).apply {
        metrics = Metrics()
        initialize(Shape(1, (Mnist.IMAGE_HEIGHT * Mnist.IMAGE_WIDTH).toLong()))
    }
}

private const val MNIST_BATCH_SIZE = 32

private fun prepareMnist(usage: Dataset.Usage): RandomAccessDataset =
    Mnist.builder()
        .optUsage(usage)
        .setSampling(MNIST_BATCH_SIZE, true)
        .build()
        .apply { prepare() }