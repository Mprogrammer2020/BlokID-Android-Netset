package com.sdk.blokid.sdk_manager

import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.provider.Settings
import android.webkit.CookieManager
import android.webkit.WebSettings
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.UUID

object SDKUtils {
    fun getDeviceProperties(context: Context): HashMap<String, Any> {
        val properties = HashMap<String, Any>()

        // Screen Width and Height
        val displayMetrics = Resources.getSystem().displayMetrics
        properties["screenWidth"] = displayMetrics.widthPixels
        properties["screenHeight"] = displayMetrics.heightPixels

        // Device Memory
        val memoryInfo = ActivityManager.MemoryInfo()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(memoryInfo)
        properties["deviceMemory"] = memoryInfo.totalMem / (1024 * 1024 * 1024) // In GB

        // Language
        properties["language"] = Locale.getDefault().language

        // Platform
        properties["platform"] = "android"

        // User Agent
        val userAgent = WebSettings.getDefaultUserAgent(context)
        properties["userAgent"] = userAgent

        // Canvas Fingerprint (using UUID for simplicity)
//        properties["canvasFingerprint"] = UUID.randomUUID().toString()
        properties["canvasFingerprint"] = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

        // Cookies Enabled
        properties["cookiesEnabled"] = CookieManager.getInstance().acceptCookie()

        // Local Storage (assuming it's enabled)
        properties["localStorage"] = true

        // Plugins (if applicable)
        properties["plugins"] = listOf(
            "PDF Viewer",
            "Chrome PDF Viewer",
            "Chromium PDF Viewer",
            "Microsoft Edge PDF Viewer",
            "WebKit built-in PDF"
        ) // Example list of plugins

        // Mime Types
        properties["mimeTypes"] = listOf("application/pdf", "text/pdf") // Example mime types

        // Color Depth and Pixel Depth
        properties["colorDepth"] = displayMetrics.densityDpi
        properties["pixelDepth"] = displayMetrics.densityDpi

        // Timezone Offset
        properties["timezoneOffset"] = TimeZone.getDefault().rawOffset / 60000 // In minutes

        // Touch Support
        properties["touchSupport"] = if (context.resources.configuration.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH) 0 else 1

        // Visibility State
        properties["visibilityState"] = "visible" // This would need to be dynamically updated

        // Previous URL (track this dynamically within your app's navigation logic)
        properties["previousUrl"] = ""
        properties["currentUrl"] = ""

        // Timestamp
        properties["timestamp"] = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.format(Date())

        // Event
//        properties["event"] = "TabSwitch" // Example event, update dynamically as needed

        return properties
    }
}