package com.example.mashaal_stores.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class item1(
    @PrimaryKey (autoGenerate = true) val uid :Int,
    @ColumnInfo(name="name") var name:String,
    @ColumnInfo(name="type") var type:String,
    @ColumnInfo(name="date") var date:String,
    @ColumnInfo (name="ward") var ward:Double,
    @ColumnInfo (name="sadr") var sadr:Double,
    @ColumnInfo (name="number") var number:Double
)
