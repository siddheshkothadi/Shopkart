package com.example.shopkart.viewmodels.cart

import android.app.Application
import androidx.lifecycle.*
import com.example.shopkart.database.getDatabase
import com.example.shopkart.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HomeRepository(getDatabase(application))
    val cartItems = repository.cartItems
    val kits  = repository.kits

    private var viewModelJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init{
        functionToBeRun()
    }

    private fun functionToBeRun() {
        coroutineScope.launch {
            try {
                repository.cartRecView()
            } catch (e: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}