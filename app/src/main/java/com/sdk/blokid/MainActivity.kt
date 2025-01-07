package com.sdk.blokid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.sdk.blokid.BlokIdDemoApplication.Companion.sdkManager


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
            sdkManager?.trackFirstVisitEvent()

        }
        findViewById<MaterialButton>(R.id.scrollDetails).setOnClickListener {
            sdkManager?.trackScroll()
        }
        findViewById<MaterialButton>(R.id.pageLoad).setOnClickListener {
            sdkManager?.trackPageLoad()
        }
        findViewById<MaterialButton>(R.id.pageUnload).setOnClickListener {
            sdkManager?.trackPageUnLoad()
        }
        findViewById<MaterialButton>(R.id.scrollDetails).setOnClickListener {
            sdkManager?.trackScroll()
        }
        findViewById<MaterialButton>(R.id.tabSwitch).setOnClickListener {
            sdkManager?.trackTabSwitch()

        }
        findViewById<MaterialButton>(R.id.click).setOnClickListener {
            sdkManager?.trackTabSwitch()

        }
        findViewById<MaterialButton>(R.id.liveDemo).setOnClickListener {
          startActivity(  Intent(this,DemoActivity::class.java))

        }
    }
}