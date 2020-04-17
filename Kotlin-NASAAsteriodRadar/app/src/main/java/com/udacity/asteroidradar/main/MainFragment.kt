package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants.MEDIA_TYPE_IMAGE
import com.udacity.asteroidradar.api.AsteroidAdapter
import com.udacity.asteroidradar.utils.NetworkState
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var asteroidAdapter: AsteroidAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        with(binding.asteroidRecycler) {
            setHasFixedSize(true)
            asteroidAdapter = AsteroidAdapter {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
            }
            adapter = asteroidAdapter
        }

        viewModel.asteroidsLiveData.observe(viewLifecycleOwner, Observer {
            asteroidAdapter.submitList(it)
        })

        viewModel.imageOfTheDayLiveData.observe(viewLifecycleOwner, Observer {
            if (it.mediaType == MEDIA_TYPE_IMAGE) {
                Picasso.with(context)
                    .load(it.url)
                    .into(binding.activityMainImageOfTheDay, object : Callback {
                        override fun onSuccess() {
                            viewModel.setImageState(NetworkState.LOADED)
                        }

                        override fun onError() {
                            val failedToLoad = Constants.FAILED_TO_LOAD_IOD
                            viewModel.setImageState(NetworkState.error(failedToLoad))
                            Timber.e(failedToLoad)
                        }
                    })
            } else {
                val imageOfDayNotAvailable = getString(R.string.not_an_image)
                viewModel.setImageState(NetworkState.error(imageOfDayNotAvailable))
                Timber.e(imageOfDayNotAvailable)
            }
        })

        viewModel.asteroidsStateLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> {
                    binding.statusLoadingWheel.visibility = View.VISIBLE
                }
                NetworkState.LOADED -> {
                    binding.statusLoadingWheel.visibility = View.GONE
                }
                else -> {
                    binding.statusLoadingWheel.visibility = View.GONE
                    binding.tvAsteroidsState.visibility = View.VISIBLE
                }
            }
        })

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_week_asteroids -> viewModel.getWeeklyAsteroids()
            R.id.show_today_asteroids -> viewModel.getTodayAsteroids()
            R.id.show_saved_asteroids -> viewModel.getSavedAsteroids()
        }
        return true
    }
}
