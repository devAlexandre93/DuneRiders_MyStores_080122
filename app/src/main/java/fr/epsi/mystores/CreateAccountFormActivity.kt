package fr.epsi.mystores

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateAccountFormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_form)
        val editTxtLast = findViewById<EditText>(R.id.editTextLastName)
        val editTxtFirst = findViewById<EditText>(R.id.editTextFirstName)
        val editTxtEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTxtAddress = findViewById<EditText>(R.id.editTextAddress)
        val editTxtZip = findViewById<EditText>(R.id.editTextZipcode)
        val editTxtCity = findViewById<EditText>(R.id.editTextCity)
        val editTxtCard = findViewById<EditText>(R.id.editTextCardNumber)
        val btnCreate = findViewById<Button>(R.id.buttonForm)

        btnCreate.setOnClickListener(View.OnClickListener {
            writeSharedPref("lastName",editTxtLast.text.toString())
            writeSharedPref("firstName",editTxtFirst.text.toString())
            writeSharedPref("name",editTxtFirst.text.toString() + " " + editTxtLast.text.toString())
            writeSharedPref("email",editTxtEmail.text.toString())
            writeSharedPref("address",editTxtAddress.text.toString())
            writeSharedPref("zipcode",editTxtZip.text.toString())
            writeSharedPref("city",editTxtCity.text.toString())
            writeSharedPref("cardNumber",editTxtCard.text.toString())
            val newIntent = Intent(application, MainActivity::class.java)
            startActivity(newIntent)
        })

        editTxtLast.setText(readSharedPref("lastName"))
        editTxtFirst.setText(readSharedPref("firstName"))
        editTxtEmail.setText(readSharedPref("email"))
        editTxtAddress.setText(readSharedPref("address"))
        editTxtZip.setText(readSharedPref("zipcode"))
        editTxtCity.setText(readSharedPref("city"))
        editTxtCard.setText(readSharedPref("cardNumber"))

        intent.getStringExtra("lastname")?.let { setLastnameWithScanner(it) }
        intent.getStringExtra("firstname")?.let { setFirstNameWithScanner(it) }
        intent.getStringExtra("email")?.let { setEmailWithScanner(it) }
        intent.getStringExtra("address")?.let { setAddressWithScanner(it) }
        intent.getStringExtra("zipcode")?.let { setZipcodeWithScanner(it) }
        intent.getStringExtra("city")?.let { setCityWithScanner(it) }
        intent.getStringExtra("cardnumber")?.let { setCardNumberWithScanner(it) }

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