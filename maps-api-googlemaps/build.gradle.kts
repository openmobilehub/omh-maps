plugins {
    `android-base-lib`
}

android {
    namespace = "com.omh.android.maps.api.googlemaps"

    viewBinding {
        enable = true
    }
}

dependencies {
    api(project(":maps-api"))

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