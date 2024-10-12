package com.example.ecomerce.views.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentAddItemBinding
import com.example.ecomerce.utils.ITEM_TABLE
import com.example.ecomerce.viewmodels.AddItemVM

class AddItemFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAddItemBinding? = null
    val binding get() = _binding
    val viewModel: AddItemVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize() {
        binding?.bar?.topBar?.setBackgroundColor(resources.getColor(R.color.admin))
        binding?.bar?.topBarText?.text = "Add Item"
        binding?.bar?.barBtn?.visibility = View.GONE
    }

    fun registerClicks() {
        binding?.btnItemAdd?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnItemAdd -> {
                viewModel.name = binding?.etItemName?.text.toString()
                viewModel.price = if (binding?.etItemPrice?.text.toString().isNotEmpty()) {
                    binding?.etItemPrice?.text.toString().toInt()
                } else {
                    0
                }
                viewModel.description = binding?.etItemDesc?.text.toString()

                if (viewModel.checkInput()) {
                    val item = viewModel.createItem()
                    ITEM_TABLE.child("${item.id}").setValue(item)
                        .addOnSuccessListener {
                            findNavController().navigate(AddItemFragmentDirections.actionAddItemFragmentToAdminDashBoardFragment())
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    viewModel.checkValues()
                    if (viewModel.nameError) {
                        binding?.etItemName?.error = "Please Enter Item Name"
                        binding?.etItemPrice?.error = "Please Enter Item Price"
                        binding?.etItemDesc?.error = "Please Enter Item Description"
                    }
                }
            }

        }
    }

}