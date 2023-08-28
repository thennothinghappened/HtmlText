plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "org.orca.htmltext"
version "1.0.0"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.5.0")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "org.orca.htmltext.android"
        minSdk = 24
        compileSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}