package com.damla.fragmentCommunicationkotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.damla.fragmentCommunicationkotlin.databinding.FragmentSimpleBinding
import java.lang.ClassCastException


class SimpleFragment : Fragment() {

    interface OnFragmentInteractionListener{
        fun onRadioButtonChoice(choice: Int)
    }
    val YES: Int
    val NO : Int
    val NONE : Int
    val CHOICE : String
    lateinit var mListener : OnFragmentInteractionListener


    init {
        YES = 0
        NO = 1
        NONE = 2
        CHOICE = "choice"
    }
    private val binding : FragmentSimpleBinding? = null
    var mRadioButtonChoice : Int = NONE


    fun newInstance(choice: Int): SimpleFragment{
        val args : Bundle = Bundle()
        args.putInt("CHOICE",choice)
        val fragment = SimpleFragment()
        fragment.arguments = args
        return  fragment
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
        if(requireArguments().containsKey(CHOICE)){
            mRadioButtonChoice = requireArguments().getInt(CHOICE)
            if (mRadioButtonChoice != NONE){
                binding.radioGroup.check(binding.radioGroup.getChildAt(mRadioButtonChoice).id)
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var radioButton: View = binding.radioGroup.findViewById(checkedId)
            var index = binding.radioGroup.indexOfChild(radioButton)
            when(index){

                YES ->
                {
                    binding.fragmentHeader.setText("ARTICLE: Like")
                    mRadioButtonChoice = YES
                    mListener.onRadioButtonChoice(YES)
                }


                NO ->
                {
                    binding.fragmentHeader.setText("ARTICLE: Thanks")
                    mRadioButtonChoice = NO
                    mListener.onRadioButtonChoice(NO)
                }
                else ->
                {
                    mRadioButtonChoice = NONE
                    mListener.onRadioButtonChoice(NONE)
                }

            }
        }
        return  binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            mListener = context
        }else{
            throw ClassCastException(context.toString() + resources.getString(R.string.exeption_message))
        }
    }


}