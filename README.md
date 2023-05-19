[![GitHub license](https://img.shields.io/github/license/openmobilehub/omh-maps)](https://github.com/openmobilehub/omh-maps/blob/main/LICENSE)
![GitHub contributors](https://img.shields.io/github/contributors/openmobilehub/omh-maps)
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://developer.android.com/studio/releases/platforms#5.0)


# OMH Maps SDK
OMH is an open-source Android SDK to make easily swap between GMS and our custom OHM services.

It aims at creating low coupled, extensible SDK reducing the code boilerplate of switching between GMS, HMS, or any other service, and also provides a custom full open source alternative services switching automatically according to your configuration in the Gradle plugin giving the right outputs without overloading your APK with unnecessary libraries.
This repository allows you to display a map by using the common components for GMS and non GMS devices without worrying about the specific implementation for each type device.

# Sample App
Sample app demonstrates how to use Omh Maps SDK functionalities, [sample](/omh-maps/tree/develop/maps-sample).

<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/e36d24ee-2b12-4d64-847e-884bd414611b" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/b781c592-2836-4fb7-b3b4-83136174b48c" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/c6c4e3e8-ccf6-45a5-abeb-07cdf7160664" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/0f37a011-f1ab-43e8-8e97-a99ed982f9f8" width="200" height="auto">

<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/77d7f107-0283-4d1e-8daa-288f61db856f" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/4548376a-326c-49a5-9786-820da2ae39b6" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/5adea3da-21ef-4081-9568-85ea7c9c9495" width="200" height="auto">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/03aab198-943c-4e2a-9f07-ce6104729efc" width="200" height="auto">

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
3. In your `AndroidManifest.xml file`, go to `com.google.android.geo.API_KEY` and update the `android:value attribute` as follows:

```xml
<meta-data
   android:name="com.google.android.geo.API_KEY"
   android:value="${MAPS_API_KEY}" />
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
For more information check [CONTRIBUTING](https://github.com/openmobilehub/omh-maps/blob/main/CONTRIBUTING.md)
