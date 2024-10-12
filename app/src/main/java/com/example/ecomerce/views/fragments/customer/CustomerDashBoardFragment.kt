package com.example.ecomerce.views.fragments.customer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentCustomerDashBoardBinding
import com.example.ecomerce.listeners.CardClick
import com.example.ecomerce.models.Customer
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.CUSTOMER
import com.example.ecomerce.viewmodels.CustomerDashBoardVM
import com.example.ecomerce.views.adapters.ItemAdapterCustomer
import com.google.gson.Gson


class CustomerDashBoardFragment : Fragment() , View.OnClickListener , CardClick {

    private var _binding: FragmentCustomerDashBoardBinding? = null
    val binding get() = _binding
    val viewModel: CustomerDashBoardVM by viewModels()
    val args : CustomerDashBoardFragmentArgs  by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCustomerDashBoardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize() {
        val email = args.email
        viewModel.fetchCustomer(email)
        viewModel.list = viewModel.fetchItems()
        binding?.bar?.topBar?.setBackgroundColor(resources.getColor(R.color.customer))
        binding?.bar?.topBarText?.setText("Customer")
        if (viewModel.list.isEmpty()) {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    binding?.rvcItemMenu?.layoutManager = GridLayoutManager(requireContext(), 3)
                    binding?.rvcItemMenu?.adapter = ItemAdapterCustomer(requireContext(), viewModel.list , this)
                }, 5000
            )
        }

    }

    fun registerClicks() {
    }

    override fun onClick(p0: View?) {
    }

    override fun onItemClick(item: Item) {
        findNavController().navigate(CustomerDashBoardFragmentDirections
            .actionCustomerDashBoardFragmentToItemDetailFragment(Gson().toJson(item)))

    }
}