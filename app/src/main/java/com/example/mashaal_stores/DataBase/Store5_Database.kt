package com.example.mashaal_stores.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(item1::class),version = 1)
abstract class Store5_Database : RoomDatabase()  {
    abstract fun itemdao():itemDao_store1

    companion object{
        @Volatile
        private var INSTANCE :Store5_Database?=null

        fun getdatabase(context: Context):Store5_Database{
            val tempinstance = INSTANCE
            if(tempinstance!=null){
                return tempinstance
            }
            val instance = Room.databaseBuilder(context,Store5_Database::class.java,"Store5_Items")
                .allowMainThreadQueries()
                .build()
            INSTANCE=instance

            return instance


        }

    }
}