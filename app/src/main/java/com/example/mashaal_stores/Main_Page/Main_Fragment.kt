package com.example.mashaal_stores.Main_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mashaal_stores.R
import com.example.mashaal_stores.Viewmodels.Store1_viewmodel
import com.example.mashaal_stores.databinding.FragmentMainBinding

class Main_Fragment : Fragment() {
   private lateinit var binding :FragmentMainBinding
   private val viewmodel : Store1_viewmodel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val  binding1 = FragmentMainBinding.inflate(inflater,container,false)
        binding=binding1
        binding.shakaStoreButton.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_shaka_Store)
            viewmodel.setbutton_number(1)
        }
        binding.store2.setOnClickListener {
            viewmodel.setbutton_number(2)
            findNavController().navigate(R.id.action_main_Fragment_to_store22)
        }
        binding.store3Button.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_store3)
        }
        binding.store4Button.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_store4)
        }
        binding.buttonStore5.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_store5)
        }

        return binding.root
    }


}