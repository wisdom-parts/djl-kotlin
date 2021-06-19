plugins {
    kotlin("jvm")
}

dependencies {
    val djlVersion: String by project
    api(platform("ai.djl:bom:$djlVersion"))
    api("ai.djl:api")

    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

