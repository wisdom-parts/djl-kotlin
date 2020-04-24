import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "parts.wisdom.djlkotlin.examples.mnist1d.ExampleMnist1DKt"
}

dependencies {
    implementation(project(":api"))
    implementation(project(":basicdataset"))
    implementation(project(":model-zoo"))

    implementation("org.slf4j:slf4j-simple:1.7.30")

    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    val djlVersion: String by project
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
    }
}


val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"
