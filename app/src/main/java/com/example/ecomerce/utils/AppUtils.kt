package com.example.ecomerce.utils

import android.util.Patterns
import java.util.regex.Pattern

object AppUtils {

    fun validPass(password : String): Boolean{
        return password.length >= 8
    }


    fun isValidEmail(email : String): Boolean {
        val pattern : Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}