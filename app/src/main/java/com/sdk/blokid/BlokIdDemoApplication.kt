package com.sdk.blokid

import android.app.Application
import com.sdk.blokid.sdk_manager.EventType
import com.sdk.blokid.sdk_manager.SDKManager

class BlokIdDemoApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        val sdkManager = SDKManager()
        sdkManager.initialize(this,"BLOKID-PIXEL-MOBILE")
        sdkManager.trackEvent(EventType.Scroll)

    }
}