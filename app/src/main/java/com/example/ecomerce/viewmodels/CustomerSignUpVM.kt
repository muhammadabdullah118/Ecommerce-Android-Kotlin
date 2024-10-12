package com.example.ecomerce.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ecomerce.models.Customer
import com.example.ecomerce.utils.AppUtils
import com.example.ecomerce.utils.CUSTOMER
import kotlin.random.Random

class CustomerSignUpVM : ViewModel(){
    var firstName = ""
    var lastName = ""
    var phone = ""
    var email = ""
    var password = ""
    var confirmPassword=""
    var firstNameError = false
    var lastNameError = false
    var phoneError =  false
    var emailError = false
    var passwordError = false
    var confirmPasswordError = false

    fun  isEmptyFields():Boolean{
        return  firstName.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty()
                && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
                && (password == confirmPassword) && AppUtils.validPass(password)
                && AppUtils.isValidEmail(email) &&  validPhone()
    }

    fun validPhone(): Boolean{
        return phone.length >= 11
    }

    fun  checkValues(){
        firstNameError = firstName.isEmpty()
        lastNameError = lastName.isEmpty()
        phoneError = (phone.isEmpty() || !validPhone())
        emailError = (email.isEmpty() || !AppUtils.isValidEmail(email))
        passwordError = (password.isEmpty() || !AppUtils.validPass(password))
        confirmPasswordError = (confirmPassword.isEmpty() || !AppUtils.validPass(confirmPassword) || password==confirmPassword)
    }

    fun createCustomer() : Customer{
        val  id = Random.nextInt(1,1000)
        val customer = Customer(
            id = id,
            firstName = firstName,
            lastName =lastName,
            phone = phone,
            email = email,
            password = password,
            role = CUSTOMER
        )
        return customer
    }

}