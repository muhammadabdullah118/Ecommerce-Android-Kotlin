package com.example.ecomerce.application

import android.app.Application
import com.example.ecomerce.utils.Prefrences

class PrefrenceApplication( ) : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefrences(this)
    }

}