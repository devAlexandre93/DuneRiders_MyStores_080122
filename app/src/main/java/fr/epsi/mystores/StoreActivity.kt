package fr.epsi.mystores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class StoreActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        intent.getStringExtra("storeName")?.let { setHeaderTitle(it) }
        showBack()

        val imgView = findViewById<ImageView>(R.id.imgViewStoreDetails)
        val urlImage = intent.getStringExtra("storePicture")
        Picasso.get().load(urlImage).error(R.drawable.no_image).into(imgView)

        val txtViewAddress = findViewById<TextView>(R.id.txtViewStoreAddress)
        val address = intent.getStringExtra("storeAddress")
        txtViewAddress.text = address

        val txtViewZipcode = findViewById<TextView>(R.id.txtViewStoreZipcode)
        val zipcode = intent.getStringExtra("storeZipcode")
        txtViewZipcode.text = "$zipcode - "

        val txtViewCity = findViewById<TextView>(R.id.txtViewStoreCity)
        val city = intent.getStringExtra("storeCity")
        txtViewCity.text = city

        val txtViewDescription = findViewById<TextView>(R.id.txtViewStoreDescription)
        val description = intent.getStringExtra("storeDescription")
        txtViewDescription.text = "Description : $description"
    }
}