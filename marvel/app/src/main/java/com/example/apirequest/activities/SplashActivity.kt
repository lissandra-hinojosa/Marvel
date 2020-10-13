package com.example.apirequest.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.apirequest.R
import com.example.apirequest.viewmodels.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity:AppCompatActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private val splashDuration = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler().postDelayed({
                route()
                finish()},
            splashDuration
        )
    }

    private fun route(){
        if(loginViewModel.isActiveSession()) intentMarvel()
        else intentLogIn()
    }

    private fun intentMarvel(){
        val action = Intent(this,MainActivity::class.java)
        startActivity(action)
    }
//    In case the need of redirecting to another activity after splash
    private fun intentLogIn() {
        val action = Intent(this,LogInActivity::class.java)
        startActivity(action)
    }
}