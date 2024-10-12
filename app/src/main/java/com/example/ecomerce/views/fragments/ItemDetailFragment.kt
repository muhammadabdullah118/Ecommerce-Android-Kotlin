package com.example.ecomerce.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentItemDetailBinding
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.CUSTOMER
import com.example.ecomerce.utils.Prefrences
import com.example.ecomerce.viewmodels.ItemDetailVM
import com.google.gson.Gson


class ItemDetailFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentItemDetailBinding ?= null
    val binding get() = _binding
    val args : ItemDetailFragmentArgs by navArgs()
    val viewModel : ItemDetailVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize(){
        viewModel.item = Gson().fromJson(args.item , Item :: class.java)
        if (Prefrences.getStringValue(CUSTOMER) ==  CUSTOMER){
            binding?.bar?.topBar?.setBackgroundColor(resources.getColor(R.color.customer))
            binding?.bar?.topBarText?.text = "Item Details"
            binding?.btnPlaceOrder?.text = "Place Order"
            binding?.btnPlaceOrder?.setBackgroundColor(resources.getColor(R.color.customer))
        }
        else{
            binding?.bar?.topBar?.setBackgroundColor(resources.getColor(R.color.admin))
            binding?.bar?.topBarText?.text = "Item Details"
            binding?.btnPlaceOrder?.text = "Update"
            binding?.btnPlaceOrder?.setBackgroundColor(resources.getColor(R.color.admin))
        }

        binding?.itemnameValue?.text = viewModel.item?.name
        binding?.itempriceValue?.text = viewModel.item?.price.toString()
        binding?.itemdescValue?.text = viewModel.item?.description

    }

    fun registerClicks(){
        binding?.bar?.barBtn?.setOnClickListener(this)
        binding?.btnPlaceOrder?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnPlaceOrder->{
                if (Prefrences.getStringValue(CUSTOMER) == CUSTOMER){
                    findNavController().navigate(ItemDetailFragmentDirections
                        .actionItemDetailFragmentToOrderPlaceFragment(args.item))
                }else{
                    findNavController().navigate(ItemDetailFragmentDirections
                        .actionItemDetailFragmentToUpdateItemFragment(args.item))
                }
            }
            R.id.barBtn->{
                findNavController().navigate(ItemDetailFragmentDirections.actionItemDetailFragmentToSettingFragment())
            }
        }
    }
}