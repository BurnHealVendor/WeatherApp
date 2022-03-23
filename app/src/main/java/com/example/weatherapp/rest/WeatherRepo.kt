package com.example.weatherapp.rest

import com.example.weatherapp.model.Results
import retrofit2.Response

class WeatherRepoImpl(
    private val weatherServiceAPI: ServiceAPI
) : WeatherRepo {

    override suspend fun getCityForecast(city: String): Response<Results> {
        return weatherServiceAPI.getForecast(city = city)
    }
}

interface WeatherRepo {
    suspend fun getCityForecast(city: String): Response<Results>
}