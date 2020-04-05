package com.example.shopkart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shopkart.databinding.FragmentHomeBinding
import com.example.shopkart.viewmodels.home.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by lazy{
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView1.adapter = ItemAdapter()
        binding.recyclerView2.adapter = ItemAdapter()
        binding.recyclerView3.adapter = ItemAdapter()
        return binding.root
    }


}
