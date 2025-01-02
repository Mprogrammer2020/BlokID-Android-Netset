package com.sdk.blokid.sdk_manager


import android.content.Context
import com.google.gson.JsonObject
import com.sdk.blokid.network.ApiCallback
import com.sdk.blokid.network.ApiClient

class SessionManager(private val context: Context) {

    fun startSession(hashMap: HashMap<String,Any>) {
        ApiClient.sendEvent(hashMap ,context,object : ApiCallback {
            override fun onSuccess(response: JsonObject) {

            }

            override fun onError(errorMessage: String) {
            }

        })
    }

    fun endSession(hashMap: HashMap<String,Any>) {
        ApiClient.sendEvent( hashMap,context,object : ApiCallback {
            override fun onSuccess(response: JsonObject) {

            }

            override fun onError(errorMessage: String) {
            }

        })
    }
}
