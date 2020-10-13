package com.example.apirequest.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.apirequest.R
import com.example.apirequest.dao.LoginDao
import com.example.apirequest.dao.UpdateDao
import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.interactors.GetUserInteractor
import com.example.apirequest.interactors.LoginInteractor
import com.example.apirequest.interactors.UpdateUserInteractor
import java.text.SimpleDateFormat
import java.util.*

class LoginViewModel(
    private val context: Context,
    private val loginInteractor: LoginInteractor,
    private val getUserInteractor: GetUserInteractor,
    private val updateUserInteractor: UpdateUserInteractor
) : ViewModel() {

    val logInDao = SingleLiveEvent<LoginDao>()
    val updateDao = SingleLiveEvent<UpdateDao>()
//    val getSessionDao = SingleLiveEvent<GetSessionDao>()


    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    fun updateUser(userEntity: UserEntity){updateUserInteractor(userEntity){it.either(::handleUpdateFailure,::handleUpdateSuccess)}}
    private fun handleUpdateSuccess(int: Int){
        updateDao.value = UpdateDao.success(int)
    }
    private fun handleUpdateFailure(failure: Failure){updateDao.value = UpdateDao.error(failure.message)}

    fun login(email: String, password: String): Boolean {
        if (!validateData(email, password)) return false
        val loginSet = setOf(email, password)
        loginInteractor(loginSet) { it.either(::handleFailure, ::handleLogin) }
        return true
    }
    private fun handleFailure(failure: Failure) {logInDao.value = LoginDao.error(failure.message)}
    private fun handleLogin(userEntity: UserEntity) {
        logInDao.value = LoginDao.success(userEntity)
        activeSession(userEntity.email)
    }


    private fun validateData(email: String, password: String): Boolean { //TODO Is email correct
        if (email.isEmpty() || password.isEmpty()) return false
        return true
    }

    fun logOut() {
        inactiveSession()
    }

    fun isActiveSession(): Boolean = sharedPreferences.contains(context.getString(R.string.preference_key_user))

    private fun activeSession(token: String) {
        with(sharedPreferences.edit()) {
            putString(context.getString(R.string.preference_key_user), token)
            apply()//to write the data to disk asynchronously. Better than commit()
//          commit()//to write the data to disk synchronously. Avoid calling in main thread
        }
    }

    private fun inactiveSession() {
        with(sharedPreferences.edit()) {
            remove(context.getString(R.string.preference_key_user))
            apply()//to write the data to disk asynchronously. Better than commit()
        }
    }


    fun getUserSession() {
        sharedPreferences.getString(context.getString(R.string.preference_key_user), "")?.also { token ->
            getUserInteractor(token) { it.either(::handleGetSessionFailure, ::handleGetSession) }
        }
    }

    private fun handleGetSession(userEntity: UserEntity) {
//        getSessionDao.value = GetSessionDao.success(userEntity)
        logInDao.value = LoginDao.success(userEntity)
        logInDao.value?.userEntity?.lastLoginDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(object : Date(){})

    }

    private fun handleGetSessionFailure(failure: Failure) {
//        getSessionDao.value = GetSessionDao.error(failure.message)
        logInDao.value = LoginDao.error(failure.message)
    }

}