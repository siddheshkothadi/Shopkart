package com.example.shopkart.di

import com.example.shopkart.database.getDatabase
import com.example.shopkart.repository.HomeRepository
import com.example.shopkart.viewmodels.account.AccountViewModel
import com.example.shopkart.viewmodels.cart.CartViewModel
import com.example.shopkart.viewmodels.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(getDatabase(androidApplication())) }
}

val viewModelModule = module(override = true) {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        CartViewModel(androidApplication(), get())
    }
    viewModel {
        AccountViewModel(get())
    }
}