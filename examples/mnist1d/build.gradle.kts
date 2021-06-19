import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("parts.wisdom.djlkotlin.examples.mnist1d.ExampleMnist1DKt")
}

dependencies {
    implementation(project(":api"))
    implementation(project(":basicdataset"))
    implementation(project(":model-zoo"))
    implementation(project(":engine"))

    implementation("org.slf4j:slf4j-simple:1.7.30")

    // implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

val compileKotlin: KotlinCompile by tasks

// compileKotlin.kotlinOptions.jvmTarget = "1.8"



