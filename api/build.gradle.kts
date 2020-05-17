plugins {
    kotlin("jvm")
}

dependencies {
    val djlVersion: String by project
    api("ai.djl:api:$djlVersion")

    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    val djlEngine: String by project
    when (djlEngine.toLowerCase()) {
        "tensorflow" -> {
            implementation("ai.djl.tensorflow:tensorflow-api:$djlVersion")
            implementation("ai.djl.tensorflow:tensorflow-engine:$djlVersion")
            implementation("ai.djl.tensorflow:tensorflow-model-zoo:$djlVersion")

            implementation("ai.djl.tensorflow:tensorflow-native-auto:2.1.0-a-SNAPSHOT")
        }
        "mxnet" -> {
            runtimeOnly("ai.djl.mxnet:mxnet-engine:$djlVersion")
            runtimeOnly("ai.djl.mxnet:mxnet-native-auto:1.6.0")
        }
        "pytorch" -> {
            runtimeOnly("ai.djl.pytorch:pytorch-engine:$djlVersion")
            runtimeOnly("ai.djl.pytorch:pytorch-native-auto:1.5.0-SNAPSHOT")
//            runtimeOnly("ai.djl.pytorch:pytorch-native-cpu:osx-x86_64:1.4.0")
        }
        else -> throw IllegalStateException("Unrecognized engine config: \"$djlEngine\"")
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}