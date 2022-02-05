package fr.epsi.mystores

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
            writeSharedPref("email",editTxtEmail.text.toString())
            writeSharedPref("address",editTxtAddress.text.toString())
            writeSharedPref("zipcode",editTxtZip.text.toString())
            writeSharedPref("city",editTxtCity.text.toString())
            writeSharedPref("cardNumber",editTxtCard.text.toString())
        })

        editTxtLast.setText(readSharedPref("lastName"))
        editTxtFirst.setText(readSharedPref("firstName"))
        editTxtEmail.setText(readSharedPref("email"))
        editTxtAddress.setText(readSharedPref("address"))
        editTxtZip.setText(readSharedPref("zipcode"))
        editTxtCity.setText(readSharedPref("city"))
        editTxtCard.setText(readSharedPref("cardNumber"))
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