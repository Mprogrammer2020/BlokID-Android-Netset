package com.sdk.blokid.sdk_manager


import android.app.Activity
import android.content.Context
import android.util.Log
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.sdk.AppLovinSdk
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsShowOptions

class BlokIDSDKManager {
    private lateinit var context: Context
    private lateinit var sessionManager: SessionManager
    private lateinit var eventTracker: EventTracker
    private lateinit var siteIdentifier: String

    // Unity Ads Variables
    private var unityGameID: String = "5778541"
    private var unityAdUnitId: String = "Interstitial_Android"
    private var unityTestMode: Boolean = true

    private var appLovinAdUnitId: String = "your_applovin_ad_unit_id"
    private var appLovinInterstitialAd: MaxInterstitialAd? = null


    fun initializeBlokIDSDK(appContext: Context, siteIdentifier: String) {
        context = appContext.applicationContext
        this.siteIdentifier = siteIdentifier
        sessionManager = SessionManager(context)
        eventTracker = EventTracker(context)
        eventTracker.trackAppInstall(siteIdentifier)
    }

    // Initialize Unity Ads
    fun initializeUnityAds(gameID: String, adUnitId: String, testMode: Boolean) {
        unityGameID = gameID
        unityAdUnitId = adUnitId
        unityTestMode = testMode

        UnityAds.initialize(context, unityGameID, unityTestMode, object :
            IUnityAdsInitializationListener {
            override fun onInitializationComplete() {
                Log.d("AdSDKManager", "Unity Ads initialized successfully.")
            }

            override fun onInitializationFailed(
                error: UnityAds.UnityAdsInitializationError?,
                message: String?
            ) {
                Log.e("AdSDKManager", "Unity Ads initialization failed: [${error?.name}] $message")
            }
        })
    }

    // Show Unity Ad
    fun showUnityAd(activity: Activity) {
        UnityAds.load(unityAdUnitId, object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String) {
                UnityAds.show(activity, unityAdUnitId, UnityAdsShowOptions(), object :
                    IUnityAdsShowListener {
                    override fun onUnityAdsShowComplete(
                        placementId: String,
                        state: UnityAds.UnityAdsShowCompletionState?
                    ) {
                        Log.e("AdSDKManager", "Unity Ad completed for placement: $placementId")
                        trackEventBlokID(EventType.UnityAdCompleted)
                    }

                    override fun onUnityAdsShowFailure(
                        placementId: String,
                        error: UnityAds.UnityAdsShowError?,
                        message: String?
                    ) {
                        Log.e("AdSDKManager", "Unity Ad failed to show: [${error?.name}] $message")
                    }

                    override fun onUnityAdsShowStart(placementId: String) {
                        trackEventBlokID(EventType.UnityAdShow)
                        Log.e("AdSDKManager", "Unity Ad started for placement: $placementId")
                    }

                    override fun onUnityAdsShowClick(placementId: String) {
                        trackEventBlokID(EventType.UnityAdClick)
                        Log.e("AdSDKManager", "Unity Ad clicked for placement: $placementId")
                    }
                })
            }

            override fun onUnityAdsFailedToLoad(
                placementId: String,
                error: UnityAds.UnityAdsLoadError?,
                message: String?
            ) {
                Log.e("AdSDKManager", "Unity Ad failed to load: [${error?.name}] $message")
            }
        })
    }


    fun initializeAppLovinAds(){
        // Initialize AppLovin
        AppLovinSdk.getInstance(context).mediationProvider = "max"
        AppLovinSdk.getInstance(context).initializeSdk {
            Log.d("AdSDKManager", "AppLovin SDK initialized successfully.")
            loadAppLovinAd("YOUR_AD_UNIT_ID")

        }
    }

    // Load AppLovin Ad
    fun loadAppLovinAd(adUnitId: String) {
        appLovinAdUnitId = adUnitId
        appLovinInterstitialAd = MaxInterstitialAd(appLovinAdUnitId, context)
        appLovinInterstitialAd?.setListener(object : MaxAdListener {
            override fun onAdLoaded(ad: MaxAd) {
                Log.d("AdSDKManager", "AppLovin Ad loaded successfully.")
            }

            override fun onAdDisplayed(ad: MaxAd) {
                trackEventBlokID(EventType.AppLovinAdShow)
                Log.d("AdSDKManager", "AppLovin Ad displayed.")
            }

            override fun onAdHidden(ad: MaxAd) {
                trackEventBlokID(EventType.AppLovinAdCompleted)
                Log.d("AdSDKManager", "AppLovin Ad completed.")
                appLovinInterstitialAd?.loadAd() // Reload for next time
            }

            override fun onAdClicked(ad: MaxAd) {
                trackEventBlokID(EventType.AppLovinAdClick)
                Log.d("AdSDKManager", "AppLovin Ad clicked.")
            }

            override fun onAdLoadFailed(p0: String, p1: MaxError) {
                Log.d("AdSDKManager", "${p1.code} ${p1.message}")
            }

            override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
                Log.d("AdSDKManager", "onAdLoadFailed")
            }

        })

        appLovinInterstitialAd?.loadAd()
    }

    // Show AppLovin Ad
    fun showAppLovinAd(activity: Activity) {
        if (appLovinInterstitialAd?.isReady == true) {
            appLovinInterstitialAd?.showAd()
        } else {
            Log.e("AdSDKManager", "AppLovin Ad not ready yet.")
        }
    }

    //In-App event tracking
    fun trackEventBlokID(eventType: EventType) {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", eventType.eventName)
            put("siteIdentifier", siteIdentifier)
        }

        eventTracker.trackEvent(properties)
    }

    fun trackFirstVisitEventBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.FirstVisit)
            put("siteIdentifier", siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackTabSwitchBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.TabSwitch)
            put("siteIdentifier", siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackClickBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.Click)
            put("siteIdentifier", siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackScrollBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.Scroll)
            put("siteIdentifier", siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackPageLoadBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.PageLoad)
            put("siteIdentifier", siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackPageUnLoadBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.PageUnload)
            put("siteIdentifier", siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackStartSessionBlokID() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.SessionStart)
            put("siteIdentifier", siteIdentifier)
        }
        sessionManager.startSession(properties)
    }
}
