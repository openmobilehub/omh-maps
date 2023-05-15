[![GitHub license](https://img.shields.io/github/license/openmobilehub/omh-maps)](https://github.com/openmobilehub/omh-maps/blob/main/LICENSE)
![GitHub contributors](https://img.shields.io/github/contributors/openmobilehub/omh-maps)
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://developer.android.com/studio/releases/platforms#5.0)


# OMH Maps SDK

OMH is an open-source Android SDK to make easily swap between GMS, HMS and our custom OHM services.

It aims at creating low coupled, extensible SDK reducing the code boilerplate of switching between GMS, HMS, or any other service, and also provides a custom full open source alternative services switching automatically according to your configuration in the Gradle plugin giving the right outputs without overloading your APK with unnecessary libraries.

* [Documentation](#documentation)
* [Contributing](#contributing)

This repository allows you to display a map by using the common components for GMS and non GMS devices without worrying about the specific implementation for each type device.

# Sample App

TODO - Add intro and a [link](/omh-maps/maps-sample) to the sample app.

## Include Gradle dependencies

### OMH Maps SDKs - TODO
### OMH Core Plug-in - TODO
  * Without core plugin - Hans to review - TODO
  * With core plugin - Hans to review - TODO

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
## Setting Core Plugin

## Getting Started
The main interfaces that you will be interacting with are called `OmhMap`, `OmhMapView` and `OmhLocation`.
It contains all your basic maps and location functions like displaying a marker, map gestures, getting current location and more.
Additionally a fragment `OmhMapFragment` is provided, this fragment manages the life cycle of the map.

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

See example and check the full documentation at our Wiki.

TODO - KDOCS (Github pages)
TODO - 3P plugins/implementation (wiki)

## Contributing

We'd be glad if you decide to contribute to this project.

All pull request is welcome, just make sure that every work is linked to an issue on this repository so everyone can track it.
