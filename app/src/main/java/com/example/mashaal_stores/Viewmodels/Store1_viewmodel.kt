package com.example.mashaal_stores.Viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mashaal_stores.DataBase.*

import kotlinx.coroutines.launch

class Store1_viewmodel (application: Application):AndroidViewModel(application) {
  private val _allitems =MutableLiveData<List<item1>>()
    val allitems :LiveData<List<item1>> = _allitems
    val context =application

    private val _button_number = MutableLiveData<Int>()
    var button_number:LiveData<Int> = _button_number

     val x1 :MutableLiveData<Int> by lazy {
         MutableLiveData<Int>()
     }

    fun insert (data :item1){
        viewModelScope.launch {
            val db =Store1_Database2.getdatabase(context)
            db.itemdao().insert_item(data)
            Toast.makeText(context,"تم اضافة العنصر بنجاح ",Toast.LENGTH_LONG).show()
        }
    }
    fun insert_store2 (data :item1){
        viewModelScope.launch {
            val db = Store2_Database.getdatabase(context)
            db.itemdao().insert_item(data)
            Toast.makeText(context,"تم اضافة العنصر بنجاح ",Toast.LENGTH_LONG).show()
        }
    }
    fun insert_store3 (data :item1){
        viewModelScope.launch {
            val db = Store3_Database.getdatabase(context)
            db.itemdao().insert_item(data)
            Toast.makeText(context,"تم اضافة العنصر بنجاح ",Toast.LENGTH_LONG).show()
        }
    }
    fun insert_store4 (data :item1){
        viewModelScope.launch {
            val db = Store4_Database.getdatabase(context)
            db.itemdao().insert_item(data)
            Toast.makeText(context,"تم اضافة العنصر بنجاح ",Toast.LENGTH_LONG).show()
        }
    }
    fun insert_store5 (data :item1){
        viewModelScope.launch {
            val db = Store5_Database.getdatabase(context)
            db.itemdao().insert_item(data)
            Toast.makeText(context,"تم اضافة العنصر بنجاح ",Toast.LENGTH_LONG).show()
        }
    }
    fun update (data: item1){
        val db =Store1_Database2.getdatabase(context)
        viewModelScope.launch {
            db.itemdao().updatebyid(data.uid,data.name,data.type,data.date,data.ward,data.sadr,data.number)
        }
    }

    fun update_store2 (data: item1){
        val db =Store2_Database.getdatabase(context)
        viewModelScope.launch {
            db.itemdao().updatebyid(data.uid,data.name,data.type,data.date,data.ward,data.sadr,data.number)
        }
    }
    fun update_store3 (data: item1){
        val db =Store3_Database.getdatabase(context)
        viewModelScope.launch {
            db.itemdao().updatebyid(data.uid,data.name,data.type,data.date,data.ward,data.sadr,data.number)
        }
    }
    fun update_store4 (data: item1){
        val db =Store4_Database.getdatabase(context)
        viewModelScope.launch {
            db.itemdao().updatebyid(data.uid,data.name,data.type,data.date,data.ward,data.sadr,data.number)
        }
    }
    fun update_store5 (data: item1){
        val db =Store5_Database.getdatabase(context)
        viewModelScope.launch {
            db.itemdao().updatebyid(data.uid,data.name,data.type,data.date,data.ward,data.sadr,data.number)
        }
    }
    fun setbutton_number(num :Int){
        _button_number.value = num
    }

}