[![GitHub license](https://img.shields.io/github/license/openmobilehub/omh-maps)](https://github.com/openmobilehub/omh-maps/blob/main/LICENSE)
![GitHub contributors](https://img.shields.io/github/contributors/openmobilehub/omh-maps)
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://developer.android.com/studio/releases/platforms#5.0)


# OMH Maps SDK overview
The solution to seamlessly integrating maps across GMS (Google Mobile Services) and non-GMS devices.
With OMH Maps SDK, you can effortlessly incorporate Google Maps and OpenStreetMap implementations into your applications, 
regardless of whether the device has Google Mobile Services or not. 
Our SDK handles the complexities behind the scenes, providing a unified interface and common components for consistent map functionality.

# Provider Implementations
We also believe in the power of community collaboration. That's why OMH Maps SDK is open-source, inviting contributions and supporting plugins from other map providers. 
Together, we can expand the capabilities of the SDK and enhance the range of supported map services.

# OMH Maps SDK quickstart
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/35b0aa31-b550-457d-af9c-d536998fb99c" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/ee0e8400-0ca4-419a-a2f6-316775111c92" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/0faae0fc-ba41-4927-a13d-51cd7e976e46" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/af321137-8304-4e09-a6e9-3aa2e5370538" width="200" height="370">

<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/6487d3cb-7913-428a-b083-22fc098d3934" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/d40f78da-a992-4f24-8c26-bd9c92407f16" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/967606b6-ce1d-43b8-9a88-f4dedb2108fd" width="200" height="370">
<img src="https://github.com/openmobilehub/omh-maps/assets/124717244/e52c8331-2f35-4261-9fa4-172e5c6b6478" width="200" height="370">

# Getting started
Create an Android app that displays a map by using an empty Activity for Android Studio.

## Set up the development environment
1. Android Studio is required. If you haven't already done so, [download](https://developer.android.com/studio/index.html) and [install](https://developer.android.com/studio/install.html?pkg=studio) it.
2. Ensure that you are using the [Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin) version 7.0 or later in Android Studio).

## Create a new project in Android Studio
1. Open Android Studio, and click Create New Project in the Welcome to Android Studio window.
2. In the New Project window, under the Phone and Tablet category, select "Empty Activity", and then click "Next".
3. Complete the Google Maps Activity form:
   - Set **Language** to Java or Kotlin. Both languages are fully supported by the Maps SDK for Android. To learn more about Kotlin.

   - Set **Minimum SDK** to an SDK version compatible with your test device. You must select a version greater than the minimum version required by the OMH Maps SDK for Android , which is currently Android API Level 21 (Android 5.0, Lollipop) or higher.
4. Click **Finish**. Android Studio starts Gradle and builds the project. This may take some time.

## Set up your Google Cloud project for applications with Google Services(Google Maps)
Complete the required Cloud Console setup following the next steps, for more information see [Documentation](https://developers.google.com/maps/documentation/android-sdk/cloud-setup)

### Steps

#### Set up your project
1. In the Google Maps Cloud Console, on the project selector page, click **Create Project** to begin creating a new Cloud project, [Go to the project selector page](https://console.cloud.google.com/projectselector2/home/dashboard?utm_source=Docs_ProjectSelector&_gl=1*1ylhfe0*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.). 
2. Make sure that billing is enabled for your Cloud project, [Confirm that billing is enabled for your project](https://console.cloud.google.com/project/_/billing?_gl=1*msea12*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.). 

#### Enable APIs
1. To use Google Maps Platform, you must enable the APIs or SDKs you plan to use with your project, [Enable the Maps SDK for Android](https://console.cloud.google.com/apis/library/maps-android-backend.googleapis.com?utm_source=Docs_EnableSpecificAPI&_gl=1*t8hu9m*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.).

#### Get an API Key
1. This step only goes through the API Key creation process. If you use your API Key in production, we strongly recommend that you restrict your API key. You can find more information in the product-specific Using API Keys page.
2. The API key is a unique identifier that authenticates requests associated with your project for usage and billing purposes. You must have at least one API key associated with your project.
3. Go to the **Google Maps Platform > Credentials** page, [Go to the Credentials page](https://console.cloud.google.com/project/_/google/maps-apis/credentials?utm_source=Docs_Credentials&_gl=1*9zgq7y*_ga*MTUwMDIzODY1Ni4xNjc1OTYyMDgw*_ga_NRWSTWS78N*MTY4MjA4ODIyNS44NS4xLjE2ODIwODgyMzcuMC4wLjA.).
4. On the **Credentials** page, click **Create credentials > API key**. The **API key created** dialog displays your newly created API key.
5. Click **Close**. The new API key is listed on the **Credentials** page under **API keys**. (Remember to [restrict the API](https://developers.google.com/maps/api-security-best-practices#restricting-api-keys) key before using it in production.)

**Note:** To continue it is necessary to complete the creation of an API key. If you had problems please visit [Set up your Google Cloud project](https://developers.google.com/maps/documentation/android-sdk/cloud-setup)

## Add the API key to your app
You should not check your API key into your version control system, so it is recommended
storing it in the `local.properties` file, which is located in the root directory of your project.
For more information about the `local.properties` file, see [Gradle properties files](https://developer.android.com/studio/build#properties-files).

1. Open the `local.properties` in your project level directory, and then add the following code. Replace `YOUR_API_KEY` with your API key.

```
MAPS_API_KEY=YOUR_API_KEY
```

2. Save the file.
3. In your `AndroidManifest.xml`file, under the `application` element add the `meta-data` element as follows:

```
<manifest ...>
   <application ...>
      ...
      
      <meta-data
         android:name="com.google.android.geo.API_KEY"
         android:value="${MAPS_API_KEY}" />
   </application>
</manifest>
```

**Important:** Before use add in the module-level `AndroidManifest.xml` the required permissions, for more information, see [Doc](https://developer.android.com/training/permissions/declaring)

```
<manifest ...>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <application ...>
        ...
    </application>
</manifest>
```

4. To read the value from the `local.properties` you can use [Secrets Gradle plugin for Android](https://github.com/google/secrets-gradle-plugin). To install the plugin and store your API key:

   - In Android Studio, open your project-level `build.gradle` file and add the following code to the `dependencies` element under `buildscript`.
   
   ```
      plugins {
          // ...
          id 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin' version '2.0.1' apply false
      }
   ```
   
   - Next, open your module-level `build.gradle` file and add the following code to the `plugins` element.

   ```
   id 'com.google.android.libraries.mapsplatform.secrets-gradle-plugin'
   ```

   - Save the file and [sync your project with Gradle](https://developer.android.com/studio/build#sync-files).

## Gradle configuration
To integrate the OMH Maps in your project is required to add some Gradle dependencies.

### Add OMH Core plugin
To add the core plugin dependency in a new project, follow the next steps:

1. In Android Studio, open your project-level `settings.gradle` file and add the following code to the `repositiories` element under `dependencyResolutionManagement`

```
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://s01.oss.sonatype.org/content/groups/staging/' }
    }
}
```

2. In the project-level `build.gradle` file at the top, add the following code:

```
buildscript {
   repositories {
      maven { url 'https://s01.oss.sonatype.org/content/groups/staging/' }
   }
   dependencies {
      classpath 'com.openmobilehub.android:omh-core:1.0'
   }
}
...
```

3. In the module-level `build.gradle` under the `plugin` element add the plugin id.

```
plugins {
   ...
   id 'com.openmobilehub.android.omh-core'
}
```

4. Save the file and [sync Project with Gradle Files](https://developer.android.com/studio/build#sync-files).

### Configure the OMH Core plugin
To use the core plugin is required some minimum configuration, for more details see [OMH Core Docs](https://github.com/openmobilehub/omh-core/tree/release/1.0).

1. In the module-level `build.gradle` file under the `buildFeatures` element add `buildConfig = true`. For more information see [BuildFeatures](https://developer.android.com/reference/tools/gradle-api/7.0/com/android/build/api/dsl/BuildFeatures)
If there is no `buildFeatures` element, add that too, see [No buildFeatures](#No buildFeatures).

#### With buildFeatures

```
android {
   ...
   buildFeatures {
      ...
      buildConfig = true
   }
}
```

#### Without buildFeatures

```
android {
   ...
   buildFeatures {
      buildConfig = true
   }
}
```

2. In the module-level `build.gradle` file is required to configure the `omhConfig`.
The `omhConfig` definition is used to extend the existing Android Studio variants in the core plugin.
For more details `omhConfig` see [OMH Core](https://github.com/openmobilehub/omh-core/tree/release/1.0).

#### Basic configuration
Define the `Bundle` that represents the build variants names. In this example are `singleBuild`, `gms` and `nongms`.

##### Variant singleBuild
- Define the `Service`. In this example is maps.
- Define the `ServiceDetails`. In this example are `gmsService` and `nonGmsService`.
- Define the dependency and the path. In this example are `com.openmobilehub.android:maps-api-googlemaps:1.0"` and `com.openmobilehub.android:maps-api-openstreetmap:1.0`.
**Note:** It's important to observe how a single build encompasses both GMS (Google Mobile Services) and Non-GMS configurations.

##### Variant gms
- Define the `Service`. In this example is maps.
- Define the `ServiceDetails` . In this example is `gmsService`.
- Define the dependency and the path. In this example is `com.openmobilehub.android:maps-api-googlemaps:1.0"`.
**Note:** gms build covers only GMS (Google Mobile Services).

##### Variant nongms
- Define the `Service`. In this example is maps.
- Define the `ServiceDetails` . In this example is `nonGmsService`.
- Define the dependency and the path. In this example is `com.openmobilehub.android:maps-api-openstreetmap:1.0`.
**Note:** nongms build covers only Non-GMS configurations.

In the module-level `build.gradle` file is, add the following code at the end of the file.

```
...
dependencies {
   ...
}

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
3. Save and [sync Project with Gradle Files](https://developer.android.com/studio/build#sync-files). 
4. Now you can select a build variant. To change the build variant Android Studio uses, do one of the following:
- Select "Build" > "Select Build Variant..." in the menu.
- Select "View" > "Tool Windows" > "Build Variants" in the menu.
- Click the "Build Variants" tab on the tool window bar.

5. You can select any of the 3 variants:
- "singleBuild" variant builds for GMS (Google Mobile Services) and Non-GMS devices without changes to the code.
- "gms" variant  builds for devices that has GMS (Google Mobile Services).
- "nongms" variant builds for devices that doesn't have GMS (Google Mobile Services).

6. Create an instance of the provider. The recommendation is to create a custom `Application` class to ensure that the provider is instantiated correctly.
To create an `Application` class do the following:
- In the module-level in the same package as the `Activity` right click and select "New" > "Kotlin Class/File".
- Select "class".
- Write any name for the class. In this example is `MainApplication`.
- Press Enter.

Add the required imports below the package name:

```kotlin
import android.app.Application
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.factories.OmhMapSDK
```

The class needs to extend `android.app.Application` as follows:

```kotlin
class MainApplication : Application() {
   
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

**Important:** If you encounter the error "Missing BuildConfig.MAPS_GMS_PATH and BuildConfig.MAPS_NON_GMS_PATH in BuildConfig class". Follow the next steps:
- [Sync Project with Gradle Files](https://developer.android.com/studio/build#sync-files). 
- Select "Build" from the menu at the top in Android Studio. 
- Click on "Clean Project" and await. 
- Click on "Rebuild Project" and await. 
- Select "Run" from the top navigation menu in Android Studio. 
- Click the first item "Run". 
- If still not working, select "File" from the menu at the top in Android Studio. 
- Click "Invalidate Caches...". 
- Click "Invalidate and Restart". If you click "Just restart", cache won't be deleted and the selected optional actions won't be applied. 
- Await until Android Studio restarts.

7. In the module-level `AndroidManifest.xml` file, specify the `android:name` property with the created class `MainApplication`.
Don't forget to replace the class name with the one created in your application. In this example is `MainApplication`.

```
...
<application
   ...
   android:name=".MainApplication">
...
```

## Add the map into your app
The main interfaces that you will be interacting with are called `OmhMap`, `OmhMapView` and `OmhLocation`.
It contains all your basic maps and location functions like displaying a marker, map gestures, getting current location and more.
Additionally a fragment `OmhMapFragment` is provided, this fragment manages the life cycle of the map.

### Map fragment
`OmhMapFragment` is the simplest way to place a map in an application.
Fragment has to declare `android:name` that sets the class name of the fragment to `OmhMapFragment`, which is the fragment type used in the maps activity file.
Being a fragment, this component can be added to an activity's layout file simply with the following XML layout:

```xml
<fragment
    android:id="@+id/fragment_map_container"
    android:name="com.omh.android.maps.api.presentation.fragments.OmhMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

And the complete activity's layout should look similar to:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <fragment
        android:id="@+id/fragment_map_container"
        android:name="com.omh.android.maps.api.presentation.fragments.OmhMapFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

Now just click the "Run" menu option (or the play button icon) to run your app and see the map.

### Display your current location
An `OmhMap` must be acquired using `getMapAsync(OmhOnMapReadyCallback)`. This class automatically initializes the maps system and the view.
Example to get `OmhMap` using the `OmhMapFragment`.

1. Implement the `OmhOnMapReadyCallback` interface and override the `onMapReady()` method, to set up the map when the `OmhMap` object is available:

```kotlin
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.omh.android.maps.api.factories.OmhMapProvider
import com.omh.android.maps.api.presentation.fragments.OmhMapFragment
import com.omh.android.maps.api.presentation.interfaces.location.OmhFailureListener
import com.omh.android.maps.api.presentation.interfaces.location.OmhSuccessListener
import com.omh.android.maps.api.presentation.interfaces.maps.OmhMap
import com.omh.android.maps.api.presentation.interfaces.maps.OmhOnMapReadyCallback
import com.omh.android.maps.api.presentation.models.OmhMarkerOptions

class MainActivity : AppCompatActivity(), OmhOnMapReadyCallback {

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

3. Click "Run" menu option (or the play button icon) to run your app and see the map with the device's location.

**Important:** For a better experience and accuracy try a phone with sim card.

4. Explore Advanced Features

   Congratulations on completing the getting started guide! Now, it's time to level up your mapping skills by diving into our advanced features. 

   Discover how to handle camera events, add markers, manage location data, work with gestures, utilize network utilities, and create custom Maps SDK implementations/plugins. These advanced capabilities    will elevate your mapping application to new heights.

   Visit our [Advanced Features wiki page](https://github.com/openmobilehub/omh-maps/wiki#ohm-map-sdk---advanced-features) to access detailed information, examples, and guides for each feature. Unleash    the full power of our mapping SDK and create exceptional mapping experiences.

   Take the next step and explore our Advanced Features now!
#### Sample App
Advanced Sample app demonstrates how to use Omh Maps SDK functionalities, [sample](/omh-maps/tree/develop/maps-sample).

## Documentation

See example and check the full documentation and add custom implementation at our [Wiki](https://github.com/openmobilehub/omh-maps/wiki).

Additionally for more information about the OMH Map functions, [Docs](https://openmobilehub.github.io/omh-maps).

## Contributing

We'd be glad if you decide to contribute to this project.

All pull request is welcome, just make sure that every work is linked to an issue on this repository so everyone can track it.
For more information check [CONTRIBUTING](https://github.com/openmobilehub/omh-maps/blob/release/1.0/CONTRIBUTING.md).

**Disclaimer:** This client library is currently undergoing development, and there may be future modifications to specific implementation details.
