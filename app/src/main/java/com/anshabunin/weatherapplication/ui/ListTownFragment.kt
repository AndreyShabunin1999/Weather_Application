package com.anshabunin.weatherapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anshabunin.weatherapplication.databinding.FragmentListTownBinding
import com.anshabunin.weatherapplication.ui.adapter.TownAdapter

class ListTownFragment : Fragment() {

    private lateinit var binding: FragmentListTownBinding
    private lateinit var adapter: TownAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListTownBinding.inflate(inflater, container, false)

        val cities = listOf("Выборг", "Санкт-Петербург", "Нижний Новгород", "Великий Новгород", "Москва", "Абакан")
        adapter = TownAdapter(cities, onItemClick = { city ->
            val action = ListTownFragmentDirections.openWeatherMainFragment(city)
            view?.findNavController()?.navigate(action)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.backButton.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }


        return binding.root
    }

}