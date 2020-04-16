package com.example.shopkart.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shopkart.R
import com.example.shopkart.databinding.FragmentAccountBinding
import com.example.shopkart.viewmodels.account.AccountViewModel

class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            AccountViewModel.Factory(activity.application)
        ).get(AccountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.orderList.adapter = AccountAdapter()
        return binding.root
    }
}