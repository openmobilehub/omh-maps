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
In the project's `build.gradle` file comment the stage reference and add maven local:

```
repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    mavenLocal()
    // maven("https://s01.oss.sonatype.org/content/groups/staging/")
}
```

Go to Android Studio -> Gradle tab and run the `publishToMavenLocal` 
in the `maps-api`, `maps-api-googlemaps` and `maps-api-openstreetmap` and run `publishtoMavenLocal`

![gradle-maps-api](https://github.com/openmobilehub/omh-maps/assets/124717244/7a8aeb52-fcf2-4c8c-a0e8-e249e69b3fea)
![gradle-maps-api-gms](https://github.com/openmobilehub/omh-maps/assets/124717244/e5a370d9-1429-4234-a884-b39a23c6dadb)
![gradle-maps-api-ngms](https://github.com/openmobilehub/omh-maps/assets/124717244/2cc52110-8faa-47e3-9298-a6cec846a348)


#### Step 2: Verify plugin is published

Go to `/Users/your_user/.m2` dot folder and you'll find the plugin.

#### Step 2: Debug

Add some prints to debug the code

#### Step 3: Test it

Create a sample project, add the plugin and sync the project with gradle and you'll see logs in the `Build` tab in Android Studio.

## Checking your work

You can verify your code with the following tasks:

```
./gradlew assemble
./gradlew detekt
```

## Write documentation

This project has documentation in a few places:

### Introduction and usage

A friendly `README.md` written for many audiences.

### Examples and advanced usage

The [wiki](https://github.com/openmobilehub/omh-maps/wiki).

## Releasing a new version

1. Clone the repository
2. Update the changelog (and commit it afterwards)
3. Push the changes and wait for the latest CI build to complete
4. Bump the version, create a Git tag and commit the changes
5. Push the version bump commit: `git push`
6. Push the Git tag: `git push --tags`
