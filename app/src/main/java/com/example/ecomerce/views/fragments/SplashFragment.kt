package com.example.ecomerce.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentSplashBinding
import com.example.ecomerce.utils.ADMIN
import com.example.ecomerce.utils.CUSTOMER
import com.example.ecomerce.utils.Prefrences
import com.example.ecomerce.utils.ROLE


class SplashFragment : Fragment()  , View.OnClickListener {

    private var _binding : FragmentSplashBinding ?= null
    val binding get() = _binding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      _binding = FragmentSplashBinding.inflate(inflater,container,false)
        return  binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun  initialize(){

    }

    fun  registerClicks(){
        binding?.btnAdmin?.setOnClickListener(this)
        binding?.btnCustomer?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAdmin->{
                Prefrences.setStringValue(ROLE, ADMIN)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
            R.id.btnCustomer->{
                Prefrences.setStringValue(ROLE, CUSTOMER)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        }
    }
}