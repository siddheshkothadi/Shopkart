package com.example.shopkart.ui.cart

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopkart.R
import com.example.shopkart.databinding.FragmentCartBinding
import com.example.shopkart.viewmodels.cart.CartViewModel

class CartFragment : Fragment() {

    private val viewModel : CartViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, CartViewModel.Factory(activity.application)).get(CartViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCartBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)
        binding.recyclerView4.adapter = CartAdapter()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
