package com.example.weatherapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.OnItemClickListener
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.viewmodel.ResultState

class ForecastFrag : BaseFragment(), OnItemClickListener {

    private val binding by lazy {
        FragmentForecastBinding.inflate(layoutInflater)
    }

    private val weatherAdapter by lazy {
        WeatherAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.recView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = weatherAdapter
        }

        weatherViewModel.cityForecast.observe(viewLifecycleOwner, ::handleState)

        weatherViewModel.getForecast("Atlanta")

        return binding.root
    }

    private fun handleState(resultState: ResultState?) {
        when(resultState) {
            is ResultState.LOADING -> {
                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
            }
            is ResultState.SUCCESS -> {
                weatherAdapter.setForecast(resultState.results.list)
            }
            is ResultState.ERROR -> {
                Log.e("Forecast", resultState.error.localizedMessage, resultState.error)
                Toast.makeText(requireContext(), resultState.error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemClicked(position: Int) {
        TODO("Not yet implemented")
    }
}