package com.example.weatherapp.views

import androidx.fragment.app.Fragment
import com.example.weatherapp.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment : Fragment() {
    protected val weatherViewModel: WeatherViewModel by viewModel()
}