package com.example.apirequest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.apirequest.R
import com.example.apirequest.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private lateinit var navHost: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHost = supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment

        init()
    }

    private fun init() {
        loginViewModel.getUserSession()
        bottomNavigationView.setupWithNavController(navHost.navController)
        bottomNavVisibility()
//        val user: UserEntity = intent.getSerializableExtra("user") as UserEntity
    }


    private fun bottomNavVisibility() {
        navHost.navController.addOnDestinationChangedListener{ controller, destination, arguments ->
            when(controller.currentDestination?.id){
                R.id.profileFragment,
                R.id.characterListFragment
                -> bottomNavigationView.visibility = View.VISIBLE
                else -> bottomNavigationView.visibility = View.GONE
            }
        }
    }



}

