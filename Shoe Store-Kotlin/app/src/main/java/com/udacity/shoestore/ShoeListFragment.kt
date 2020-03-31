package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.shoe_list_fragment.*
import timber.log.Timber

class ShoeListFragment : Fragment() {

    lateinit var shoeListBinding: ShoeListFragmentBinding
    val shoeListViewModel: ShoeListViewModel by activityViewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        shoeListViewModel.shoes.observe(this, Observer {shoes ->
//            showShoes(shoes)
//        })
//
//    }
//
//    private fun showShoes(shoes: List<Shoe>) {
//        shoeView.removeAllViews()
//        shoes.forEach { addShoeItem(it) }
//    }
//
//    private fun addShoeItem(shoe: Shoe) {
//        Timber.e("Adding shoe $shoe")
//        val shoeLayout = LayoutInflater.from(this.context).inflate(R.layout.shoe_item, shoeView, false)
//        shoeLayout.findViewById<TextView>(R.id.shoeName).text = shoe.name
//        shoeLayout.findViewById<TextView>(R.id.shoeDescription).text = shoe.description
//        shoeView.addView(shoeLayout)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shoeListBinding = DataBindingUtil.inflate(inflater,
            R.layout.shoe_list_fragment, container, false)

        shoeListBinding.buttonSelect = this
        val shoesLayout = shoeListBinding.shoeView
        //        val shoesLayout = shoeListBinding.root.findViewById<LinearLayout>(R.id.shoeView

        shoeListViewModel.getShoes().observe(viewLifecycleOwner, Observer<MutableList<Shoe>> { shoes ->
            shoes.forEach { shoe ->
                val shoeItemBinding = DataBindingUtil.inflate<ShoeItemBinding>(inflater, R.layout.shoe_item, container, false)
                shoeItemBinding.shoe = shoe
                shoesLayout.addView(shoeItemBinding.root)
            }
        })
//        shoeListBinding.addButton.setOnClickListener {
//            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
//        }
        return shoeListBinding.root
    }


}
