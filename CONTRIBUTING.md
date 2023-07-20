# How to contribute

Bug reports and pull requests from users are what keep this project working.

## Basics

1. Create an issue and describe your idea
2. [Fork it](https://github.com/openmobilehub/omh-maps/fork)
3. Create your feature branch (`git checkout -b my-new-feature`)
4. Commit your changes (`git commit -am 'Add some feature'`)
5. Publish the branch (`git push origin my-new-feature`)
6. Create a new Pull Request

## Running for development

#### Step 1: Publish the plugin to mavenLocal
1. In the project's `build.gradle` file comment the stage reference and add maven local:

```kotlin
repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    mavenLocal()
}
```

2. Edit the `android-base-lib.gradle` file in `buildSrc` and remove `id("signing"")` inside the plugins:

```kotlin
plugins {
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")
    kotlin("android")
    id("jacoco")
    id("maven-publish")
}
```

3. Below the plugins` add the next code:

```kotlin
publishing {
    publications {
        register<MavenPublication>("release") {
            group = getPropertyOrFail("group")
            artifactId = properties.get("artifactId").toString()
            version = getPropertyOrFail("version")

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
```

4. Below the comment `// Publishing block` remove all the code.

5. Go to Android Studio -> Gradle tab and run the `publishToMavenLocal`in the `maps-api`, `maps-api-googlemaps` and `maps-api-openstreetmap`:

![gradle-maps-api](https://github.com/openmobilehub/omh-maps/assets/124717244/7a8aeb52-fcf2-4c8c-a0e8-e249e69b3fea)
![gradle-maps-api-gms](https://github.com/openmobilehub/omh-maps/assets/124717244/e5a370d9-1429-4234-a884-b39a23c6dadb)
![gradle-maps-api-ngms](https://github.com/openmobilehub/omh-maps/assets/124717244/2cc52110-8faa-47e3-9298-a6cec846a348)


#### Step 2: Verify plugin is published

Go to `/Users/your_user/.m2` dot folder and you'll find the plugin.

#### Step 3: Debug

Add some prints to debug the code

#### Step 4: Test it

Create a sample project, add the plugin and sync the project with gradle and you'll see logs in the `Build` tab in Android Studio.

## Checking your work

You can verify your code with the following tasks:

```
./gradlew assemble
./gradlew detekt
```

Once you have made a change in any of the `maps-api`, `maps-api-google maps` or `maps-api-openstreetmap` modules, 
you must `publishToMavenLocal` in that module in order to see the changes.

## Write documentation

This project has documentation in a few places:

### Introduction and usage

A friendly [README.md](https://github.com/openmobilehub/omh-maps/blob/refactor/documentation/README.md) written for many audiences.

### Examples and advanced usage

You can find more information in the [wiki](https://github.com/openmobilehub/omh-maps/wiki).

## Releasing a new version

1. Clone the repository
2. Update the changelog (and commit it afterwards)
3. Push the changes and wait for the latest CI build to complete
4. Bump the version, create a Git tag and commit the changes
5. Push the version bump commit: `git push`
6. Push the Git tag: `git push --tags`
