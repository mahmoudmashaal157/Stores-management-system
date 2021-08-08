package com.example.mashaal_stores.DataBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface itemDao_store1 {
    @Query("SELECT * FROM item1")
     fun getAll(): List<item1>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert_item(data :item1)

    @Query("select * from item1 where name=:name ")
    fun getallbyname(name:String):item1

    @Update (onConflict = OnConflictStrategy.REPLACE)
    suspend fun update (item:item1)

    @Query("UPDATE item1 SET name=:name1,type=:type1 ,date=:date1,ward=:ward1,sadr=:sadr1,number=:number1 WHERE uid=:id")
    suspend fun updatebyid(id:Int,name1: String,type1:String,date1:String,ward1:Double,sadr1:Double,number1:Double)

    @Delete()
    suspend fun delete (item: item1)

    //@Query("DROP TABLE IF EXISTS item")
    //suspend fun delete()

    

}