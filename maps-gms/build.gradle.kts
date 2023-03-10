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

    implementation(Libs.androidAppCompat)
    implementation(Libs.material)
    implementation("com.google.android.gms:play-services-maps:18.1.0")

    // Test dependencies
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidJunit)
}