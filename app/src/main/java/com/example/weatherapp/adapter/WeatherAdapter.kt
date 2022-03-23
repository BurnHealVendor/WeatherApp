package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ForecastItemBinding
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.model.Main

class WeatherAdapter(
    private val forecastList: MutableList<Forecast> = mutableListOf()
) : RecyclerView.Adapter<ForecastViewHolder>() {

    fun setForecast(newForecast: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(newForecast)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(forecastList[position].main)


    override fun getItemCount(): Int = forecastList.size

}

class ForecastViewHolder(private val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(main: Main) {
        binding.currentTemp.text = main.temp.toString()
        binding.tempMax.text = main.tempMax.toString()
        binding.tempMin.text = main.tempMin.toString()
    }
}

interface OnItemClickListener {
    fun onItemClicked(position: Int)
}