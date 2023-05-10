import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    `android-application`
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android") version "2.44" apply true
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply true
}

android {
    namespace = "com.omh.android.maps.sample"

    defaultConfig {
        val apiKey = "MAPS_API_KEY"
        manifestPlaceholders[apiKey] = gradleLocalProperties(rootDir)[apiKey].toString()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "google_services"
    productFlavors {
        create("gms") {
            dimension = "google_services"
        }
        create("nonGms") {
            dimension = "google_services"
        }
        create("singleBuild") {
            dimension = "google_services"
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        viewBinding = true
    }
}

val gmsImplementation by configurations
val nonGmsImplementation by configurations
val singleBuildImplementation by configurations

dependencies {
    // OMH SDK
    gmsImplementation(project(":maps-api-googlemaps"))
    nonGmsImplementation(project(":maps-api-openstreetmap"))
    singleBuildImplementation(project(":maps-api-googlemaps"))
    singleBuildImplementation(project(":maps-api-openstreetmap"))

    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)
    implementation(Libs.reflection)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    // Test
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
}