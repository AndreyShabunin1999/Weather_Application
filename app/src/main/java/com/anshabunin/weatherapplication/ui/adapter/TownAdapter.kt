package com.anshabunin.weatherapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anshabunin.weatherapplication.databinding.ItemTownBinding

class TownAdapter(private val cities: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<TownAdapter.TownViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTownBinding.inflate(inflater, parent, false)
        return TownViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class TownViewHolder(private val binding: ItemTownBinding, onItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(binding.tvCityName.text.toString())
            }
        }

        fun bind(city: String) {
            binding.tvCityName.text = city
        }
    }
}

