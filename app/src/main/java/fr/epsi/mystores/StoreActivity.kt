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

        val txtView = findViewById<TextView>(R.id.txtViewStoreDetails)
        val description = intent.getStringExtra("storeDescription")
        txtView.text = description
    }
}