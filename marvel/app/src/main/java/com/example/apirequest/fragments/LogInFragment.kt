package com.example.apirequest.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apirequest.R
import com.example.apirequest.activities.MainActivity
import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.databinding.FragmentLoginBinding
import com.example.apirequest.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LogInFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.apply {
            emailHint = getString(R.string.email)
            passwordHint = getString(R.string.password)
            clickLogin = loginListener
        }
        return binding.root //Returned View
//        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private val loginListener = View.OnClickListener {
        logIn()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
//        buttonLogIn.setOnClickListener { logIn() }
        textForgotPassword.setOnClickListener { actionResetPass() }
        textRegister.setOnClickListener { actionRegister() }
        observables()
    }

    private fun logIn(){
        if(!loginViewModel.login(textInputEmail.editText?.text.toString(),textInputPassword.editText?.text.toString())){
            val dialog = AlertDialog.Builder(this.context, R.style.positive_dialog_button)
            dialog.setTitle(R.string.invalid_info)
                .setMessage(R.string.wrong_info)
                .setPositiveButton(R.string.ok){ _, _ ->}
                .create()
                .show()
        }
    }

    private fun observables() {
        loginViewModel.logInDao.observe(viewLifecycleOwner, Observer { login ->
            login.error?.also {
                val dialog = AlertDialog.Builder(this.context, R.style.positive_dialog_button)
                dialog.setTitle(R.string.invalid_info)
                    .setMessage(R.string.incorrect_user)
                    .setPositiveButton(R.string.ok){ _, _ ->}
                    .create()
                    .show()
            }
            login.userEntity?.also { intentMarvel(it) }
        })
    }

    private fun actionResetPass() {
        val action = LogInFragmentDirections.resetPassAction()
        findNavController().navigate(action)
    }

    private fun actionRegister() {
        val action = LogInFragmentDirections.registerUserFragment()
        findNavController().navigate(action)
    }


    private fun intentMarvel(userEntity: UserEntity) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("user", userEntity)
        startActivityForResult(intent, 1) //TODO Clean use of request code
        activity?.finish()
    }



}
