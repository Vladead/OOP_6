import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.compose.compose

plugins {
    kotlin("jvm") version "1.4.20"
//    kotlin("plugin.serialization") version "1.4.20"
    id("org.jetbrains.compose") version "0.2.0-build132"
//    application
}

group = "me.vladk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(project(":figures"))
    implementation(compose.desktop.currentOs)
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.0.1")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
