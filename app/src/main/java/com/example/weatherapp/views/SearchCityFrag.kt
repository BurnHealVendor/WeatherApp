package com.example.weatherapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchCityBinding

class SearchCityFrag : Fragment() {

    private val binding by lazy {
        FragmentSearchCityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            SearchCityFrag().apply {
                arguments = Bundle().apply {
                }
            }
    }
}