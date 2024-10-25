package com.example.forboost.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.forboost.ForBoostApp
import com.example.forboost.R
import com.example.forboost.navigation.Navigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    private val fragmentContainerId = R.id.fv_root

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as ForBoostApp).appComponent.inject(this)

        setContentView(R.layout.activity_main)

        initNavigation()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                ),
                0
            )
        }
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment

        navController = navHostFragment.navController.apply {
            navigator.attachNavController(this)

            findViewById<BottomNavigationView>(R.id.bnv_main).setupWithNavController(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        navController?.let { navigator.detachNavController(it) }
        navController = null
    }
}