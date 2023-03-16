plugins {
    `android-base-lib`
}

android {
    namespace = "com.openmobilehub.maps.api"

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

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
}