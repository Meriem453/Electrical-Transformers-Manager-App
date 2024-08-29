import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {21
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "desktop_test"
            packageVersion = "1.0.0"
            windows {

                // a version only for the exe package
                exePackageVersion = "1.0.0"

                iconFile.set(project.file("src/jvmMain/resources/images/sonelgaz_logo.ico"))
                packageName = "Sonelgaz"
                packageVersion = "1.0.0"
                description = "Transfo management"
                copyright = "© 2024 Zemane Meriem. All rights reserved."
            }
        }


    }
}
