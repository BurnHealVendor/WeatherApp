package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.networkModule
import com.example.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}