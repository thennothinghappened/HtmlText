
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("maven-publish")
}

val GITHUB_USER = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USER")
val GITHUB_TOKEN = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")

kotlin {
    jvm("desktop")
    jvmToolchain(18)

    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.material3)
                implementation("ca.gosyer:accompanist-flowlayout:0.25.2")
                implementation("org.jsoup:jsoup:1.15.3")
                implementation("com.alialbaali.kamel:kamel-image:0.4.1")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.12.0")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
    }
}

android {
    namespace = group.toString()
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        compileSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

publishing {
    repositories {
        maven {
            setUrl("https://maven.pkg.github.com/thennothinghappened/HtmlText")
            credentials {
                username = GITHUB_USER
                password = GITHUB_TOKEN
            }
        }
    }
}