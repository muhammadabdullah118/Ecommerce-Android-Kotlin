package com.example.ecomerce.views.fragments.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentSignUpBinding
import com.example.ecomerce.models.Customer
import com.example.ecomerce.utils.AUTH
import com.example.ecomerce.utils.CUSTOMER
import com.example.ecomerce.utils.CUSTOMER_TABLE
import com.example.ecomerce.viewmodels.CustomerSignUpVM
import kotlin.random.Random


class SignUpFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentSignUpBinding? = null
    val binding get() = _binding
    val viewModel: CustomerSignUpVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize() {

    }

    fun registerClicks() {
        binding?.btnSignUp?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSignUp -> {
                viewModel.firstName = binding?.etFname?.text.toString()
                viewModel.lastName = binding?.etLname?.text.toString()
                viewModel.phone = binding?.etPhoneNo?.text.toString()
                viewModel.email = binding?.etEmail?.text.toString()
                viewModel.password = binding?.etPassword?.text.toString()
                viewModel.confirmPassword = binding?.etConfirmPassword?.text.toString()

                if (viewModel.isEmptyFields()) {
                    AUTH.createUserWithEmailAndPassword(viewModel.email, viewModel.password)
                        .addOnSuccessListener {
                            val customer = viewModel.createCustomer()
                            CUSTOMER_TABLE.child("${customer.id}").setValue(customer)
                                .addOnSuccessListener {
                                    findNavController().navigate(
                                        SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                                    )
                                    Toast.makeText(context, " Please Login", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Error! : Check Credentials",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, " Cannot Create a User", Toast.LENGTH_SHORT)
                                .show()
                        }
                } else {
                    viewModel.checkValues()
                    if (viewModel.firstNameError) {
                        binding?.etFname?.error = " Please Enter First Name"
                    }
                    if (viewModel.lastNameError) {
                        binding?.etLname?.error = " Please Enter Second Name"
                    }
                    if (viewModel.emailError) {
                        binding?.etEmail?.error = " Please Enter Email"
                    }
                    if (viewModel.phoneError) {
                        binding?.etPhoneNo?.error = " Please Enter Phone Number"
                    }
                    if (viewModel.passwordError) {
                        binding?.etPassword?.error = " Please Enter Password"
                    }
                    if (viewModel.confirmPasswordError) {
                        binding?.etConfirmPassword?.error = "Please Enter Confirm Password"
                    }

                }
            }

        }
    }


}