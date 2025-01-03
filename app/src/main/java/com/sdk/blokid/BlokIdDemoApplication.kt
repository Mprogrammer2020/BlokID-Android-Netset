package com.sdk.blokid

import android.app.Application
import com.sdk.blokid.sdk_manager.EventType
import com.sdk.blokid.sdk_manager.BlokSDKManager

class BlokIdDemoApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        val sdkManager = BlokSDKManager()
        sdkManager.initialize(this,"BLOKID-PIXEL-MOBILE")
        sdkManager.trackEvent(EventType.Scroll)

    }
}