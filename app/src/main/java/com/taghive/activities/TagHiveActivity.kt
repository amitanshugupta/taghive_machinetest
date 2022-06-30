package com.taghive.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.taghive.R

class TagHiveActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            HomeActivity.open(this)
            finish()
        }, 3000)
    }
}