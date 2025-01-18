package com.sdk.blokid

import android.app.Application
import com.sdk.blokid.sdk_manager.BlokIDSDKManager

class BlokIdDemoApplication:Application() {
    companion object{
        var blokSDKManager: BlokIDSDKManager? = null

    }
    override fun onCreate() {
        super.onCreate()
         blokSDKManager = BlokIDSDKManager()
        blokSDKManager?.initializeBlokIDSDK(this,"BLOKID-PIXEL-MOBILE")
        blokSDKManager?.initializeUnityAds("5778541","Interstitial_Android",true)

    }
}