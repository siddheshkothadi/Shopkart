package com.example.shopkart.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shopkart.database.*
import com.example.shopkart.domain.AccountModel
import com.example.shopkart.domain.CartModel
import com.example.shopkart.domain.ItemTypeModel
import com.example.shopkart.domain.KitTypeModel
import com.example.shopkart.network.*
import com.example.shopkart.util.visibility1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository(private val database: Databases) {

    //TODO: can be val
    var cartItemsForRecView: LiveData<List<CartModel>> =
        Transformations.map(database.commonDao.getCartForRecView()) { it.asDomainCartModel() }

    val orders: LiveData<List<AccountModel>> =
        Transformations.map(database.commonDao.getAccount()){it.asDomainAccountModel()}

    val kits: LiveData<List<KitTypeModel>> =
        Transformations.map(database.commonDao.getKitType()) { it.asDomainKitTypeModel() }
    val items1: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType1()) { it.asDomainItemType1Model() }
    val items2: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType2()) { it.asDomainItemType2Model() }
    val items3: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType3()) { it.asDomainItemType3Model() }

    suspend fun refreshKitsAndItems() {
        withContext(Dispatchers.IO) {
            val kitNetwork = Api.retrofitService.getKitTypesAsync().await()
            val listItems1Network = Api.retrofitService.getItems1Async().await()
            val listItems2Network = Api.retrofitService.getItems2Async().await()
            val listItems3Network = Api.retrofitService.getItems3Async().await()
            database.commonDao.insertAllKits(kitNetwork.asDatabaseKitType())
            database.commonDao.insertAllItems1(listItems1Network.asDatabaseItemType1())
            database.commonDao.insertAllItems2(listItems2Network.asDatabaseItemType2())
            database.commonDao.insertAllItems3(listItems3Network.asDatabaseItemType3())
        }
    }

    suspend fun add1() {
        withContext(Dispatchers.IO) {
            val kitsForCart : DatabaseKitType = database.commonDao.getKitTypeForCart()[0]
            database.commonDao.insertInCart(kitsForCart.asDatabaseCartType())
        }
    }
    suspend fun add2() {
        withContext(Dispatchers.IO) {
            val kitsForCart : DatabaseKitType = database.commonDao.getKitTypeForCart()[1]
            database.commonDao.insertInCart(kitsForCart.asDatabaseCartType())
        }
    }
    suspend fun add3() {
        withContext(Dispatchers.IO) {
            val kitsForCart : DatabaseKitType = database.commonDao.getKitTypeForCart()[2]
            database.commonDao.insertInCart(kitsForCart.asDatabaseCartType())
        }
    }

    suspend fun remove1() {
        withContext(Dispatchers.IO) {
            database.commonDao.remove1()
        }
    }
    suspend fun remove2() {
        withContext(Dispatchers.IO) {
            database.commonDao.remove2()
        }
    }
    suspend fun remove3() {
        withContext(Dispatchers.IO) {
            database.commonDao.remove3()
        }
    }

    suspend fun deleteAll(){
        withContext(Dispatchers.IO) {
            var sum = 0
            val listCart = database.commonDao.getCartForAccount()
            try {
                for (items in listCart) {
                    sum += items.price.substring(1).toInt()
                }
                database.commonDao.insertAccount(sum)
            }
            catch (e: Exception){
                val orderList = database.commonDao.getAccountForDebug()
                println("new Exception")
                println("new $orderList")
            }
            val orderList = database.commonDao.getAccountForDebug()
            println("new $orderList")
            database.commonDao.deleteAll()
        }
    }

}