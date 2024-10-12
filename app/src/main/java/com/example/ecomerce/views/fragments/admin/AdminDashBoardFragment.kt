package com.example.ecomerce.views.fragments.admin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentAdminDashBoardBinding
import com.example.ecomerce.listeners.ItemListener
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.ADMIN
import com.example.ecomerce.utils.CUSTOMER
import com.example.ecomerce.utils.itemList
import com.example.ecomerce.viewmodels.AdminDashBoardVM
import com.example.ecomerce.views.adapters.ItemAdapter
import com.example.ecomerce.views.fragments.customer.CustomerDashBoardFragmentDirections
import com.google.gson.Gson

class AdminDashBoardFragment : Fragment() , View.OnClickListener , ItemListener {

    private var _binding : FragmentAdminDashBoardBinding ?= null
    val binding get() = _binding
    val viewModel : AdminDashBoardVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminDashBoardBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize(){
        viewModel.list = viewModel.fetchItems()
        binding?.bar?.topBar?.setBackgroundColor(resources.getColor(R.color.admin))
        binding?.bar?.topBarText?.setText("Admin")
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if(viewModel.list.isNotEmpty()){
                    binding?.rvItemMenu?.layoutManager=LinearLayoutManager(requireContext())
                    val adapter = ItemAdapter(requireContext(), viewModel.list ,this )
                    binding?.rvItemMenu?.adapter = adapter
                    adapter.updateSetItemList(viewModel.list)
                }
            },
            5000
        )
    }

    fun registerClicks(){
        binding?.fbtnAddItem?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fbtnAddItem->{
                findNavController().navigate(AdminDashBoardFragmentDirections.actionAdminDashBoardFragmentToAddItemFragment())
            }
        }
    }

    override fun updateClick(item: Item) {
        findNavController().navigate(AdminDashBoardFragmentDirections.actionAdminDashBoardFragmentToUpdateItemFragment(
            Gson().toJson(item)
        ))
    }

    override fun onItemClick(item: Item) {
        findNavController().navigate(
            AdminDashBoardFragmentDirections
                .actionAdminDashBoardFragmentToItemDetailFragment(Gson().toJson(item) ))

    }


}