package com.udacity.shoestore

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.ShoeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.udacity.shoestore.R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
    }
}
