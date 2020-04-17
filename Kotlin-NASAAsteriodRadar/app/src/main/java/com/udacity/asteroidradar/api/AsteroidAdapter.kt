package com.udacity.asteroidradar.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import com.udacity.asteroidradar.Asteroid

class AsteroidAdapter(
    private val onItemClicked: (Asteroid) -> Unit
) : ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(
    AsteroidsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {

        val binding = AsteroidItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AsteroidViewHolder(
        private val binding: AsteroidItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(asteroid: Asteroid) {
            binding.asteroid = asteroid
            itemView.setOnClickListener {
                onItemClicked(asteroid)
            }
        }
    }

    class AsteroidsDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }
}
