plugins {
    application
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

kotlin {
    target {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
            compileJavaTaskProvider.get().options.encoding = "UTF-8"
        }
    }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("com.github.Diosa34:ObjectConverter:master-SNAPSHOT")
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
