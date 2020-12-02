import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "com.draketalley"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:4.3.1") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:4.3.1") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:4.3.1") // for kotest property test
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
