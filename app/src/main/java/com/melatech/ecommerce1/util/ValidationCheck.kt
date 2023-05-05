package com.melatech.ecommerce1.util

import android.util.Log
import android.util.Patterns

fun validateEmail(email: String): RegisterValidation{
    if (email.isEmpty()){
        Log.d("jason", "validateEmail -> mail must not be empty")
        return RegisterValidation.Failed("Email must not be empty")
    }


    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        Log.d("jason", "validateEmail -> Wrong email format")
        return RegisterValidation.Failed("Wrong email format")
    }


    return RegisterValidation.Success

}

fun validatePassword(password: String): RegisterValidation{
    if (password.isEmpty()){
        Log.d("jason", "validatePassword -> Password must not be empty")
        return RegisterValidation.Failed("Password must not be empty")
    }

    if (password.length < 6){
        Log.d("jason", "validatePassword -> assword should contain 6 char")
        return RegisterValidation.Failed("Password should contain 6 char")

    }

    return RegisterValidation.Success

}