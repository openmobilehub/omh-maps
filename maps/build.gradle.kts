plugins {
    `android-base-lib`
}

android {
    namespace = "com.github.openmobilehub.maps"

    viewBinding {
        enable = true
    }
}

dependencies {
    // KTX
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)

    implementation(Libs.androidAppCompat)
    implementation(Libs.material)

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
}