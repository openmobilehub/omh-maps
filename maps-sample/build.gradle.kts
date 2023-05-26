@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    `android-application`
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android") version "2.44" apply true
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply true
    id("com.openmobilehub.android.omh-core")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

omhConfig {
    bundle("singleBuild") {
        maps {
            gmsService {
                dependency = "com.openmobilehub.android:maps-api-googlemaps:1.0"
            }
            nonGmsService {
                dependency = "com.openmobilehub.android:maps-api-openstreetmap:1.0"
            }
        }
    }
    bundle("gms") {
        maps {
            gmsService {
                dependency = "com.openmobilehub.android:maps-api-googlemaps:1.0"
            }
        }
    }
    bundle("nongms") {
        maps {
            nonGmsService {
                dependency = "com.openmobilehub.android:maps-api-openstreetmap:1.0"
            }
        }
    }
}

android {
    namespace = "com.omh.android.maps.sample"

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        buildConfig = true
    }
}

dependencies {
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