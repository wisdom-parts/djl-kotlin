plugins {
    kotlin("jvm") version "1.3.72"
}

allprojects {
    group = "parts.wisdom.djlkotlin"
    version = "0.0.1"

    repositories {
        jcenter()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
}