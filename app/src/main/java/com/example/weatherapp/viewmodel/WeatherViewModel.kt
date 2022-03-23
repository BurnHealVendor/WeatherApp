package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Results
import com.example.weatherapp.rest.WeatherRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val weatherRepo: WeatherRepo,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _cityForecast: MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val cityForecast: LiveData<ResultState> get() = _cityForecast

    fun getForecast(city: String) {
        viewModelScope.launch(ioDispatcher) {
            // worker thread
            try {
                val response = weatherRepo.getCityForecast(city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // forecast for city
                        //_cityForecast.postValue(ResultState.SUCCESS(it))
                        withContext(Dispatchers.Main) {
                            // main thread
                            _cityForecast.value = (ResultState.SUCCESS(it))
                        }
                    } ?: throw Exception("Response null")
                }
                else {
                    throw Exception("Not successful")
                }
            }
            catch (e: Exception) {
                _cityForecast.postValue(ResultState.ERROR(e))
            }
        }
    }
}