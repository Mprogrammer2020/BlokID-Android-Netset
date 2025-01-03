package com.sdk.blokid

import android.app.Application
import com.sdk.blokid.sdk_manager.EventType
import com.sdk.blokid.sdk_manager.BlokSDKManager

class BlokIdDemoApplication:Application() {
    companion object{
        var sdkManager: BlokSDKManager? = null

    }
    override fun onCreate() {
        super.onCreate()
         sdkManager = BlokSDKManager()
        sdkManager?.initialize(this,"BLOKID-PIXEL-MOBILE")

    }
}