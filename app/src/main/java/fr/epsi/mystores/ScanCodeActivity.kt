package fr.epsi.mystores

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import org.json.JSONObject

private const val CAMERA_REQUEST_CODE = 101

class ScanCodeActivity : BaseActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_code)

        setupPermissions()
        codeScanner()
    }

    private fun codeScanner() {
        val scanView = findViewById<CodeScannerView>(R.id.scannerView)
        codeScanner = CodeScanner(this, scanView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    val jsonData = JSONObject(it.text)
                    val lastname = jsonData.getString("lastName")
                    val firstname = jsonData.getString("firstName")
                    val email = jsonData.getString("email")
                    val address = jsonData.getString("address")
                    val zipcode = jsonData.getString("zipcode")
                    val city = jsonData.getString("city")
                    val cardnumber = jsonData.getString("cardRef")
                    val newIntent = Intent(application, CreateAccountFormActivity::class.java)
                    newIntent.putExtra("lastname", lastname)
                    newIntent.putExtra("firstname", firstname)
                    newIntent.putExtra("email", email)
                    newIntent.putExtra("address", address)
                    newIntent.putExtra("zipcode", zipcode)
                    newIntent.putExtra("city", city)
                    newIntent.putExtra("cardnumber", cardnumber)
                    startActivity(newIntent)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        scanView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "You need the camera permission to be able to scan !",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Successful
                }
            }
        }
    }
}