package com.example.shopkart.viewmodels.account

import androidx.lifecycle.*
import com.example.shopkart.repository.HomeRepository

class AccountViewModel(repository: HomeRepository) : ViewModel() {

    val orders = repository.orders
}