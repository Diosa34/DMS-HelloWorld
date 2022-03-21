import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    application
    kotlin("jvm") version "1.6.10"
}
application {
    mainClass.set("Main")
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.Diosa34:ObjectConverter:master-SNAPSHOT")
}
repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.jar {
    manifest {
        attributes(mapOf("Main-Class" to "Main",
        "Class-Path" to "ObjectConverter.jar"))
    }
}