package com.volodymyrmalynovskyi.simple_kotlin_application.db

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppContext: Application(){
    init {
        instance = this
    }

    companion object{
        private var instance: AppContext? = null

        fun getContext(): Context{
            return instance!!.applicationContext
        }
    }
}