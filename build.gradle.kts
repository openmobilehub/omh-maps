import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}

subprojects {
    repositories {
        mavenCentral()
        google()
//        maven("https://s01.oss.sonatype.org/content/groups/staging/")
        mavenLocal()
    }
}

tasks.register("installPrePushHook", Copy::class) {
    from("tools/scripts/pre-push")
    into(".git/hooks")
    fileMode = 0b000_111_111_111
}

tasks.register("installPreCommitHook", Copy::class) {
    from("tools/scripts/pre-commit")
    into(".git/hooks")
    fileMode = 0b000_111_111_111
}

tasks {
    val installPrePushHook by existing
    val installPreCommitHook by existing
    getByName("prepareKotlinBuildScriptModel").dependsOn(installPrePushHook)
    getByName("prepareKotlinBuildScriptModel").dependsOn(installPreCommitHook)
}

val ossrhUsername by extra(getValueFromEnvOrProperties("OSSRH_USERNAME"))
val ossrhPassword by extra(getValueFromEnvOrProperties("OSSRH_PASSWORD"))
val mStagingProfileId by extra(getValueFromEnvOrProperties("SONATYPE_STAGING_PROFILE_ID"))
val signingKeyId by extra(getValueFromEnvOrProperties("SIGNING_KEY_ID"))
val signingPassword by extra(getValueFromEnvOrProperties("SIGNING_PASSWORD"))
val signingKey by extra(getValueFromEnvOrProperties("SIGNING_KEY"))

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
    val localProperties = gradleLocalProperties(rootDir)
    return System.getenv(name) ?: localProperties[name]
}
