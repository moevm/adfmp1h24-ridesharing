package com.example.ridesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ridesharing.fragments.Statistics
import com.example.ridesharing.fragments.Cars
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class AdminPage_table : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_page_table)

        val bottomNav = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, Statistics()).commit()
    }

    private val navListener = NavigationBarView.OnItemSelectedListener {
        lateinit var selectedFragment: Fragment
        when (it.itemId) {
            R.id.analytics_page -> {
                selectedFragment = Statistics()
            }
            R.id.cars_page -> {
                selectedFragment = Cars()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        true
    }
}