pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "omh-maps"
include(":maps-api")
include(":maps-sample")
include(":maps-api-googlemaps")
include(":maps-api-openstreetmap")
