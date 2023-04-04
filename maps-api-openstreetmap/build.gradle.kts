plugins {
    `android-base-lib`
}

android {
    namespace = "com.omh.android.maps.api.openstreetmap"
}

dependencies {
    api(project(":maps-api"))

    // KTX
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)

    // Android
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)

    // Open Street Map
    implementation(Libs.osmdroid)

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
    testImplementation(Libs.mockk)
}