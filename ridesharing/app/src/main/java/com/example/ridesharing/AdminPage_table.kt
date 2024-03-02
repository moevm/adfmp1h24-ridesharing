package com.example.ridesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ridesharing.fragments.Statistics
import com.example.ridesharing.fragments.Cars
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminPage_table : AppCompatActivity() {
    private val stats = Statistics()
    private val cars = Cars()

    private var bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_page_table)
        change_fragment(stats)

        bottom_navigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.analytics_page -> change_fragment(stats)
                R.id.cars_page -> change_fragment(cars)
            }
            true
        }
    }

    private fun change_fragment(fragment: Fragment){
        if (fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}