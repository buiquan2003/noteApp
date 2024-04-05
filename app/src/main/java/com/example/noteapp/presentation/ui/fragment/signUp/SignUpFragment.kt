package com.example.noteapp.presentation.ui.fragment.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.databinding.SignUpFragmentBinding
import com.example.noteapp.extensions.BaseFragment
import com.example.noteapp.extensions.coroutine.launchWhenStarted
import com.example.noteapp.extensions.injectVM
import com.example.noteapp.utils.Utils

class SignUpFragment: BaseFragment<SignUpFragmentBinding>() {

    private val viewModel: SignUpViewModel by injectVM()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickBack()

        clickButtonSignUp()

        launchWhenStarted {
            viewModel.isSignUpSuccess.collect {
                if(it) {
                    Toast.makeText(requireContext(),"sign up successfully.", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun clickButtonSignUp() {
        binding.apply {
            btnOk.setOnClickListener {
                if(!Utils.validPassword(emailSigUp.text.toString(), passwordSignUp.text.toString(), edtConfirmPassword.text.toString())){
                    Toast.makeText(requireContext(),"please enter email and password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (isNetworkConnected) {
                    viewModel.register(
                        UserAuth(
                            emailSigUp.text.toString(),
                            passwordSignUp.text.toString(),
                            "",
                            "",
                            ""
                        )
                    )
                }
            }
        }
    }

    private fun clickBack() {
        binding.appImageSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): SignUpFragmentBinding =
        SignUpFragmentBinding.inflate(layoutInflater)
}