plugins {
    kotlin("jvm") version "1.5.10"
}

allprojects {
    group = "parts.wisdom.djlkotlin"
    version = "0.0.1"

    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }

}