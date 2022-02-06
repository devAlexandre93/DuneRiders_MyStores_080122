package fr.epsi.mystores

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
    }

    private fun showTab2() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("OffersFragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, OffersFragment::class.java, null)
        fragmentTransaction.commit()
    }

    private fun showTab3() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("StoresFragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, StoresFragment::class.java, null)
        fragmentTransaction.commit()
    }

    //override fun onBackPressed() {
    //    if(supportFragmentManager.backStackEntryCount>1)
    //        super.onBackPressed()
    //    else
    //        finish()
    //}
}