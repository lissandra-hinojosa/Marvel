package com.example.apirequest.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.apirequest.R
import com.example.apirequest.failure.Failure
import com.example.apirequest.utils.toast
import com.example.apirequest.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterUserFragment: Fragment(){

    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        observeInput()
        return inflater.inflate(R.layout.fragment_register, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        buttonRegister.setOnClickListener { validateData() }

    }

    private fun observeInput() {

        registerViewModel.registerDao.observe(viewLifecycleOwner, Observer { registered ->
            registered.error?.also {
                toast(it)
            }
            registered.identifier?.also {
                toast("Success")
            }
        })

        registerViewModel.errorName.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterName.error = error
            }
        })
        registerViewModel.errorLastName.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterLastName.error = error
            }
        })
        registerViewModel.errorEmail.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterEmail.error = error
            }
        })
        registerViewModel.errorPass.observe(viewLifecycleOwner, Observer { error ->
            error.also {
                inputRegisterPassword.error = error
                if(error.isEmpty())
                    inputConfirmPassword.error = error
                else
                    inputConfirmPassword.error = " "
            }
        })
    }

    private fun validateData(){
        registerViewModel.registerUser(
            inputRegisterNameText.text.toString().trimStart().trimEnd(),
            inputRegisterLastNameText.text.toString().trimStart().trimEnd(),
            inputRegisterEmailText.text.toString().trimStart().trimEnd(),
            inputRegisterPasswordText.text.toString().trimStart().trimEnd(),
            inputConfirmPasswordText.text.toString().trimStart().trimEnd())
    }

    private fun actionEnter(){
//        val action = RegisterUserFragmentDirections.registerUserAction(user)
//        findNavController().navigate(action)
    }
}