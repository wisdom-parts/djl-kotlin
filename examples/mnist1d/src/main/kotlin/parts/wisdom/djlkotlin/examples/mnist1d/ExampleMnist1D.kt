package parts.wisdom.djlkotlin.examples.mnist1d

import ai.djl.Application
import ai.djl.Model
import ai.djl.basicdataset.cv.classification.Mnist
import ai.djl.basicmodelzoo.basic.Mlp
import ai.djl.ndarray.types.Shape
import ai.djl.nn.Activation
import ai.djl.nn.Blocks
import ai.djl.nn.SequentialBlock
import ai.djl.nn.core.Linear
import ai.djl.training.DefaultTrainingConfig
import ai.djl.training.EasyTrain
import ai.djl.training.evaluator.Accuracy
import ai.djl.training.listener.TrainingListener
import ai.djl.training.loss.Loss
import ai.djl.training.util.ProgressBar


private const val INPUT_SIZE = 28*28
private const val OUTPUT_SIZE = 10
private const val BATCH_SIZE = 32
private const val EPOCHS = 2

fun main() {
    val application = Application.CV.IMAGE_CLASSIFICATION

    val block = SequentialBlock()
    block.add(Blocks.batchFlattenBlock(INPUT_SIZE.toLong()));
    block.add(Linear.builder().setUnits(128).build());
    block.add(Activation::relu);
    block.add(Linear.builder().setUnits(64).build());
    block.add(Activation::relu);
    block.add(Linear.builder().setUnits(OUTPUT_SIZE.toLong()).build());

    val mnist = Mnist.builder().setSampling(BATCH_SIZE, true).build()
    mnist.prepare(ProgressBar())

    val model = Model.newInstance("mlp")
    model.block = Mlp(INPUT_SIZE, OUTPUT_SIZE, intArrayOf(100))

    val config =
        DefaultTrainingConfig(Loss.softmaxCrossEntropyLoss())
            .addEvaluator(Accuracy())
            .addTrainingListeners(*TrainingListener.Defaults.logging())

    val trainer = model.newTrainer(config)
    trainer.initialize(Shape(1L, INPUT_SIZE.toLong()))

    EasyTrain.fit(trainer, EPOCHS, mnist, null)
}
