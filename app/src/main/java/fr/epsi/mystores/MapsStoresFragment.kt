package fr.epsi.mystores

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import com.google.android.gms.maps.model.Marker

import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener


class MapsStoresFragment : Fragment() {

    lateinit var googleMap: GoogleMap

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.N)
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled = true
            }
            else -> {
                // No location access granted.
            }
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))

        fetchStores(googleMap)

        this.googleMap = googleMap
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_stores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun fetchStores(googleMap: GoogleMap) {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url("https://djemam.com/epsi/stores.json")
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                val jsonStores = JSONObject(data)
                val stores = jsonStores.getJSONArray("stores")
                activity?.runOnUiThread(Runnable {
                    for (i in 0 until stores.length()) {
                        val jsonStore = stores.getJSONObject(i)
                        val store = MarkerOptions()
                        val storeName = jsonStore.optString("name")
                        val storeAddress = jsonStore.optString("address")
                        val storeZipcode = jsonStore.optString("zipcode")
                        val storeCity = jsonStore.optString("city")
                        val storePicture = jsonStore.optString("pictureStore")
                        val storeDescription = jsonStore.optString("description")
                        store.title("$storeName")
                        store.snippet("$storeAddress, $storeZipcode $storeCity")
                        val storeLatLng = LatLng(
                            jsonStore.optDouble("latitude", 0.0),
                            jsonStore.optDouble("longitude", 0.0)
                        )
                        store.position(storeLatLng)
                        store.icon(defaultMarker(HUE_AZURE))
                        googleMap.addMarker(store)?.tag = "$storePicture///$storeDescription"

                        googleMap.setOnInfoWindowClickListener {
                            val storePictureAndDescription = it.tag as? String
                            val newIntent = Intent(activity as BaseActivity, StoreActivity::class.java)
                            newIntent.putExtra("storeName", it.title)
                            newIntent.putExtra("storeAddress", it.snippet)
                            newIntent.putExtra("storePictureAndDescription", storePictureAndDescription)
                            startActivity(newIntent)
                        }
                    }
                })
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

}
