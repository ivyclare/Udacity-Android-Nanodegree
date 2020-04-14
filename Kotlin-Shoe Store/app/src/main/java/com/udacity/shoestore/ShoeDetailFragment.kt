package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel


/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    lateinit var shoeDetailBinding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoeDetailBinding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(inflater, R.layout.fragment_shoe_detail, container, false)

        shoeDetailBinding.buttonSelect = this
        shoeDetailBinding.shoe = Shoe("", 0.0, "", "")
        return shoeDetailBinding.root

    }

    fun cancel(view: View) {
        navigate(view)
    }

    fun save(view: View, shoe: Shoe) {
        navigate(view)
        val shoeListViewModel: ShoeListViewModel by activityViewModels()
        shoeListViewModel.getShoes().value?.add(shoe)
    }

    private fun navigate(view: View) {
        //val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment()
        //view.findNavController().navigate(action)
        //view.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)

        view.findNavController().popBackStack()
    }



}
