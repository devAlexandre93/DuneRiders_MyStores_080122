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

        val storePictureAndDescription =
            intent.getStringExtra("storePictureAndDescription")?.split("///")

        val imgView = findViewById<ImageView>(R.id.imgViewStoreDetails)
        val urlImage = storePictureAndDescription?.get(0)
        Picasso.get().load(urlImage).error(R.drawable.no_image).into(imgView)

        val fullAddress = intent.getStringExtra("storeAddress")?.split(',')
        val address = fullAddress?.get(0)
        val zipcodeAndCity = fullAddress?.get(1)?.split(' ')
        val zipcode = zipcodeAndCity?.get(1)
        val city = zipcodeAndCity?.get(2)

        val txtViewAddress = findViewById<TextView>(R.id.txtViewStoreAddress)
        txtViewAddress.text = address

        val txtViewZipcode = findViewById<TextView>(R.id.txtViewStoreZipcode)
        txtViewZipcode.text = "$zipcode - "

        val txtViewCity = findViewById<TextView>(R.id.txtViewStoreCity)
        txtViewCity.text = city

        val txtViewDescription = findViewById<TextView>(R.id.txtViewStoreDescription)
        val description = storePictureAndDescription?.get(1)
        txtViewDescription.text = "Description : $description"
    }
}