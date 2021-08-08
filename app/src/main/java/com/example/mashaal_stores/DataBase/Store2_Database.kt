package com.example.mashaal_stores.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(item1::class),version = 4)
abstract class Store2_Database: RoomDatabase()  {
    abstract fun itemdao():itemDao_store1

    companion object{
        @Volatile
        private var INSTANCE :Store2_Database?=null

        fun getdatabase(context: Context):Store2_Database{
            val tempinstance = INSTANCE
            if(tempinstance!=null){
                return tempinstance
            }
            val instance = Room.databaseBuilder(context,Store2_Database::class.java,"Store2_Items1")
                .allowMainThreadQueries()
                .build()
            INSTANCE=instance
            return instance


        }

    }
}