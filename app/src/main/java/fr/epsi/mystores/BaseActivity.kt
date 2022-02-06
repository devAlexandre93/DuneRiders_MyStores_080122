package fr.epsi.mystores

import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun showBack() {
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility = View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }

    fun setHeaderTitle(text: String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = text
    }

    fun showToast(txt: String) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }

    fun setLastnameWithScanner(lastname: String) {
        val editTxtLast = findViewById<EditText>(R.id.editTextLastName)
        editTxtLast.setText(lastname)
    }

    fun setFirstNameWithScanner(firstname: String) {
        val editTxtFirst = findViewById<EditText>(R.id.editTextFirstName)
        editTxtFirst.setText(firstname)
    }

    fun setEmailWithScanner(email: String) {
        val editTxtEmail = findViewById<EditText>(R.id.editTextEmail)
        editTxtEmail.setText(email)
    }

    fun setAddressWithScanner(address: String) {
        val editTxtAddress = findViewById<EditText>(R.id.editTextAddress)
        editTxtAddress.setText(address)
    }

    fun setZipcodeWithScanner(zipcode: String) {
        val editTxtZip = findViewById<EditText>(R.id.editTextZipcode)
        editTxtZip.setText(zipcode)
    }

    fun setCityWithScanner(city: String) {
        val editTxtCity = findViewById<EditText>(R.id.editTextCity)
        editTxtCity.setText(city)
    }

    fun setCardNumberWithScanner(cardnumber: String) {
        val editTxtCard = findViewById<EditText>(R.id.editTextCardNumber)
        editTxtCard.setText(cardnumber)
    }
}