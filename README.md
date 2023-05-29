[![GitHub license](https://img.shields.io/github/license/openmobilehub/omh-maps)](https://github.com/openmobilehub/omh-maps/blob/main/LICENSE)
![GitHub contributors](https://img.shields.io/github/contributors/openmobilehub/omh-maps)
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://developer.android.com/studio/releases/platforms#5.0)


# OMH Maps SDK
The solution to seamlessly integrating maps across GMS and non-GMS devices. Our open-source Android SDK tackles the problem of device compatibility, allowing developers to use the same SDK without worrying about specific device requirements.

With OMH Maps SDK, you can effortlessly incorporate Google Maps and OpenStreetMap implementations into your applications, regardless of whether the device has Google Mobile Services or not. 
Our SDK handles the complexities behind the scenes, providing a unified interface and common components for consistent map functionality.

# Provider Implementations
We also believe in the power of community collaboration. That's why OMH Maps SDK is open-source, inviting contributions and supporting plugins from other map providers. 
Together, we can expand the capabilities of the SDK and enhance the range of supported map services.

# Sample App
Sample app demonstrates how to use Omh Maps SDK functionalities, [sample](/omh-maps/tree/develop/maps-sample).

<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/35b0aa31-b550-457d-af9c-d536998fb99c" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/ee0e8400-0ca4-419a-a2f6-316775111c92" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/0faae0fc-ba41-4927-a13d-51cd7e976e46" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/af321137-8304-4e09-a6e9-3aa2e5370538" width="200" height="370">

<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/6487d3cb-7913-428a-b083-22fc098d3934" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/d40f78da-a992-4f24-8c26-bd9c92407f16" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/967606b6-ce1d-43b8-9a88-f4dedb2108fd" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/e52c8331-2f35-4261-9fa4-172e5c6b6478" width="200" height="370">

# Getting started

## Set up the development environment
1. Android Studio is required. If you haven't already done so, [download](https://developer.android.com/studio/index.html) and [install](https://developer.android.com/studio/install.html?pkg=studio) it.
2. Ensure that you are using the [Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin) version 7.0 or later in Android Studio).

## Set up your Google Cloud project for applications with Google Services(Google Maps)
Complete the required Cloud Console setup steps by clicking through the following tabs:

### Steps
1. In the Google Maps Cloud Console, on the project selector page, click **Create Project** to begin creating a new Cloud project, [Go to the project selector page](https://console.cloud.google.com/projectselector2/home/dashboard?utm_source=Docs_ProjectSelector&_gl=1*1ylhfe0*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.). 
2. Make sure that billing is enabled for your Cloud project, [Confirm that billing is enabled for your project](https://console.cloud.google.com/project/_/billing?_gl=1*msea12*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.). 
3. To use Google Maps Platform, you must enable the APIs or SDKs you plan to use with your project, [Enable the Maps SDK for Android](https://console.cloud.google.com/apis/library/maps-android-backend.googleapis.com?utm_source=Docs_EnableSpecificAPI&_gl=1*t8hu9m*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.).
4. The below steps only go through the API Key creation process.
5. Go to the **Google Maps Platform > Credentials** page, [Go to the Credentials page](https://console.cloud.google.com/project/_/google/maps-apis/credentials?utm_source=Docs_Credentials&_gl=1*9zgq7y*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.).
6. On the **Credentials** page, click **Create credentials > API key**. The **API key created** dialog displays your newly created API key.
7. Click **Close**. The new API key is listed on the **Credentials** page under **API keys**. (Remember to [restrict the API](https://developers.google.com/maps/api-security-best-practices#restricting-api-keys) key before using it in production.)

## Add the API key to your app
You should not check your API key into your version control system, so it is recommended
storing it in the `local.properties` file, which is located in the root directory of your project.
For more information about the `local.properties` file, see [Gradle properties](https://developer.android.com/studio/build#properties-files)
[files](https://developer.android.com/studio/build#properties-files).

1. Open the `local.properties` in your project level directory, and then add the following code. Replace `YOUR_API_KEY` with your API key.
`MAPS_API_KEY=YOUR_API_KEY`
2. Save the file.
3. In your `AndroidManifest.xml`file, go to `com.google.android.geo.API_KEY` and update the `android:value attribute` as follows:

```xml
<meta-data
   android:name="com.google.android.geo.API_KEY"
   android:value="${MAPS_API_KEY}" />
```

**Important:** Before use be sure to add in the app's `AndroidManifest.xml` the required permissions:

```
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

4. Add Secrets (Gradle plugin for Android)[https://github.com/google/secrets-gradle-plugin] to read the API key.
- In your top-level settings.gradle file, include the Gradle plugin portal, Google Maven repository, and Maven central repository under the pluginManagement block. 
The pluginManagement block must appear before any other statements in the script.

```
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
} 
```
- In Android Studio, open your project-level build.gradle file and add the following code to the dependencies element under buildscript.

```
plugins {
    // ...
    id 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin' version '2.0.1' apply false
}
```

- Open your module-level build.gradle file and add the following code to the plugins element.

```
id 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin'
```

- Save the file and sync your project with Gradle.

## Gradle configuration
To integrate the OMH Maps in your project is required to add some Gradle dependencies.

### Add OMH Core plugin
To add the core plugin dependency in a new project, follow the next steps:

1. In the project's `settings.gradle` file in the `dependencyResolutionManagement`, 
then in the `repositories` the maven reference `maven { url 'https://s01.oss.sonatype.org/content/groups/staging/' }`

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://s01.oss.sonatype.org/content/groups/staging/' }
    }
}
```

2. In the project's `build.gradle` add the next script

```
buildscript {
   repositories {
        maven { url 'https://s01.oss.sonatype.org/content/groups/staging/' }
   }
   dependencies {
        classpath 'com.openmobilehub.android:omh-core:1.0'
   }
}
```

3. In the app's `build.gradle` add the plugin id

```
id 'com.openmobilehub.android.omh-core'
```

4. Sync Project with Gradle Files.

**Note:** If you encounter the error "Missing BuildConfig.MAPS_GMS_PATH and BuildConfig.MAPS_NON_GMS_PATH in BuildConfig class". Follow the next steps:
1. Sync Project with Gradle Files.
2. Clean Project.
3. Rebuild Project.
4. Run the app.
5. If still not working, Invalidate the caches.

### Configure the OMH Core plugin
To use the core plugin is required some minimum configuration, for more details see [OMH Core Docs](https://github.com/openmobilehub/omh-core/tree/release/1.0).
1. Go to your app's `build.gradle` file and inside `buildFeatures` add `buildConfig = true`:

```
android {
   buildFeatures {
      buildConfig = true
   }
}
```

2. In the same file in the app's `build.gradle`, add the following code to set the build variants with the [OMH Core](https://github.com/openmobilehub/omh-core/tree/release/1.0).

```
omhConfig {
    bundle("singleBuild") {
        maps {
            gmsService {
                dependency = "com.openmobilehub.android:maps-api-googlemaps:1.0"
            }
            nonGmsService {
                dependency = "com.openmobilehub.android:maps-api-openstreetmap:1.0"
            }
        }
    }
    bundle("gms") {
        maps {
            gmsService {
                dependency = "com.openmobilehub.android:maps-api-googlemaps:1.0"
            }
        }
    }
    bundle("nongms") {
        maps {
            nonGmsService {
                dependency = "com.openmobilehub.android:maps-api-openstreetmap:1.0"
            }
        }
    }
}
```

3. You can now select from Android Studio build variants.
**Note:** The "omhConfig" definition is used to extend the existing Android Studio variants in the core plugin. It's important to observe how a single build encompasses both GMS (Google Mobile Services) and Non-GMS configurations.
4. Create an instance of the provider. The recommendation is to do it in the Application to ensure that the provider is instantiated correctly for the application.
You can choose whatever name you want for the class, it is not mandatory to use `DemoApp`.

```kotlin
class DemoApp : Application() {
    
    var omhProviderBuilder: OmhMapProvider.Builder? = null

    override fun onCreate() {
        super.onCreate()
        
        val omhProviderBuilder = OmhMapProvider.Builder()
        omhProviderBuilder
            .addGmsPath(BuildConfig.MAPS_GMS_PATH)
            .addNonGmsPath(BuildConfig.MAPS_NON_GMS_PATH)
        initialize(omhProviderBuilder)
    }
}
```

**Note:** if you create your class that implements the 'Application' class, remember to add it in the 'AndroidManifest.xml' of the application.
Also, don't forget to replace the class name with the one created in your application.
Before:

```
<application
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.OMHMaps">
```

Add the class that was created above, in this example it is `DemoApp`:
After: 

```
<application
        android:name=".DemoApp"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.OMHMaps">
```

## Add the map into your app
The main interfaces that you will be interacting with are called `OmhMap`, `OmhMapView` and `OmhLocation`.
It contains all your basic maps and location functions like displaying a marker, map gestures, getting current location and more.
Additionally a fragment `OmhMapFragment` is provided, this fragment manages the life cycle of the map.

### Map fragment
`OmhMapFragment` is the simplest way to place a map in an application.
Being a fragment, this component can be added to an activity's layout file simply with the XML below.

```xml
<fragment
    android:id="@+id/fragment_map_container"
    android:name="com.omh.android.maps.api.presentation.fragments.OmhMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

Now just run your app and you will see the map.

### Display your current location
An `OmhMap` must be acquired using `getMapAsync(OmhOnMapReadyCallback)`. This class automatically initializes the maps system and the view.
Example to get `OmhMap` using the `OmhMapFragment`.

1. Implement the `OmhOnMapReadyCallback` interface and override the `onMapReady()` method, to set up the map when the `OmhMap` object is available:

```kotlin
class MapsMarkerActivity : AppCompatActivity(), OmhOnMapReadyCallback {

    // ...

    override fun onMapReady(omhMap: OmhMap) {
        if (!hasPermissions()) {
            Log.e("permission error", "Not required permissions to get current location")
            return
        }
        
        val onSuccessListener = OmhSuccessListener { omhCoordinate ->
            omhMap.moveCamera(omhCoordinate, 15f)
            val omhMarkerOptions = OmhMarkerOptions().apply {
               title = "My Current Location"
               position = omhCoordinate
            }
            omhMap.addMarker(omhMarkerOptions)
        }
        val onFailureListener = OmhFailureListener { exception ->
            Log.e("location error", exception.localizedMessage, exception)
        }
        // Safe use of 'noinspection MissingPermission' since it is checking permissions in the if condition
        // noinspection MissingPermission
        OmhMapProvider.getInstance().provideOmhLocation(this).getCurrentLocation(onSuccessListener, onFailureListener)
    }
    
    private fun hasPermissions() = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION).all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}
```

2. In your activity's onCreate() method, set the layout file as the content view. Get a handle to the map fragment by calling FragmentManager.findFragmentById(). 
Then use `getMapAsync()` to register for the map callback:

```kotlin
override fun onCreate(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setContentView(R.layout.activity_maps)
    
    // Request permissions, this can be done in another way, see https://developer.android.com/training/permissions/requesting
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        // Obtain the OmhMapFragment and get notified when the map is ready to be used.
        val omhMapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map_container) as? OmhMapFragment
        omhMapFragment?.getMapAsync(this)
    }.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
}
```

3. Run your app.

**Note:** Any object obtained from the `OmhMap` is associated with the view. It's important to not hold on to objects (e.g. `OmhMarker`) beyond the view's life. Otherwise it will cause a memory leak as the view cannot be released.

4. Explore Advanced Features

   Congratulations on completing the getting started guide! Now, it's time to level up your mapping skills by diving into our advanced features. 

   Discover how to handle camera events, add markers, manage location data, work with gestures, utilize network utilities, and create custom Maps SDK implementations/plugins. These advanced capabilities    will elevate your mapping application to new heights.

   Visit our [Advanced Features wiki page](https://github.com/openmobilehub/omh-maps/wiki#ohm-map-sdk---advanced-features) to access detailed information, examples, and guides for each feature. Unleash    the full power of our mapping SDK and create exceptional mapping experiences.

   Take the next step and explore our Advanced Features now!

## Documentation

See example and check the full documentation and add custom implementation at our [Wiki](https://github.com/openmobilehub/omh-maps/wiki).

Additionally for more information about the OMH Map functions, [Docs](https://openmobilehub.github.io/omh-maps).

## Contributing

We'd be glad if you decide to contribute to this project.

All pull request is welcome, just make sure that every work is linked to an issue on this repository so everyone can track it.
For more information check [CONTRIBUTING](https://github.com/openmobilehub/omh-maps/blob/release/1.0/CONTRIBUTING.md).

**Disclaimer:** This client library is currently undergoing development, and there may be future modifications to specific implementation details.
