package com.example.ecomerce.views.fragments.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentOrderPlaceBinding
import com.example.ecomerce.models.Item
import com.example.ecomerce.viewmodels.OrderPlaceVM
import com.google.gson.Gson


class OrderPlaceFragment : Fragment() , View.OnClickListener {

    private var _binding : FragmentOrderPlaceBinding ?= null
    val binding get() = _binding
    val args : OrderPlaceFragmentArgs by navArgs()
    val viewModel : OrderPlaceVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderPlaceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize(){
        viewModel.item = Gson().fromJson(args.items , Item :: class.java)
        viewModel.price = viewModel.item?.price ?: 0
        viewModel.totalPrice = viewModel.item?.price ?: 0

        binding?.getName?.text = viewModel.item?.name
        binding?.getPrice?.text = viewModel.totalPrice.toString()
        binding?.getQuantity?.text = viewModel.quantity.toString()
    }

    fun registerClicks(){
        binding?.buttonMinus?.setOnClickListener(this)
        binding?.buttonPlus?.setOnClickListener(this)
        binding?.buttonConfirmOrder?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonMinus->{
                viewModel.subPrice()
                binding?.getQuantity?.text = viewModel.quantity.toString()
                binding?.getPrice?.text = viewModel.totalPrice.toString()
            }
            R.id.buttonPlus->{
                viewModel.addPrice()
                binding?.getQuantity?.text = viewModel.quantity.toString()
                binding?.getPrice?.text = viewModel.totalPrice.toString()
            }
        }
    }

}