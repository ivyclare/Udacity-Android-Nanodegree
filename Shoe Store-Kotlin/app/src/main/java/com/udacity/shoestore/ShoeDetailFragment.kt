package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    lateinit var shoeDetailBinding: FragmentShoeDetailBinding

    val shoeListViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        shoeDetailBinding.saveButton.setOnClickListener {
            Timber.e("Clicking Save button")
            shoeListViewModel.addShoe(
                Shoe(shoeDetailBinding.shoe.text.toString(), shoeDetailBinding.size.text.toDouble(),
                    shoeDetailBinding.company.text.toString(), shoeDetailBinding.description.text.toString())
            )
            findNavController().popBackStack()
        }

        shoeDetailBinding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return shoeDetailBinding.root
    }
}


fun Editable.toDouble(): Double {
    val stringValue = toString()
    if (stringValue.isEmpty()) {
        return 0.0
    }
    return stringValue.toDouble()
}