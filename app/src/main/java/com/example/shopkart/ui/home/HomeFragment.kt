package com.example.shopkart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shopkart.R
import com.example.shopkart.databinding.FragmentHomeBinding
import com.example.shopkart.viewmodels.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView1.adapter = ItemAdapter()
        binding.recyclerView2.adapter = ItemAdapter()
        binding.recyclerView3.adapter = ItemAdapter()
        return binding.root
    }

}
