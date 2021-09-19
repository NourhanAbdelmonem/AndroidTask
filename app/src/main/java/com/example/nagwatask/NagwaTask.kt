package com.example.nagwatask

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NagwaTask : Application() {


    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    fun context() = context

}