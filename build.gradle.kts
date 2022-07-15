plugins {
    kotlin("jvm") version "1.6.10" apply false
    kotlin("plugin.serialization") version "1.6.10" apply false
    id("org.openjfx.javafxplugin") version "0.0.13" apply false
}

group = "com.github.diosa"
version = "1.0"

repositories {
    mavenCentral()
//    maven(url = "https://jitpack.io/")
}
