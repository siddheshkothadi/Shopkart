package com.example.tabbedactivity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tabbedactivity.R
import com.example.tabbedactivity.databinding.FragmentHomeBinding

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
        binding.recyclerView.adapter = PhotoGridAdapter()
        return binding.root
    }

}
