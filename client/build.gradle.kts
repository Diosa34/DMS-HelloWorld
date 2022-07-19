plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.openjfx.javafxplugin")
}

repositories {
    mavenCentral()
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
                implementation("io.github.landgrafhomyak.itmo:dms-lab-core:1.0-b0+-SNAPSHOT")
                implementation ("no.tornado:fx:1.2.3")
                implementation(project(":common"))
                implementation("com.jfoenix:jfoenix:9.0.9")
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

application {
    mainClass.set("com.github.diosa.dms.mainGUI.MainGUI.java")
}

javafx {
    modules("javafx.controls", "javafx.fxml", "javafx.graphics")
}

val fatJar = tasks.create<Jar>("fatJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks["build"].dependsOn(fatJar)
