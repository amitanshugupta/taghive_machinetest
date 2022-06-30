package com.taghive.utils

import android.app.Application
import android.os.StrictMode

class TagHiveApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

    }
}