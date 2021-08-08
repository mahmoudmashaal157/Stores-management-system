package com.example.mashaal_stores.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(item1::class),version = 4)
abstract class Store3_Database : RoomDatabase()  {
    abstract fun itemdao():itemDao_store1

    companion object{
        @Volatile
        private var INSTANCE :Store3_Database?=null

        fun getdatabase(context: Context):Store3_Database{
            val tempinstance = INSTANCE
            if(tempinstance!=null){
                return tempinstance
            }
            val instance = Room.databaseBuilder(context,Store3_Database::class.java,"Store3_Items")
                .allowMainThreadQueries()
                .build()
            INSTANCE=instance
            return instance


        }

    }
}