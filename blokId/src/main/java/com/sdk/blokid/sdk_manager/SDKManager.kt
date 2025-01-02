package com.sdk.blokid.sdk_manager


import android.content.Context

class SDKManager {
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
            put("site_identifier",siteIdentifier)
        }

        eventTracker.trackEvent(properties)
    }

    fun trackFirstVisitEvent() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.FirstVisit)
            put("site_identifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackTabSwitch() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.TabSwitch)
            put("site_identifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackClick() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.Click)
            put("site_identifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackScroll() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.Scroll)
            put("site_identifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackPageLoad() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.PageLoad)
            put("site_identifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackPageUnLoad() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.PageUnload)
            put("site_identifier",siteIdentifier)
        }
        eventTracker.trackEvent(properties)
    }

    fun trackStartSession() {
        val properties = SDKUtils.getDeviceProperties(context).apply {
            put("event", EventType.SessionStart)
            put("site_identifier",siteIdentifier)
        }
        sessionManager.startSession(properties)
    }
}
