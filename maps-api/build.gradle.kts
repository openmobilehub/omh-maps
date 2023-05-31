plugins {
    `android-base-lib`
    id("org.jetbrains.dokka") version "1.7.20"
}

android {
    namespace = "com.omh.android.maps.api"

    viewBinding {
        enable = true
    }
}

dependencies {
    // KTX
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)

    // Android
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)
    implementation(Libs.reflection)

    implementation(Libs.googlePlayBase)

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
    testImplementation(Libs.mockk)
}