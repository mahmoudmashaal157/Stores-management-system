package com.example.mashaal_stores.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = arrayOf(item::class),version = 1)
@Database(entities = arrayOf(item1::class),version = 3)
abstract class Store1_Database2 :RoomDatabase()  {
    abstract fun itemdao():itemDao_store1

    companion object{
        @Volatile
        private var INSTANCE :Store1_Database2?=null

        fun getdatabase(context: Context):Store1_Database2{
            val tempinstance = INSTANCE
            if(tempinstance!=null){
                return tempinstance
            }
            val instance = Room.databaseBuilder(context,Store1_Database2::class.java,"Store1_Items2")
                .allowMainThreadQueries()
                .build()
            INSTANCE=instance

            return instance


        }

    }
}