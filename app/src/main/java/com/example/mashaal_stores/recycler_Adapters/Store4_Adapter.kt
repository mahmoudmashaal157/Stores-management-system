package com.example.mashaal_stores.recycler_Adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mashaal_stores.DataBase.item1
import com.example.mashaal_stores.R
import com.example.mashaal_stores.Store3.update_act_store3
import com.example.mashaal_stores.Store4.Store4
import com.example.mashaal_stores.Store4.Update_act_Store4

class Store4_Adapter (private val context: Store4, val dataset :MutableList<item1>
):
    RecyclerView.Adapter<Store4_Adapter.Viewholder>(), Filterable
{

    internal lateinit var tempdataset :MutableList<item1>
    init {
        this.tempdataset=dataset
        Log.v("dataset",tempdataset.size.toString())
    }
    inner  class Viewholder(private val view : View): RecyclerView.ViewHolder(view){
        val name : TextView =view.findViewById(R.id.name_of_item)
        val sadr: TextView =view.findViewById(R.id.sadr)
        val ward: TextView =view.findViewById(R.id.ward)
        val number : TextView =view.findViewById(R.id.number_of_item)
        val date: TextView =view.findViewById(R.id.date)
        val type: TextView =view.findViewById(R.id.type)

        init {

            view.setOnClickListener {
                Log.v("nameee",name.text.toString())
                // onclickListner.onclick(adapterPosition)
                var intent = Intent(view.context, Update_act_Store4::class.java)
                intent.putExtra("name",name.text.toString())
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val adapterlayout =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_of_items,parent,false)
        return Viewholder(adapterlayout)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = tempdataset[position]
        holder.name.text=item.name
        holder.number.text=item.number.toString()
        holder.sadr.text=item.sadr.toString()
        holder.ward.text=item.ward.toString()
        holder.type.text=item.type.toString()
        holder.date.text=item.date.toString()

    }

    override fun getItemCount(): Int {
        return tempdataset.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){

            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filterresult = FilterResults()

                if(p0==null || p0.isEmpty()){
                    tempdataset =dataset
                    filterresult.count = tempdataset.size
                    filterresult.values=tempdataset
                }
                else {
                    var searchChr :String =p0.toString().toLowerCase();
                    var itemmodal = ArrayList<item1>()
                    for (item in tempdataset){
                        if(item.name.toLowerCase().contains(searchChr))
                            itemmodal.add(item)
                        Log.v("nameee",item.toString())
                    }
                    filterresult.count=itemmodal.size
                    filterresult.values=itemmodal

                }
                Log.v("result",filterresult.values.toString())
                return filterresult
            }
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                tempdataset =p1!!.values as ArrayList<item1>
                Log.v("temp",tempdataset.toString())
                notifyDataSetChanged()
            }
        }
    }
}
