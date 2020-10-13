package com.example.apirequest.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.apirequest.activities.LogInActivity
import com.example.apirequest.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

import android.content.Context
import android.content.DialogInterface
import com.example.apirequest.R
import com.example.apirequest.utils.toast
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import com.karumi.dexter.Dexter
import android.provider.MediaStore
//import sun.invoke.util.VerifyAccess.getPackageName
import androidx.core.content.FileProvider.getUriForFile
import java.nio.file.Files.exists
import android.net.Uri
import java.io.File
import android.graphics.Bitmap
import android.text.Editable
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.databinding.FragmentProfileBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_character_info.*


class ProfileFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private var selectedImageUri: Uri? = null
    private lateinit var userEntity: UserEntity
//    private lateinit var binding: FragmentProfileBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        setProfileUserInfo(binding)
        return binding.root
//        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun setProfileUserInfo(binding: FragmentProfileBinding) {
        loginViewModel.logInDao.observe(viewLifecycleOwner, Observer {
            it.userEntity?.also { user ->
                binding.name = user.name ?: "Default"
                binding.lastName = user.lastName ?: "Default"
                binding.email = user.email ?: "Default"
                val uri = user.profileImage.also { uri ->
                    Uri.parse(uri)
                }
                Glide.with(this)
                    .load(uri ?: user.profileImage)
                    .error(R.drawable.profile_placeholder)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profileImage)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observable()
    }

    private fun init() {
        loginViewModel.getUserSession() //TODO Bad performance
        buttonLogOut.setOnClickListener { logOut() }
        editButton.setOnClickListener { selectImage() }
        buttonSaveUser.setOnClickListener { saveUser() }
    }

    private fun saveUser() {
        loginViewModel.logInDao.value?.userEntity?.also { user ->
            user.name = profileUserName.editText?.text.toString()
            user.lastName = profileUserLastName.editText?.text.toString()
            loginViewModel.updateUser(user)
        }
    }

    private fun selectImage() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setGuidelines(CropImageView.Guidelines.OFF)
            .start(requireContext(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                Glide.with(this)
                    .load(resultUri)
                    .error(R.drawable.profile_placeholder)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profileImage)
                //Save user to database with updated profile picture
                loginViewModel.logInDao.value?.userEntity?.also { user ->
                    user.profileImage = resultUri.toString()
                    loginViewModel.updateUser(user)
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                toast(error.toString())
            }
        }
    }


    private fun observable() {
        loginViewModel.logInDao.observe(this, Observer { session ->
            session.error?.also {
                val dialog =
                    AlertDialog.Builder(this.context, com.example.apirequest.R.style.positive_dialog_button)
                dialog.setTitle(R.string.invalid_info)
                    .setMessage(it)
                    .setPositiveButton(R.string.ok) { _, _ -> }
                    .create()
                    .show()
            }
        })

        loginViewModel.updateDao.observe(viewLifecycleOwner, Observer { updated ->
            updated.error?.also {
                toast(it)
            }
            updated.int?.also {
                toast("Saved")
            }
        })
    }


    private fun logOut() {
        loginViewModel.logOut() //TODO End activity
        activity?.finish()
        intentLogIn()
    }


    private fun intentLogIn() {
        val action = Intent(activity, LogInActivity::class.java)
        action.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(action)
    }
}