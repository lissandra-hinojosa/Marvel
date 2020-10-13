package com.example.apirequest.viewmodels

import android.text.TextUtils
import androidx.lifecycle.ViewModel

class ValidateInputViewModel(): ViewModel() {

    fun validateEmail(email: String): Boolean{
        return !(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
}