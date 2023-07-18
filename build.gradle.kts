plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}

subprojects {
    repositories {
        mavenCentral()
        google()
        maven("https://s01.oss.sonatype.org/content/groups/staging/")
    }
}

val ossrhUsername by extra(getValueFromEnvOrProperties("ossrhUsername"))
val ossrhPassword by extra(getValueFromEnvOrProperties("ossrhPassword"))
val mStagingProfileId by extra(getValueFromEnvOrProperties("stagingProfileId"))
val signingKeyId by extra(getValueFromEnvOrProperties("signing.keyId"))
val signingPassword by extra(getValueFromEnvOrProperties("signing.password"))
val signingKey by extra(getValueFromEnvOrProperties("signing.key"))

// Set up Sonatype repository
nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(mStagingProfileId.toString())
            username.set(ossrhUsername.toString())
            password.set(ossrhPassword.toString())
            // Add these lines if using new Sonatype infra
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

fun getValueFromEnvOrProperties(name: String): Any? {
    val localProperties =
        com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
    return System.getenv(name) ?: localProperties[name]
}
