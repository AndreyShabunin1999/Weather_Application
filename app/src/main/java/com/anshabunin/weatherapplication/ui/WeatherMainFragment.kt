package com.anshabunin.weatherapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import com.anshabunin.weatherapplication.R
import com.anshabunin.weatherapplication.data.Status
import com.anshabunin.weatherapplication.databinding.FragmentWeatherMainBinding
import com.anshabunin.weatherapplication.domain.DbRepository
import com.anshabunin.weatherapplication.domain.NetworkRepository
import com.anshabunin.weatherapplication.viewmodel.WeatherMainViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherMainFragment : Fragment() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    @Inject
    lateinit var dbRepository: DbRepository

    private lateinit var binding: FragmentWeatherMainBinding

    private val viewModel: WeatherMainViewModel by viewModels {
        WeatherMainViewModel.factory(networkRepository, dbRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_main,
            container,
            false
        )

        val cityName = arguments?.let {
            WeatherMainFragmentArgs.fromBundle(it).town
        } ?: resources.getString(R.string.default_town)

        viewModel.getWeatherData(cityName)

        binding.networkBtn.setOnClickListener {
            viewModel.getWeatherData(cityName)
        }

        binding.databaseBtn.setOnClickListener {
            viewModel.getSavedWeather()
        }

        binding.databaseSaveBtn.setOnClickListener {
            viewModel.weatherData.value?.data?.let { it1 -> viewModel.insertWeatherDataIntoDb(it1) }
        }

        viewModel.dbInsertStatus.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), R.string.success_toast, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), R.string.error_toast, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnListTown.setOnClickListener {
            view?.findNavController()?.navigate(WeatherMainFragmentDirections.openListTownFragment())
        }

        viewModel.weatherData.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    // Данные успешно получены, используйте resource.data
                    val weatherData = resource.data
                    println(weatherData)

                    val modifiedUrl = resource.data?.current?.condition?.icon?.replace("64x64", "128x128")
                    Glide.with(requireContext())
                        .load("https:${modifiedUrl}")
                        .into(binding.weatherIcon)

                    binding.invalidateAll()
                    binding.executePendingBindings()
                }

                Status.ERROR -> {
                    println("error")
                    println(resource.message)
                    // Произошла ошибка, используйте resource.message
                    val errorMessage = resource.message
                }

                Status.LOADING -> {
                    println("loading")
                    // Идет загрузка
                }
            }
        }


        binding.apply {
            lifecycleOwner = this@WeatherMainFragment
            data = viewModel
        }

        // Другие операции с привязкой данных
        return binding.root
    }
}