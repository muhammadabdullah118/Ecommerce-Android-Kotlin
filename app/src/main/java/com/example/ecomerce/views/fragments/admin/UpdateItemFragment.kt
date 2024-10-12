package com.example.ecomerce.views.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentUpdateItemBinding
import com.example.ecomerce.models.Item
import com.example.ecomerce.utils.ITEM_TABLE
import com.example.ecomerce.viewmodels.UpdateItemVM
import com.google.gson.Gson


class UpdateItemFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentUpdateItemBinding? = null
    val binding get() = _binding
    val viewModel: UpdateItemVM by viewModels()
    val args: UpdateItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        registerClicks()
    }

    fun initialize() {
        viewModel.item = Gson().fromJson(args.items, Item::class.java)
        binding?.uetItemName?.setText(viewModel.item?.name)
        binding?.uetItemPrice?.setText(viewModel.item?.price.toString())
        binding?.uetItemDesc?.setText(viewModel.item?.description)
        binding?.bar?.topBar?.setBackgroundColor(resources.getColor(R.color.admin))
        binding?.bar?.topBarText?.text = "Update Item"
        binding?.bar?.barBtn?.visibility = View.GONE
    }

    fun registerClicks() {
        binding?.btnItemUpdate?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnItemUpdate -> {
                viewModel.name = binding?.uetItemName?.text.toString()
                viewModel.price = if (binding?.uetItemPrice?.text.toString().isNotEmpty()) {
                    binding?.uetItemPrice?.text.toString().toInt()
                } else {
                    0
                }
                viewModel.description = binding?.uetItemDesc?.text.toString()
                if (viewModel.checkInput()) {
                    val map = HashMap<String, Any>()
                    map["id"] = viewModel.item?.id ?: 0
                    map["name"] = viewModel.name
                    map["price"] = viewModel.price
                    map["description"] = viewModel.description
                    ITEM_TABLE.child("${viewModel.item?.id}").updateChildren(map)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(UpdateItemFragmentDirections.actionUpdateItemFragmentToAdminDashBoardFragment())
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show()
                        }


                } else {
                    viewModel.checkValues()
                    if (viewModel.nameError) {
                        binding?.uetItemName?.error = "Please Enter Item Name"
                        binding?.uetItemPrice?.error = "Please Enter Item Price"
                        binding?.uetItemDesc?.error = "Please Enter Item Description"
                    }


                }
            }
        }
    }
}