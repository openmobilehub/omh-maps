plugins {
    `android-base-lib`
}

android {
    namespace = "com.github.mapsgms"

    viewBinding {
        enable = true
    }
}

dependencies {
    api(project(":maps"))

    // KTX
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)

    // Android
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)

    // Play services
    implementation(Libs.playServicesMaps)
    implementation(Libs.playServicesLocation)

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
    testImplementation(Libs.mockk)
}