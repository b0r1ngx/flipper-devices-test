enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

//    versionCatalogs {
//        create("libs") {
//            from(files("../gradle/libs.versions.toml"))
//        }
//    }
}

rootProject.name = "decompose-template"
include(":shared")
include(":compose-ui")
include(":app-android")
include(":app-desktop")
