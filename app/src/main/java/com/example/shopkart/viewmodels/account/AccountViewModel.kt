package com.example.shopkart.viewmodels.account

import android.app.Application
import androidx.lifecycle.*
import com.example.shopkart.database.getDatabase
import com.example.shopkart.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HomeRepository(getDatabase(application))

    val orders = repository.orders

    private var viewModelJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AccountViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}