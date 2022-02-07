package fr.epsi.mystores

import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import android.view.View

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tab1=findViewById<TextView>(R.id.textViewTab1)
        val tab2=findViewById<TextView>(R.id.textViewTab2)
        val tab3=findViewById<TextView>(R.id.textViewTab3)
        showAccount()

        tab1.setOnClickListener(View.OnClickListener {
            showTab1()
        })
        tab2.setOnClickListener(View.OnClickListener {
            showTab2()
        })
        tab3.setOnClickListener(View.OnClickListener {
            showTab3()
        })
        showTab1()
    }

    private fun showTab1() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("CardFragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, CardFragment::class.java, null)
        fragmentTransaction.commit()

        val tab1=findViewById<TextView>(R.id.textViewTab1)
        val tab2=findViewById<TextView>(R.id.textViewTab2)
        val tab3=findViewById<TextView>(R.id.textViewTab3)
        tab1.setTypeface(null, Typeface.BOLD)
        tab1.setBackgroundResource(R.drawable.gradient_black_border_darker)
        tab2.setTypeface(null, Typeface.NORMAL)
        tab2.setBackgroundResource(R.drawable.gradient_black_border)
        tab3.setTypeface(null, Typeface.NORMAL)
        tab3.setBackgroundResource(R.drawable.gradient_black_border)
    }

    private fun showTab2() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("OffersFragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, OffersFragment::class.java, null)
        fragmentTransaction.commit()

        val tab1=findViewById<TextView>(R.id.textViewTab1)
        val tab2=findViewById<TextView>(R.id.textViewTab2)
        val tab3=findViewById<TextView>(R.id.textViewTab3)
        tab1.setTypeface(null, Typeface.NORMAL)
        tab1.setBackgroundResource(R.drawable.gradient_black_border)
        tab2.setTypeface(null, Typeface.BOLD)
        tab2.setBackgroundResource(R.drawable.gradient_black_border_darker)
        tab3.setTypeface(null, Typeface.NORMAL)
        tab3.setBackgroundResource(R.drawable.gradient_black_border)
    }

    private fun showTab3() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("StoresFragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, MapsStoresFragment::class.java, null)
        fragmentTransaction.commit()

        val tab1=findViewById<TextView>(R.id.textViewTab1)
        val tab2=findViewById<TextView>(R.id.textViewTab2)
        val tab3=findViewById<TextView>(R.id.textViewTab3)
        tab1.setTypeface(null, Typeface.NORMAL)
        tab1.setBackgroundResource(R.drawable.gradient_black_border)
        tab2.setTypeface(null, Typeface.NORMAL)
        tab2.setBackgroundResource(R.drawable.gradient_black_border)
        tab3.setTypeface(null, Typeface.BOLD)
        tab3.setBackgroundResource(R.drawable.gradient_black_border_darker)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1)
            super.onBackPressed()
        else
            finish()
    }
}