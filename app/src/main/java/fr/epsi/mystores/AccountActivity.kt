package fr.epsi.mystores

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class AccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setHeaderTitle("Votre compte")
        showBack()
        val editTxtLast = findViewById<EditText>(R.id.editTextLastNameDetails)
        val editTxtFirst = findViewById<EditText>(R.id.editTextFirstNameDetails)
        val editTxtEmail = findViewById<EditText>(R.id.editTextEmailDetails)
        val editTxtAddress = findViewById<EditText>(R.id.editTextAddressDetails)
        val editTxtZip = findViewById<EditText>(R.id.editTextZipcodeDetails)
        val editTxtCity = findViewById<EditText>(R.id.editTextCityDetails)
        val btnSave = findViewById<Button>(R.id.buttonEditForm)

        editTxtLast.setText(readSharedPref("lastName"))
        editTxtFirst.setText(readSharedPref("firstName"))
        editTxtEmail.setText(readSharedPref("email"))
        editTxtAddress.setText(readSharedPref("address"))
        editTxtZip.setText(readSharedPref("zipcode"))
        editTxtCity.setText(readSharedPref("city"))

        btnSave.setOnClickListener(View.OnClickListener {
            writeSharedPref("lastName",editTxtLast.text.toString())
            writeSharedPref("firstName",editTxtFirst.text.toString())
            writeSharedPref("name",editTxtLast.text.toString() + " " + editTxtFirst.text.toString())
            writeSharedPref("email",editTxtEmail.text.toString())
            writeSharedPref("address",editTxtAddress.text.toString())
            writeSharedPref("zipcode",editTxtZip.text.toString())
            writeSharedPref("city",editTxtCity.text.toString())
            val newIntent = Intent(application, MainActivity::class.java)
            startActivity(newIntent)
        })
    }

    fun writeSharedPref(key: String, value: String) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun readSharedPref(key: String): String {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "").toString()
    }
}