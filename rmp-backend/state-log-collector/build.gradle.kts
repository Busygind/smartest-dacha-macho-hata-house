import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("io.ktor.plugin") version "2.3.10"
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.ktor:ktor-serialization-jackson")
    implementation("io.ktor:ktor-jackson:1.6.8")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("org.slf4j:slf4j-reload4j:2.0.12")
    implementation("org.lz4:lz4-java:1.8.0")
    implementation("org.redisson:redisson:3.21.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.github.vitalyros:redisson-kotlin-coroutines-reactive:0.0.1")
    implementation("com.clickhouse:clickhouse-http-client:0.5.0")
    implementation("com.github.salomonbrys.kodein:kodein:4.1.0")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation(project(mapOf("path" to ":dacha-backend-model")))
    implementation(project(mapOf("path" to ":dacha-backend-model")))
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
repositories {
    mavenCentral()
}

application {
    mainClass = "com.dacha.ApplicationKt"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}

kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}