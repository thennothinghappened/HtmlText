
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
    id("maven-publish")
}

val githubUser: String? = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USER")
val githubToken: String? = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")

kotlin {

    jvm("desktop")
    jvmToolchain(17)

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
                api(compose.material3)
                implementation(libs.jsoup)
                implementation(libs.kamelImage)
            }
        }

        val androidMain by getting

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }

    }
}

android {

    namespace = group.toString()
    compileSdk = libs.versions.android.sdk.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = libs.versions.android.sdk.minSdk.get().toInt()
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
                username = githubUser
                password = githubToken
            }
        }
    }
}
