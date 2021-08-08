package com.example.mashaal_stores.Store1

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mashaal_stores.DataBase.Store1_Database2
import com.example.mashaal_stores.DataBase.item1
import com.example.mashaal_stores.R
import com.example.mashaal_stores.databinding.FragmentShakaStoreBinding
import com.example.mashaal_stores.recycler_Adapters.shaka_recycler_Adapter
import kotlinx.coroutines.launch



 class Shaka_Store : Fragment() {

    private lateinit var binding: FragmentShakaStoreBinding
    lateinit var dataset :MutableList<item1>
    lateinit var recycler :RecyclerView
    lateinit var adapter: shaka_recycler_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShakaStoreBinding.inflate(inflater,container,false)

        recycler =binding.shakaRecycler
        dataset= mutableListOf()

      viewLifecycleOwner.lifecycleScope.launch {
            val db = activity?.let { Store1_Database2.getdatabase(it?.applicationContext) }
            db?.itemdao()?.getAll()?.let { dataset.addAll(it) }
            adapter =shaka_recycler_Adapter(Shaka_Store(),dataset)
            recycler.adapter=adapter


          binding.swipeRefresh.setOnRefreshListener {
              dataset.clear()
              recycler.adapter = shaka_recycler_Adapter(Shaka_Store(),dataset)
              val db = activity?.let { Store1_Database2.getdatabase(it?.applicationContext) }
              db?.itemdao()?.getAll()?.let { dataset.addAll(it) }
              adapter =shaka_recycler_Adapter(Shaka_Store(),dataset)
              recycler.adapter=adapter
              binding.swipeRefresh.setRefreshing(false)
          }

        }
            recycler.addItemDecoration(DividerItemDecoration(
                context,LinearLayoutManager.HORIZONTAL
            ))
            recycler.setHasFixedSize(true)
            setHasOptionsMenu(true)

        return binding.root
       // return inflater.inflate(R.layout.fragment_shaka__store, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_item,menu)
        var menuittem =menu!!.findItem(R.id.search)
        val searchview =menuittem.actionView as SearchView
        searchview.maxWidth = Int.MAX_VALUE

        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
               // adapter.filter.filter(p0)
              return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter!!.filter.filter(p0)
                Log.v("worrd",p0.toString())
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item->{
                findNavController().navigate(R.id.action_shaka_Store_to_insert_item)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
