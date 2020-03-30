package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

/**
 * A simple [Fragment] subclass.
 */
class InstructionsFragment : Fragment() {
    lateinit var instructionsBinding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        instructionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        instructionsBinding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_instructionsFragment_to_shoeListFragment)

        }
        return instructionsBinding.root
    }

}
