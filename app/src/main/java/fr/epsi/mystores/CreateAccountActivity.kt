package fr.epsi.mystores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CreateAccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        val buttonQrCode = findViewById<Button>(R.id.button_create_account1)
        val buttonForm = findViewById<Button>(R.id.button_create_account2)

        buttonQrCode.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,ScanCodeActivity::class.java)
            startActivity(newIntent)
        })

        buttonForm.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,CreateAccountFormActivity::class.java)
            startActivity(newIntent)
        })
    }
}