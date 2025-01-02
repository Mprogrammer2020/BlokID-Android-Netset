package com.sdk.blokid.sdk_manager

enum class EventType(val eventName: String) {
    PageLoad("PageLoad"),
    PageUnload("PageUnload"),
    TabSwitch("TabSwitch"),
    FirstVisit("FirstVisit"),
    SessionStart("SessionStart"),
    Click("Click"),
    Scroll("Scroll");

    override fun toString(): String {
        return eventName
    }
}
