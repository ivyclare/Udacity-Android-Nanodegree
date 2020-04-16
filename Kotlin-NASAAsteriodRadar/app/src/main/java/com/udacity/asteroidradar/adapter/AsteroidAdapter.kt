package com.udacity.asteroidradar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding


class AsteroidsAdapter(var asteroids:List<Asteroid>,
                       var onAsteroidClickListener: OnAsteroidClickListener)
    :RecyclerView.Adapter<AsteroidViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun getItemCount() = asteroids.size
    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = asteroids[position]
        holder.bind(asteroid,onAsteroidClickListener)
    }

    fun setList(asteroidsList: List<Asteroid>) {
        asteroids = asteroidsList
        notifyDataSetChanged()
    }

}


class AsteroidViewHolder(val layoutAsteroidBinding: AsteroidItemBinding):RecyclerView.ViewHolder(layoutAsteroidBinding.root) {

    fun bind(asteroid: Asteroid,onAsteroidClickListener: OnAsteroidClickListener) {
        layoutAsteroidBinding.asteroid = asteroid
        layoutAsteroidBinding.onAsteroidClickedListener = onAsteroidClickListener
        layoutAsteroidBinding.executePendingBindings()
    }
    companion object {

        fun from(parent:ViewGroup):AsteroidViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = AsteroidItemBinding.inflate(layoutInflater)
            return AsteroidViewHolder(binding)
        }
    }
}

class OnAsteroidClickListener(var listener:(asteroid:Asteroid)->Unit) {
    fun onAsteroidClicked(asteroid:Asteroid) = listener(asteroid)
}
