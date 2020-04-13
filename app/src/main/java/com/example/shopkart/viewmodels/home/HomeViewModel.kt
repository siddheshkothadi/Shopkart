package com.example.shopkart.viewmodels.home

import android.app.Application
import androidx.lifecycle.*
import com.example.shopkart.database.*
import com.example.shopkart.repository.HomeRepository
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //For loading screen => progress bar
    enum class ApiStatus {LOADING, DONE, ERROR}
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    //For visibility
    private val _added1 = MutableLiveData<Boolean>()
    val added1: LiveData<Boolean>
        get() = _added1
    private val _added2 = MutableLiveData<Boolean>()
    val added2: LiveData<Boolean>
        get() = _added2
    private val _added3 = MutableLiveData<Boolean>()
    val added3: LiveData<Boolean>
        get() = _added3

    //repository
    private val repository = HomeRepository(getDatabase(application))
    val kitTypes = repository.kits
    val items1 = repository.items1
    val items2 = repository.items2
    val items3 = repository.items3
    val cartItems = repository.cartItemsForRecView
    //val bool = repository.bool

    //coroutines
    private var viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        refreshDataFromRepository()
        //visibility()
    }

    private fun refreshDataFromRepository() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                repository.refreshKitsAndItems()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    /*private fun visibility(){
        coroutineScope.launch {
            if (cartItems.value.isNullOrEmpty()) {
                _added1.value = false
                _added2.value = false
                _added3.value = false
            } else {
                for (items in cartItems.value!!) {
                    if (items.id == "1") {
                        _added1.value = true
                    }
                    if (items.id == "2") {
                        _added2.value = true
                    }
                    if (items.id == "3") {
                        _added3.value = true
                    }
                }
            }
        }
    }*/


    //onClicks
    fun addToCart1(){
        coroutineScope.launch {
            try {
                repository.add1()
            }catch (e:Exception){
                print("hey error here 1")
            }
        }
    }
    fun addToCart2(){
        coroutineScope.launch {
            try {
                repository.add2()
            }catch (e:Exception){
                print("hey error here 1")
            }
        }
    }
    fun addToCart3(){
        coroutineScope.launch {
            try {
                repository.add3()
            }catch (e:Exception){
                print("hey error here 1")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}