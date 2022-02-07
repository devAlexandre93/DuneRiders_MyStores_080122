package fr.epsi.mystores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.epsi.mystores.ProductsListAdapter.ViewHolder

class ProductsListAdapter(private val productsList: ProductsList): RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val imageViewProduct: ImageView = view.findViewById<ImageView>(R.id.imageViewProductCell)
        val textViewName: TextView = view.findViewById<TextView>(R.id.textViewProductName)
        val textViewDescription: TextView = view.findViewById<TextView>(R.id.textViewProductDescription)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_product_cell, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsList.items[position]
        holder.textViewName.text = product.name
        holder.textViewDescription.text = product.description
        Picasso.get().load(product.picture_url).error(R.drawable.no_image).into(holder.imageViewProduct)
    }

    override fun getItemCount(): Int {
        return productsList.items.size
    }

}