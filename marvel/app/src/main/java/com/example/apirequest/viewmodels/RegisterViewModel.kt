package com.example.apirequest.viewmodels

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apirequest.dao.RegisterDao
import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.interactors.RegisterInteractor
import com.example.apirequest.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(private val registerInteractor: RegisterInteractor) : ViewModel() {
    var errorName = MutableLiveData<String>()
    var errorLastName = MutableLiveData<String>()
    var errorEmail = MutableLiveData<String>()
    var errorPass = MutableLiveData<String>()

    val registerDao = MutableLiveData<RegisterDao>()

    private fun validate(name: String, lastName: String, email: String, pass: String, confirmPass: String): Boolean {
        val v = arrayListOf<Boolean>(
            validateName(name),
            validateLastName(lastName),
            validateEmail(email),
            validatePassword(pass, confirmPass))
        //Accumulates the results in the array and returns it
        v.reduce(({ acc, b -> acc && b })).let { validInfo ->
            return validInfo
        }
    }

    fun registerUser(name: String, lastName: String, email: String, pass: String, confirmPass: String): Boolean{
        if(validate(name,lastName,email,pass,confirmPass)){
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(object : Date(){})
            println(date)
            val userEntity = UserEntity(email,name,lastName,pass,date)
            registerInteractor(userEntity) { it.either(::handleFailure, ::handleRegister)}
            return true
        }
        return false
    }

    private fun handleFailure(failure: Failure){
//        println(failure.message)
        registerDao.value = RegisterDao.error(failure.message)

    }
    private fun handleRegister(long: Long){
//        println("Success")
        registerDao.value = RegisterDao.success(long)
    }

    private fun validateName(name: String): Boolean {
//        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName))
        return when {
            TextUtils.isEmpty(name) -> {
                errorName.value = " "
                false
            }
            else -> {
                errorName.value = ""
                true
            }
        }
    }

    private fun validateLastName(lastName: String): Boolean {
//        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName))
        return when {
            TextUtils.isEmpty(lastName) -> {
                errorLastName.value = " "
                false
            }
            else -> {
                errorLastName.value = ""
                true
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorEmail.value = " "
            false
        } else {
            errorEmail.value = ""
            true
        }
    }

    private fun validatePassword(pass: String, confirmPass: String): Boolean {
        return if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass)) {
            errorPass.value = "Missing"
            false
        } else if (!TextUtils.equals(pass, confirmPass)) {
            errorPass.value = "Passwords don't match"
            false
        } else {
            errorPass.value = ""
            true
        }
    }
}