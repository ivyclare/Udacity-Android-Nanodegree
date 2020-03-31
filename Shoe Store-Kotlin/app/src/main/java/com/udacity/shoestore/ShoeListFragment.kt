package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel

class ShoeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val shoeListBinding = DataBindingUtil.inflate<ShoeListFragmentBinding>(inflater,
            R.layout.shoe_list_fragment, container, false)
        shoeListBinding.buttonSelect = this
        //val shoesLayout = shoeListBinding.shoeView
        val shoesLayout = shoeListBinding.root.findViewById<LinearLayout>(R.id.shoeView)
        val shoeListViewModel: ShoeListViewModel by activityViewModels()

        shoeListViewModel.getShoes().observe(viewLifecycleOwner, Observer<MutableList<Shoe>> { shoes ->
            shoes.forEach { shoe ->
                val shoeItemBinding = DataBindingUtil.inflate<ShoeItemBinding>(inflater, R.layout.shoe_item, container, false)
                shoeItemBinding.shoe = shoe
                shoesLayout.addView(shoeItemBinding.root)
            }
        })

        return shoeListBinding.root
    }

    fun addShoe(view: View) {
        navigate(view)
    }

    private fun navigate(view: View) {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        view.findNavController().navigate(action)
    }


}
