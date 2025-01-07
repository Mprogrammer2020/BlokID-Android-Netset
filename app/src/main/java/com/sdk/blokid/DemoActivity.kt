package com.sdk.blokid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.sdk.blokid.BlokIdDemoApplication.Companion.sdkManager

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<MaterialButton>(com.sdk.blokid.R.id.clickDemo).setOnClickListener {
            sdkManager?.trackClick()

        }
        val tabLayout: TabLayout = findViewById(R.id.tabLaoyt)

        // Set up a TabSelectedListener to listen for tab switches
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Called when a tab is selected
                when (tab?.position) {
                    0 -> {
                      sdkManager?.trackTabSwitch()
                        println("Home Tab Selected")
                    }

                    1 -> {
                        sdkManager?.trackTabSwitch()
                        // Second tab selected (Catalogue)
                        println("Catalogue Tab Selected")
                    }

                    2 -> {
                        sdkManager?.trackTabSwitch()
                        // Third tab selected (Profile)
                        println("Profile Tab Selected")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
// Static List Data with 30 Random US Names
        val dataList = listOf(
            "John Smith", "Emily Johnson", "Michael Williams", "Sophia Brown", "James Jones",
            "Olivia Garcia", "Robert Miller", "Isabella Martinez", "William Davis", "Mia Rodriguez",
            "David Wilson", "Charlotte Moore", "Joseph Taylor", "Amelia Anderson", "Thomas Thomas",
            "Emma Harris", "Daniel Martin", "Ava Jackson", "Matthew Lee", "Abigail White",
            "Anthony Thompson", "Harper Lewis", "Andrew Walker", "Ella Young", "Christopher Hall",
            "Elizabeth Allen", "Joshua Hernandez", "Scarlett King", "Ethan Wright", "Grace Scott"
        )


        // Set up RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter(dataList)

        // Add Scroll Listener to RecyclerView
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // Scrolling stopped
                    Log.d("ScrollTracking", "Scrolling stopped. Sending data to pixel server...")
                    sdkManager?.trackScroll()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Track continuous scrolling if needed
                Log.d("ScrollTracking", "RecyclerView is scrolling...")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        sdkManager?.trackPageLoad()
    }

    override fun onPause() {
        super.onPause()
        sdkManager?.trackPageUnLoad()
    }
}