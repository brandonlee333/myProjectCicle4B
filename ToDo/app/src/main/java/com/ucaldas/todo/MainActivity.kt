package com.ucaldas.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var varAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.myToolBar))

        val fab: View = findViewById(R.id.myFloatingActionButton)
        fab.setOnClickListener{view ->
        /// add nue task
        }

        val varDrawerLayout:DrawerLayout = findViewById(R.id.myDrawer_layout)
        val varNavView:NavigationView = findViewById(R.id.myNavigationView)

        val varNavHostFragment= supportFragmentManager.findFragmentById(R.id.my_NavHostFragmentContainerView) as NavHostFragment
        val varNavController= varNavHostFragment.navController

        varAppBarConfiguration= AppBarConfiguration(setOf(R.id.nav_intro,R.id.nav_todo,R.id.nav_about),varDrawerLayout)
        setupActionBarWithNavController(varNavController, varAppBarConfiguration)
        varNavView.setupWithNavController(varNavController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val varNavHostFragment=supportFragmentManager.findFragmentById(R.id.my_NavHostFragmentContainerView)as NavHostFragment
        val varNavController=varNavHostFragment.navController
        return varNavController.navigateUp(varAppBarConfiguration) || super.onSupportNavigateUp()
    }
}