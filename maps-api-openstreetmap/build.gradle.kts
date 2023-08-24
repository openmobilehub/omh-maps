plugins {
    `android-base-lib`
}

android {
    namespace = "com.omh.android.maps.api.openstreetmap"
}

dependencies {
    api("com.openmobilehub.android:maps-api:1.0.2-beta")

    // KTX
    implementation(Libs.coreKtx)
    implementation(Libs.lifecycleKtx)

    // Android
    implementation(Libs.androidAppCompat)
    implementation(Libs.material)
    implementation(Libs.preference)

    // Open Street Map
    implementation(Libs.osmdroid)

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
    testImplementation(Libs.mockk)
}