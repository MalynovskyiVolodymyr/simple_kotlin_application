package com.volodymyrmalynovskyi.simple_kotlin_application.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.volodymyrmalynovskyi.simple_kotlin_application.R

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_splash_screen)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            this@SplashScreenActivity.startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}