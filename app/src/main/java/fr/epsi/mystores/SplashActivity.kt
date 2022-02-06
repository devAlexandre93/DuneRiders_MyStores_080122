package fr.epsi.mystores

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("account", Context.MODE_PRIVATE)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if (sharedPreferences.contains("name")) {
                val newIntent = Intent(application, MainActivity::class.java)
                startActivity(newIntent)
            } else {
                val newIntent = Intent(application, CreateAccountActivity::class.java)
                startActivity(newIntent)
            }
            finish()
        }, 2000)
    }
}