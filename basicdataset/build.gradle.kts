plugins {
    kotlin("jvm")
}

dependencies {
    val djlVersion: String by project
    api(platform("ai.djl:bom:$djlVersion"))
    api("ai.djl:basicdataset")

    // implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}