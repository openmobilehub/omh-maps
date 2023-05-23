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
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/967606b6-ce1d-43b8-9a88-f4dedb2108fd" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/e52c8331-2f35-4261-9fa4-172e5c6b6478" width="200" height="auto">

## Set up the development environment
1. Android Studio is required. If you haven't already done so, [download](https://developer.android.com/studio/index.html) and [install](https://developer.android.com/studio/install.html?pkg=studio) it.
2. Ensure that you are using the [Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin) version 7.0 or later in Android Studio.

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

4. Then, to read the value from the `local.properties` and use in the `AndroidManifest.xml` file:

```groovy
def localProps = new Properties()
localProps.load(rootProject.file("local.properties").newDataInputStream())
manifestPlaceholders = [MAPS_API_KEY: "${localProps.getProperty('MAPS_API_KEY')}"]
```

## Gradle dependencies
To integrate the OMH Maps in your project is required to add some Gradle dependencies.

### OMH Core Plugin
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

4. Finally, Sync Project with Gradle Files.

![sync-project-with-gradle-files](https://github.com/openmobilehub/omh-maps/assets/124717244/c79ccfaf-5e82-45a7-a1a1-ac75e1522f35)

**Note:** If you encounter the error "Missing BuildConfig.MAPS_GMS_PATH and BuildConfig.MAPS_NON_GMS_PATH in BuildConfig class". Follow the next steps:
1. Sync Project with Gradle Files.

![sync-project-with-gradle-files](https://github.com/openmobilehub/omh-maps/assets/124717244/c79ccfaf-5e82-45a7-a1a1-ac75e1522f35)
   
2. Clean Project.

![clean](https://github.com/openmobilehub/omh-maps/assets/124717244/802d3e87-9f4d-4c2f-9819-6ff71eed207b)

3. Rebuild Project.

![rebuild](https://github.com/openmobilehub/omh-maps/assets/124717244/3fd973f6-9fad-4835-8ce9-c8b8aea438c3)

4. Run the app.

![run](https://github.com/openmobilehub/omh-maps/assets/124717244/50ee8984-d991-4bc0-980c-266b19275118)

5. If still not working Invalidate the caches.

![invalidate-caches](https://github.com/openmobilehub/omh-maps/assets/124717244/c5e1abad-2105-424b-9b42-a58cea9d7121)

### Configure the Core plugin
To use the core plugin is required some minimum configuration, for more details [Docs](https://github.com/openmobilehub/omh-core/tree/release/1.0)

1. Go to your app's `build.gradle` file and add the next code:

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

2. Now you can select in the build variants:

![build-variants](https://github.com/openmobilehub/omh-maps/assets/124717244/91c511dd-d8ff-42b1-9fde-2340fec3277b)

3. Finally you need to instantiate the Provider. The recommendation is to do it in the Application
guarantee that the provider is instantiate correctly for the app.
For example:

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

Note: if you create your of Application, remember to add in the `AndroidManifest.xml` of the app.

```
<application
        android:name=".DemoApp"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.OMHMaps">
```

4. Finally in your code you can get a `OmhMapView` or `OmhLocation`, for example:

```kotlin
val omhLocation = OmhMapProvider.getInstance().provideOmhLocation(context)
```

## Getting Started
The main interfaces that you will be interacting with are called `OmhMap`, `OmhMapView` and `OmhLocation`.
It contains all your basic maps and location functions like displaying a marker, map gestures, getting current location and more.
Additionally a fragment `OmhMapFragment` is provided, this fragment manages the life cycle of the map.

**Important:** Before use be sure to add in the app's `AndroidManifest.xml` the required permissions:

```
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

**Disclaimer:** This is a tool in development, it could change some implementation details in the future.

### OmhMapFragment
This fragment is the simplest way to place a map in an application.
Being a fragment, this component can be added to an activity's layout file simply with the XML below.

```xml
<fragment
    android:id="@+id/fragment_map_container"
    android:name="com.omh.android.maps.api.presentation.fragments.OmhMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

An `OmhMap` must be acquired using `getMapAsync(OmhOnMapReadyCallback)`. This class automatically initializes the maps system and the view.
Example to get `OmhMap` using the `OmhMapFragment`.

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Obtain the OmhMapFragment and get notified when the map is ready to be used.
    val omhMapFragment = childFragmentManager.findFragmentById(R.id.fragment_map_container) as? OmhMapFragment
    omhMapFragment.getMapAsync(this)
}

override fun onMapReady(omhMap: OmhMap) {
    // omhMap object ready to use.
}
```

Any object obtained from the `OmhMap` is associated with the view. It's important to not hold on to objects (e.g. `Marker`) beyond the view's life. Otherwise it will cause a memory leak as the view cannot be released.

### OmhMapView
The snippet below shows how to use the OmhMapView. `OmhMapView` displays a map getting a View using `getView(): View?`

```kotlin
val view = omhMapView.getView()
```

An `OmhMap` must be acquired using `getMapAsync(OnOmhMapCallback)`. The `OmhMapView` automatically initializes the maps system and the view.

```kotlin
omhMapView.getMapAsync { omhMap ->
     // omhMap object ready to use.
}
```

**Note:** Advised not to add children to this view.

### OmhMap
This is the main class of the OMH Maps SDK for Android and is the entry point for all methods related to the map. You cannot instantiate a `OmhMap` object directly, rather, you must obtain one from the `getMapAsync() `method on a `OmhMapFragment` or `OmhMapView` that you have added to your application.

**Note:** Similar to a `View` object, an `OmhMap` can only be read and modified from the Android UI thread. Calling `OmhMap` methods from another thread will result in an exception.

You can use the map's camera to set parameters as location and zoom level. For more information, see [Documentation](#documentation)

```kotlin
override fun onMapReady(omhMap: OmhMap) {
    // omhMap object is ready to use.
    // Example of usage. 
    omhMap.setZoomGesturesEnabled(true)
    omhMap.setMyLocationEnabled(true)
}
```

## Documentation

See example and check the full documentation and add custom implementation at our [Wiki](https://github.com/openmobilehub/omh-maps/wiki).

Additionally for more information about the OMH Map functions, [Docs](https://openmobilehub.github.io/omh-maps).

## Contributing

We'd be glad if you decide to contribute to this project.

All pull request is welcome, just make sure that every work is linked to an issue on this repository so everyone can track it.
For more information check [CONTRIBUTING](https://github.com/openmobilehub/omh-maps/blob/release/1.0/CONTRIBUTING.md)
