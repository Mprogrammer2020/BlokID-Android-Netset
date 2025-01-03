package com.sdk.blokid.sdk_manager


import android.content.Context

class BlokSDKManager {
    private lateinit var context: Context
    private lateinit var sessionManager: SessionManager
    private lateinit var eventTracker: EventTracker
    private lateinit var siteIdentifier: String

    fun initialize(appContext: Context,siteIdentifier:String) {
        context = appContext.applicationContext
        this.siteIdentifier = siteIdentifier
        sessionManager = SessionManager(context)
        eventTracker = EventTracker(context)
        eventTracker.trackAppInstall(siteIdentifier)
    }

    fun trackEvent(eventType: EventType) {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", eventType.eventName)
            put("siteIdentifier",siteIdentifier)
        }

        eventTracker.trackEvent(properties)
    }

    fun trackFirstVisitEvent() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.FirstVisit)
            put("siteIdentifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackTabSwitch() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.TabSwitch)
            put("siteIdentifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackClick() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.Click)
            put("siteIdentifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackScroll() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.Scroll)
            put("siteIdentifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackPageLoad() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.PageLoad)
            put("siteIdentifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackPageUnLoad() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.PageUnload)
            put("siteIdentifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackStartSession() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.SessionStart)
            put("siteIdentifier",siteIdentifier)
        }
        sessionManager.startSession(properties)
    }
}
