package com.example.shopkart.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommonDao{

    //For item type 1
    @Query("select * from databaseitemtype1")
    fun getItemType1(): LiveData<List<DatabaseItemType1>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems1( items: List<DatabaseItemType1>)

    //For item type 2
    @Query("select * from databaseitemtype2")
    fun getItemType2(): LiveData<List<DatabaseItemType2>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems2( items: List<DatabaseItemType2>)

    //For item type 3
    @Query("select * from databaseitemtype3")
    fun getItemType3(): LiveData<List<DatabaseItemType3>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems3( items: List<DatabaseItemType3>)

    //For kit type
    @Query("select * from databasekittype")
    fun getKitType(): LiveData<List<DatabaseKitType>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKits( kits: List<DatabaseKitType>)
}

@Database(entities = [DatabaseItemType1::class, DatabaseItemType2::class, DatabaseItemType3::class, DatabaseKitType::class], version = 1)
abstract class Databases:RoomDatabase(){
    abstract val commonDao: CommonDao
}

private lateinit var INSTANCE: Databases

//creates database when not initialized
fun getDatabase(context: Context): Databases {
    synchronized(Databases::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                Databases::class.java,
                "databases").build()
        }
    }
    return INSTANCE
}