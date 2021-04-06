package com.damla.fragmentexamplekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.damla.fragmentexamplekotlin.databinding.ActivityMainBinding
import android.os.Bundle as Bundle
import com.damla.fragmentexamplekotlin.SimpleFragment as SimpleFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isFragmentDisplay : Boolean = false
    val STATE_FRAGMENT : String

    init {
        STATE_FRAGMENT = "state_of_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        binding.btOpen.setOnClickListener(View.OnClickListener { 
            if(!isFragmentDisplay){
                Ac()
            }
            else{
                Kapa()
            }
        })


        if(savedInstanceState!=null){
            isFragmentDisplay = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if(isFragmentDisplay){
                binding.btOpen.setText(R.string.butonKapat)
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {

        if (outState != null) {
            outState.putBoolean(STATE_FRAGMENT,isFragmentDisplay)
            super.onSaveInstanceState(outState)
        }
    }


    fun Ac(){
        val fragmentManager = supportFragmentManager
        val fragmentTransection = fragmentManager.beginTransaction()
        val fragment = SimpleFragment()
        fragmentTransection.replace(R.id.fragment_container,fragment).commit()
        binding.btOpen.setText(R.string.butonKapat)
        isFragmentDisplay = true
        

    }
    fun Kapa(){

        val fragmentManager = supportFragmentManager

        val fragment =  fragmentManager.findFragmentById(R.id.fragment_container) as SimpleFragment
        if(fragment!=null){
            val fragmentTransection = fragmentManager.beginTransaction()
            fragmentTransection.remove(fragment).commit()
        }
        binding.btOpen.setText(R.string.butonAc)
        isFragmentDisplay = false

        
    }
    fun Sonraki(view:View){
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)

    }


}