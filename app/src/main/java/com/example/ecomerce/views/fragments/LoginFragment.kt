package com.example.ecomerce.views.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentLoginBinding
import com.example.ecomerce.utils.AUTH
import com.example.ecomerce.utils.CUSTOMER
import com.example.ecomerce.utils.Prefrences
import com.example.ecomerce.viewmodels.LoginVM
import com.google.gson.Gson


class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    val binding get() = _binding
//    val args: LoginFragmentArgs by navArgs()
    val viewModel: LoginVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize() {
        if (Prefrences.getStringValue(CUSTOMER) == CUSTOMER) {
            binding?.tvLoginIcon?.setTextColor(resources.getColor(R.color.customer))
            binding?.btnLogin?.setBackgroundColor(resources.getColor(R.color.customer))
            binding?.signUpLayer?.visibility = View.VISIBLE
        } else {
            binding?.tvLoginIcon?.setTextColor(resources.getColor(R.color.admin))
            binding?.btnLogin?.setBackgroundColor(resources.getColor(R.color.admin))
            binding?.signUpLayer?.visibility = View.GONE
        }
    }

    fun registerClicks() {
        binding?.btnLogin?.setOnClickListener(this)
        binding?.signUpAcount?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btnLogin -> {
                viewModel.password = binding?.etPAssword?.text.toString()
                viewModel.email = binding?.etEmail?.text.toString()
                if (viewModel.checkInput()) {
                    AUTH.signInWithEmailAndPassword(viewModel.email, viewModel.password)
                        .addOnSuccessListener {

//                            val userID = AUTH.currentUser?.uid
//                            val userStatus =

                            if (Prefrences.getStringValue(CUSTOMER) == CUSTOMER) {
//                                findNavController().popBackStack()
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCustomerDashBoardFragment(viewModel.email
                                ))
                            } else {
//                                findNavController().popBackStack()
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAdminDashBoardFragment())

                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, " ${it.message}",Toast.LENGTH_SHORT).show()
                        }
                } else {
                    viewModel.checkValues()
                    if (viewModel.emailError) {
                        binding?.etEmail?.error = "Please enter valid Email"
                    }
                    if (viewModel.passwordError) {
                        binding?.etPAssword?.error = "Please enter valid Password"
                    }
                }
            }

            R.id.signUpAcount -> {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
            }
        }
    }
}