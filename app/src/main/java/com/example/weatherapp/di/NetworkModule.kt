package com.example.weatherapp.di

import android.app.Service
import android.content.Context
import com.example.weatherapp.rest.ServiceAPI
import com.example.weatherapp.rest.WeatherRepo
import com.example.weatherapp.rest.WeatherRepoImpl
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.schedulers.Schedulers.single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun providesMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    fun providesServiceAPI(okHttpClient: OkHttpClient, moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(ServiceAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(ServiceAPI::class.java)

    single { providesMoshi() }
    single { providesLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { providesServiceAPI(get(), get()) }
}

val viewModelModule = module {
    fun providesWeatherRepo(weatherAPI: ServiceAPI): WeatherRepo =
        WeatherRepoImpl(weatherAPI)

    single { providesWeatherRepo(get()) }

    viewModel { WeatherViewModel(get()) }
}