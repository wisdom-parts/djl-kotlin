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
}

