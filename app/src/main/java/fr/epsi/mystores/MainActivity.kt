package fr.epsi.mystores

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.content.SharedPreferences

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtViewName = findViewById<TextView>(R.id.txt_name_home)
        txtViewName.text = readSharedPref("name")
    }

    fun readSharedPref(key: String): String {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "").toString()
    }
}