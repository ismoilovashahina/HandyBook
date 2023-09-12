package com.example.handybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.handybook.intro.SplashFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_window, SplashFragment()).commit()

    }
}