package com.example.shopkart.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shopkart.database.*
import com.example.shopkart.domain.AccountModel
import com.example.shopkart.domain.CartModel
import com.example.shopkart.domain.ItemTypeModel
import com.example.shopkart.domain.KitTypeModel
import com.example.shopkart.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository(private val database: Databases) {

    val cartItemsForRecView: LiveData<List<CartModel>> =
        Transformations.map(database.commonDao.getCartForRecView()) { it.asDomainCartModel() }
    val orders: LiveData<List<AccountModel>> =
        Transformations.map(database.commonDao.getAccount()) { it.asDomainAccountModel() }
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

    suspend fun add(id: Int){
        withContext(Dispatchers.IO) {
            val kitsForCart = database.commonDao.getKitTypeForCart()
            database.commonDao.insertInCart(kitsForCart[id-1].asDatabaseCartType())
        }
    }

    suspend fun remove(id: Int) {
        withContext(Dispatchers.IO) {
            database.commonDao.remove(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            val listCart = database.commonDao.getCartForAccount()
            val price = calculateTotalBill(listCart)
            database.commonDao.insertAccount(price)
            delay(2200)
            database.commonDao.deleteAll()
        }
    }

    private fun calculateTotalBill(listCart : List<DatabaseCart>): Int{
        var sum = 0
        for (items in listCart) {
            sum += items.price.substring(1).toInt()
        }
        return sum
    }

}