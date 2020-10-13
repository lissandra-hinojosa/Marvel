package com.example.apirequest.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apirequest.R
import com.example.apirequest.viewmodels.ValidateInputViewModel
import kotlinx.android.synthetic.main.fragment_reset_password.*
import org.koin.android.viewmodel.ext.android.viewModel

class ResetPasswordFragment : Fragment() {

    private val validateInputViewModel by viewModel<ValidateInputViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        buttonResetPass.setOnClickListener { validateEmail() }
    }

    private fun validateEmail() {
        if (!validateInputViewModel.validateEmail(textInputEmail.editText?.text.toString())) {
            val dialog = AlertDialog.Builder(this.context, R.style.positive_dialog_button)
                .setTitle(R.string.invalid_info)
                .setMessage(R.string.error_email)
                .setPositiveButton(R.string.ok) { _, _ -> }
                .create()
                .show()
        } else{
            val dialog = AlertDialog.Builder(this.context, R.style.positive_dialog_button)
                .setTitle(R.string.reset_password_tittle)
                .setMessage(R.string.reset_password_message)
                .setPositiveButton(R.string.ok) { _, _ -> }
                .create()
                .show()
        }
    }

}