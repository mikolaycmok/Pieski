pluginManagement {
    repositories {
        google()  // <- WAŻNE!
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()  // <- WAŻNE!
        mavenCentral()
    }
}

rootProject.name = "Pieski"
include(":app")
