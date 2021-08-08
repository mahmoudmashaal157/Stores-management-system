package com.example.mashaal_stores.Store1

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.mashaal_stores.DataBase.Store1_Database2
import com.example.mashaal_stores.DataBase.item1
import com.example.mashaal_stores.R
import com.example.mashaal_stores.Viewmodels.Store1_viewmodel
import com.example.mashaal_stores.databinding.ActivityUpdateBinding
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class update_act : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding

    val viewmodel :Store1_viewmodel by viewModels()
    lateinit var item1 :item1
    lateinit var ss:String

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
            setspinneradapter()
        lifecycleScope.launch {

             ss =intent.getStringExtra("name").toString()
            Log.v("name1",ss)

            val db = Store1_Database2.getdatabase(applicationContext)
            try{
                item1 = db.itemdao().getallbyname(ss)
            }
            catch (E:Exception){
                Log.v("error in get" , E.toString())
            }
            Log.v("name1",item1.toString())
            binding.apply {
                nameUpdate.setText (item1.name)
                val w =item1.ward.toString()
                numberUpdate.setText(item1.number.toString())
                var pos =0
                when(item1.type) {
                    "كرتونه" -> {
                        pos = 0
                    }
                    "قطعه" -> {
                        pos = 1
                    }
                    "دستة" -> {
                        pos = 2
                    }
                    "ربطه" -> {
                        pos = 3
                    }
                }
                spinner2.setSelection(pos)

                updateButton.setOnClickListener {

                    val db = Store1_Database2.getdatabase(applicationContext)
                    try{
                        item1 = db.itemdao().getallbyname(ss)
                    }
                    catch (E:Exception){
                        Log.v("get error",E.toString())
                    }
                    var sadr =0.0
                    var ward =0.0
                    var update_flag=1

                    var flag:Int =0
                    Log.v("itemm",item1.toString())
                    var num = item1.number
                   if(wardUpdate.text.isNotEmpty() && sadrUpdate1.text.isEmpty()) {

                           sadr = item1.sadr
                           ward = wardUpdate.text.toString().toDouble()
                           num = num + ward
                           update_flag=1
                       numberUpdate.setText( num.toString())
                       Log.v("where1","sadr 0 , ward 1")


                   }
                    else if (sadrUpdate1.text.isNotEmpty() && wardUpdate.text.isEmpty()){
                       Log.v("where1","sadr 1 ,ward 0")
                        if(sadrUpdate1.text.toString().toDouble() > num ){
                            val builder = AlertDialog.Builder(this@update_act)
                            builder.setTitle("خطأ حسابي ")
                            builder.setMessage("لا يمكن ان يكون عدد الصادر اكبر من المتبقي")
                            builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
                            builder.show()
                            update_flag=0
                        }
                       else {
                           sadr = sadrUpdate1.text.toString().toDouble()
                            num = num - sadr
                            ward = item1.ward
                            numberUpdate.setText( num.toString())
                            update_flag=1
                        }

                   }
                    else if (wardUpdate.text.isNotEmpty() && sadrUpdate1.text.isNotEmpty()){
                       Log.v("where1","sadr 1 ,ward 1")

                       if(sadrUpdate1.text.toString().toDouble() > num + wardUpdate.text.toString().toDouble()){
                            val builder = AlertDialog.Builder(this@update_act)
                            builder.setTitle("خطأ حسابي ")
                            builder.setMessage(" لا يمكن ان يكون عدد الصادر اكبر من المتبقي و العدد الوارد")
                            builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
                            builder.show()
                            update_flag=0
                           flag =0
                        }
                       else{
                           ward = wardUpdate.text.toString().toDouble()
                            sadr = sadrUpdate1.text.toString().toDouble()
                            num = num+ward-sadr
                            numberUpdate.setText( num.toString())
                            update_flag=1
                        }
                   }
                    else {
                       Log.v("where1","sadr 0 ,ward 0")

                       sadr = item1.sadr
                       ward = item1.ward
                   }

                        val id = item1.uid.toString().toInt()
                        var ward_update = ward
                        var sadr_update = sadr

                        val name = nameUpdate.text.toString()
                        val curdate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        val type = spinner2.selectedItem.toString()

                   if(update_flag==1) {
                        try {
                            val item = item1(item1.uid,name,type,curdate,ward_update,sadr_update,num)
                            viewmodel.update(item)
                            ss= name

                            flag=1
                        }
                        catch (E:Exception){
                            Log.v("Excc",E.toString())

                        }
                    }
                    if(flag==1){
                        Toast.makeText(applicationContext,"تم التعديل بنجاح  ",Toast.LENGTH_SHORT).show()
                        flag=0
                    }
                    update_flag=1
                }
            }

        }
    }
    fun setspinneradapter (){
        val lst :MutableList<String> =ArrayList()
        lst.add("كرتونه")
        lst.add("قطعه")
        lst.add("دستة")
        lst.add("ربطه")
        val adapter :ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_text,lst)
        binding.spinner2.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_item ->{
                lifecycleScope.launch {
                    val db = Store1_Database2.getdatabase(applicationContext)
                    db.itemdao().delete(item1)
                    Toast.makeText(applicationContext,"تم المسح",Toast.LENGTH_SHORT).show()
                }
                name_update.setText("")
                number_update.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


