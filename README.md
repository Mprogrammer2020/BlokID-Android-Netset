# BlokID-Android-Netset

# BlokID Android SDK

BlokID Android SDK allows developers to track events and collect user interaction data seamlessly. This document outlines the setup and usage instructions for integrating the SDK into your Android application.

## Installation

To integrate the BlokID SDK into your Android project:

### Step 1: Add JitPack Repository
Add the JitPack repository to your root `build.gradle` file:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the SDK Dependency
Add the BlokID dependency to your module's `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.Mprogrammer2020:BlokID-Android-Netset:1.0.0'
}
```

## Usage

### Initializing the SDK
To use the SDK, initialize it in your application or activity:

```kotlin
val sdkManager = SDKManager()
sdkManager.initialize(context, "BLOKID-PIXEL-MOBILE")
```
- **`context`**: Provide the application or activity context.
- **`BLOKID-PIXEL-MOBILE`**: Replace this with your site identifier.

### Tracking Events
Use the SDK to track predefined events or custom events:

#### Track Predefined Events
```kotlin
// Track a TabSwitch event
sdkManager.trackTabSwitch()

// Track a Scroll event
sdkManager.trackEvent(EventType.Scroll)
```

#### Track Custom Events
To track custom events with additional data:

```kotlin
val additionalData = hashMapOf("customKey" to "customValue")
sdkManager.trackEvent(EventType.Click, additionalData)
```

### Supported Event Types
The SDK supports the following event types:

```kotlin
enum class EventType(val eventName: String) {
    PageLoad("PageLoad"),
    PageUnload("PageUnload"),
    TabSwitch("TabSwitch"),
    FirstVisit("FirstVisit"),
    SessionStart("SessionStart"),
    Click("Click"),
    Scroll("Scroll");
}
```

### Dynamic Data Collection
The SDK automatically collects the following data dynamically:
- User Agent
- Screen Width and Height
- Device Model
- Platform (Android)
- Timestamp
- Event Type

You can also add custom data using the `additionalData` parameter.

## Example Usage

Here is a complete example:

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val blokSdkManager = BlokSDKManager()
        blokSdkManager.initialize(this, "BLOKID-PIXEL-MOBILE")

        // Track predefined events
        blokSdkManager.trackTabSwitch()
        blokSdkManager.trackEvent(EventType.Scroll)

        // Track a custom event with additional data
        val additionalData = hashMapOf("customKey" to "customValue")
        blokSdkManager.trackEvent(EventType.Click, additionalData)
    }
}
```

## Logging
The SDK logs events to help with debugging. You can view logs using `Logcat` in Android Studio.

## Support
For further assistance or to report issues, please contact our support team.

