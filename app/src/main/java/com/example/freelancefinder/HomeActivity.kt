package com.example.freelancefinder

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FreelancerAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        applyCustomFontToNavigationView()

        val findWorkButton: MaterialButton = findViewById(R.id.find_work)

        val menuIcon: ImageView = findViewById(R.id.menu_icon)

        // Dummy data for freelancers
        val freelancers = listOf(
            Freelancer("John Doe", "Web Developer", "https://img.freepik.com/premium-vector/businessman-profile-cartoon_18591-58479.jpg"),
            Freelancer("Jane Smith", "Graphic Designer", "https://img.freepik.com/premium-vector/businesswoman-avatar-cartoon-character-profile_18591-50377.jpg")
            // Add more freelancers as needed
        )

        adapter = FreelancerAdapter(freelancers)
        recyclerView.adapter = adapter


        // Set up the Spinner
        val categoriesSpinner: Spinner = findViewById(R.id.categories_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categoriesSpinner.adapter = adapter
        }

        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        findWorkButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handles home click
                }
                R.id.nav_profile -> {
                    // Handles profile click
                }
                R.id.nav_settings -> {
                    // Handles settings click
                }
                R.id.nav_logout -> {
                    // Handles logout click
                }
            }
            drawerLayout.closeDrawer(Gravity.LEFT)
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun applyCustomFontToNavigationView() {
        val typeface = resources.getFont(R.font.poppins)
        navigationView.menu.forEach { menuItem ->
            val spanString = SpannableString(menuItem.title)
            spanString.setSpan(
                CustomTypefaceSpan("", typeface),
                0,
                spanString.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            menuItem.title = spanString
        }
    }

    fun openDrawer(view: View) {
        drawerLayout.openDrawer(Gravity.LEFT)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(Gravity.LEFT)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT)
        } else {
            super.onBackPressed()
        }
    }
}
