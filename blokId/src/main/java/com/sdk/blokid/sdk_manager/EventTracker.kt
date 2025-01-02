package com.sdk.blokid.sdk_manager


import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.sdk.blokid.network.ApiCallback
import com.sdk.blokid.network.ApiClient

class EventTracker(private val context: Context) {

    fun trackAppInstall(siteIdentifier: String) {
        val isInstalled = context.getSharedPreferences("YourSDKPrefs", Context.MODE_PRIVATE)
            .getBoolean("is_installed", false)
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.FirstVisit)
            put("site_identifier",siteIdentifier)
        }
        if (!isInstalled) {
            ApiClient.sendEvent(properties, context,object : ApiCallback {
                override fun onSuccess(response: JsonObject) {
                    context.getSharedPreferences("YourSDKPrefs", Context.MODE_PRIVATE).edit()
                        .putBoolean("is_installed", true).apply()
                }

                override fun onError(errorMessage: String) {
                }

            })

        }
    }



    fun trackEvent(properties: HashMap<String, Any>) {
        ApiClient.sendEvent(properties, context,object : ApiCallback {
            override fun onSuccess(response: JsonObject) {
                Log.e("onSuccess",response.toString())
            }

            override fun onError(errorMessage: String) {
                Log.e("onError",errorMessage.toString())
            }

        })
    }
}
