package com.example.forboost.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.signin.api.domain.usecase.IsUserAuthorizedUseCase
import com.example.forboost.ForBoostApp
import com.example.forboost.R
import com.example.forboost.navigation.AppComponentRunner
import com.example.forboost.navigation.Navigator
import com.example.uploadpost.impl.service.UploadPostService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AppComponentRunner {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var isUserAuthorizedUseCase: IsUserAuthorizedUseCase

    private val fragmentContainerId = R.id.fv_root

    private lateinit var navController: NavController

    private var isUserAuthorized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ForBoostApp).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        isUserAuthorized = isUserAuthorizedUseCase.invoke()
        initNavigation()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
    }

    override fun runUploadPostService() {
        val mIntent = Intent(this, UploadPostService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(mIntent)
        } else {
            startService(mIntent)
        }
    }

    private fun initNavigation() {
        navigator.setAppComponentRunner(this)
        val navHostFragment =
            supportFragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_main)
        val graph = navController.navInflater.inflate(R.navigation.root_nav_graph)

        graph.setStartDestination(if (isUserAuthorized) R.id.feedFragment else R.id.signInFragment)
        navController.setGraph(graph, intent.extras)

        navigator.attachNavController(navController)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility = if (
                destination.id in listOf(
                    R.id.postDetailsFragment,
                    R.id.commentRepliesFragment,
                    R.id.signInFragment,
                    R.id.savePostFragment
                )
            ) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.removeAppComponentRunner()
        navigator.detachNavController(navController)
    }
}
