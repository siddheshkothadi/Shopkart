package com.example.shopkart.viewmodels.cart

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.example.shopkart.database.getDatabase
import com.example.shopkart.repository.HomeRepository
import com.example.shopkart.ui.MainActivity
import com.example.shopkart.ui.cart.CartFragment
import kotlinx.coroutines.*
import java.security.AccessController.getContext

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HomeRepository(getDatabase(application))
    val cartItemsRecView = repository.cartItemsForRecView

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private var viewModelJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    fun removeAll(){
        coroutineScope.launch{
            _loading.value = true
            repository.deleteAll()
            _loading.value = false
            Toast.makeText(getApplication(),"Paid",Toast.LENGTH_LONG).show()
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