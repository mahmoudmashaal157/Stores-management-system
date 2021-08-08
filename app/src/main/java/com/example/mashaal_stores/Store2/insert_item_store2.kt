package com.example.mashaal_stores.Store2

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mashaal_stores.DataBase.item1
import com.example.mashaal_stores.R
import com.example.mashaal_stores.Viewmodels.Store1_viewmodel
import com.example.mashaal_stores.databinding.FragmentInsertItemStore2Binding
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class insert_item_store2 : Fragment() {
    private lateinit var binding :FragmentInsertItemStore2Binding
    private val viewmodel : Store1_viewmodel by activityViewModels()
    val c=0
    val positiveButtonClick = { dialog: DialogInterface, which: Int -> }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInsertItemStore2Binding.inflate(inflater,container,false)
        val lst :MutableList<String> =ArrayList()
        lst.add("كرتونه")
        lst.add("قطعه")
        lst.add("دستة")
        lst.add("ربطه")

        val adapter : ArrayAdapter<String> = activity?.let {
            ArrayAdapter(
                it.applicationContext,R.layout.spinner_text,
                lst)
        }!!
        binding.spinner.adapter=adapter

        binding.button.setOnClickListener {
            binding.apply {
                if(checknotnull()){
                    val name = nameInsert.text.toString()
                    val num = numInsert.text.toString().toDouble()
                    val type = spinner.selectedItem.toString()
                    val sadr = sadrInsert.text.toString().toDouble()
                    val curdate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    if(num < sadr){
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setTitle("خطأ حسابي ")
                        builder.setMessage("لا يمكن ان يكون عدد الصادر اكبر من الوارد")
                        builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
                        builder.show()
                    }
                    else {
                        try{
                            lifecycleScope.launch {
                                viewmodel.insert_store2(item1(0, name, type, curdate, num, sadr, num - sadr))
                                nameInsert.setText("")
                                numInsert.setText("")
                                sadrInsert.setText("")
                            }

                        }
                        catch (E :Exception){
                            val builder = AlertDialog.Builder(requireContext())
                            builder.setTitle("error in database ")
                            builder.setMessage("لديك مشكله في الداتا بيز")
                            builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
                        }
                    }
                }
            }
        }
        return binding.root
    }
    fun checknotnull():Boolean{
        var c :Int =0
        binding.apply {
            if(nameInsert.text?.isEmpty() == true){
                nameInsert.setError("يجب ملئ هذا المكان")
                c++
            }
            if(numInsert.text!!.isEmpty()){
                numInsert.setError("يجب ملئ هذا المكان")
                c++
            }
            if(sadrInsert.text!!.isEmpty()){
                sadrInsert.setError("يجب ملئ هذا المكان")
                c++
            }


        }
        if(c==0)return true
        else return false
    }
}