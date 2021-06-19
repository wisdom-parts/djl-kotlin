plugins {
    kotlin("jvm")
}

dependencies {
    val djlVersion: String by project
    api(platform("ai.djl:bom:$djlVersion"))

    val djlEngine: String by project
    val e = djlEngine.toLowerCase()
    api("ai.djl.$e:$e-engine")
    api("ai.djl.$e:$e-native-auto")

    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

