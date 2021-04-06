package com.damla.fragmentexamplekotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import com.damla.fragmentexamplekotlin.databinding.FragmentSimpleBinding


class SimpleFragment : Fragment() {
    val YES: Int
    val NO : Int
    private val binding : FragmentSimpleBinding? = null
    init {
        YES = 0
        NO = 1
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = FragmentSimpleBinding.inflate(inflater,container,false)
        binding.rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            var Raring = ratingBar.getRating()
            Toast.makeText(this.requireContext(),"Rating Is : ${Raring}",Toast.LENGTH_LONG).show()
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var radioButton: View = binding.radioGroup.findViewById(checkedId)
            var index = binding.radioGroup.indexOfChild(radioButton)
            when(index){

                YES -> binding.fragmentHeader.setText("ARTICLE: Like")
                NO -> binding.fragmentHeader.setText("ARTICLE: Thanks")

            }
        }
        return  binding.root
    }


}