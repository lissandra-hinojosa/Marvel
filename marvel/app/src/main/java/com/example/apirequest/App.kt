package com.example.apirequest

import android.app.Application
import com.example.apirequest.di.*
import com.google.firebase.iid.FirebaseInstanceId
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules,serviceModule, repositoryModule, databaseModule, interactorModule)
        }
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
                if(it.isSuccessful){
                    println(it.result?.token?:"Not found")
                }
            }
    }
}