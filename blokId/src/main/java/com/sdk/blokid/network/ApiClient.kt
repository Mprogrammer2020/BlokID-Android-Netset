package com.sdk.blokid.network


import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.sdk.blokid.network_call.PixelApiCallServices
import com.sdk.blokid.network_call.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ApiClient {
    fun sendEvent(
        properties: HashMap<String, Any>,
        context: Context,
        callback: ApiCallback
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response= RetrofitHelper.getClient(context)
                    .create(PixelApiCallServices::class.java)
                    .pixelEventTracker(properties)

                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    } ?: run {
                        callback.onError("Empty response body")
                    }
                } else {
                    callback.onError("Error: ${response.code()} - ${response.message()}")
                }
            } catch (exception: Exception) {
                callback.onError("Exception: ${exception.message}")
            }
        }
        Log.d("ApiClient", "Sending with properties: $properties")
    }
}
interface ApiCallback {
    fun onSuccess(response: JsonObject){
        Log.e("onSuccess",response.toString())
    }
    fun onError(errorMessage: String){
        Log.e("onError",errorMessage.toString())
    }
}