package com.sdk.blokid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.sdk.blokid.BlokIdDemoApplication.Companion.blokSDKManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<MaterialButton>(R.id.firstVisit).setOnClickListener {
            blokSDKManager?.trackFirstVisitEventBlokID()

        }
        findViewById<MaterialButton>(R.id.scrollDetails).setOnClickListener {
            blokSDKManager?.trackScrollBlokID()
        }
        findViewById<MaterialButton>(R.id.pageLoad).setOnClickListener {
            blokSDKManager?.trackPageLoadBlokID()
        }
        findViewById<MaterialButton>(R.id.pageUnload).setOnClickListener {
            blokSDKManager?.trackPageUnLoadBlokID()
        }
        findViewById<MaterialButton>(R.id.scrollDetails).setOnClickListener {
            blokSDKManager?.trackScrollBlokID()
        }
        findViewById<MaterialButton>(R.id.tabSwitch).setOnClickListener {
            blokSDKManager?.trackTabSwitchBlokID()

        }
        findViewById<MaterialButton>(R.id.click).setOnClickListener {
            blokSDKManager?.trackClickBlokID()

        }
        findViewById<MaterialButton>(R.id.showUnityAd).setOnClickListener {
            blokSDKManager?.trackClickBlokID()
            blokSDKManager?.showUnityAd(this)

        }
        findViewById<MaterialButton>(R.id.showAppLovinAd).setOnClickListener {
            blokSDKManager?.showAppLovinAd(this)
            blokSDKManager?.trackClickBlokID()

        }
        findViewById<MaterialButton>(R.id.liveDemo).setOnClickListener {
            blokSDKManager?.trackClickBlokID()
          startActivity(  Intent(this,DemoActivity::class.java))

        }
    }
}