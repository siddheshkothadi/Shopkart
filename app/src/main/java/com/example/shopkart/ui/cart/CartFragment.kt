package com.example.shopkart.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shopkart.R
import com.example.shopkart.databinding.FragmentCartBinding
import com.example.shopkart.viewmodels.cart.CartViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class CartFragment : Fragment() {

    lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
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
