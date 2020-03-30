package com.example.tabbedactivity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tabbedactivity.databinding.FragmentHomeBinding
import com.example.tabbedactivity.network.Property
import kotlinx.android.synthetic.main.fragment_home.*

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
        binding.recyclerView1.adapter = Adapter1()
        binding.recyclerView2.adapter = Adapter2()
        binding.recyclerView3.adapter = Adapter3()
        /*viewModel.text1.observe(viewLifecycleOwner, Observer { newX ->
            binding.property = newX
        })*/
        return binding.root
    }


}
