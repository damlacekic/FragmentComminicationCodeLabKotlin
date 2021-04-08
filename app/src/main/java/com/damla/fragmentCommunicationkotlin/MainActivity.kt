package com.damla.fragmentCommunicationkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.damla.fragmentCommunicationkotlin.databinding.ActivityMainBinding
import android.os.Bundle as Bundle
import com.damla.fragmentCommunicationkotlin.SimpleFragment as SimpleFragment

class MainActivity : AppCompatActivity(),SimpleFragment.OnFragmentInteractionListener{
    private lateinit var binding: ActivityMainBinding
    var isFragmentDisplay : Boolean = false
    val STATE_FRAGMENT : String
    var mRadioButtonChoice : Int = 2
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
        val fragment = SimpleFragment().newInstance(mRadioButtonChoice)
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

    override fun onRadioButtonChoice(choice: Int) {

        mRadioButtonChoice = choice
        Toast.makeText(this, "Coice is : ${Integer.toString(choice)}",Toast.LENGTH_SHORT).show()
    }


}