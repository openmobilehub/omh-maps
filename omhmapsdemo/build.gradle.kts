plugins {
    id("org.jetbrains.kotlin.android")
    `android-application`
}

android {
    namespace = "com.github.omhmapsdemo"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("com.github.openmobilehub:maps:1.0-SNAPSHOT")

    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
}