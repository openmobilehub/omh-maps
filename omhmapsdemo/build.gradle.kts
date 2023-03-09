plugins {
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

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":maps"))

    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
}