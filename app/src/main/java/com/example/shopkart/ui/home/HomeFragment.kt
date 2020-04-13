package com.example.shopkart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shopkart.R
import com.example.shopkart.databinding.FragmentHomeBinding
import com.example.shopkart.domain.CartModel
import com.example.shopkart.viewmodels.home.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
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
        viewModel.bool?.observe(viewLifecycleOwner, Observer { bool ->
            Toast.makeText(context,"$bool",Toast.LENGTH_LONG).show()
        })
        return binding.root
    }

}
