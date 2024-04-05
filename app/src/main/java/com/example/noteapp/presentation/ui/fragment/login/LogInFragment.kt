package com.example.noteapp.presentation.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.databinding.LoginFragmentBinding
import com.example.noteapp.extensions.BaseFragment
import com.example.noteapp.extensions.coroutine.launchWhenCreate
import com.example.noteapp.extensions.coroutine.launchWhenStarted
import com.example.noteapp.extensions.injectVM

class LogInFragment: BaseFragment<LoginFragmentBinding>() {

    private val viewModel: LoginViewModel by injectVM()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSignUp()

        clickButtonLogIn()

        launchWhenStarted {
            viewModel.signInSuccess.collect{
                if (it) {
                    findNavController().navigate(R.id.action_logInFragment_to_homeFragment2)
                } else if (binding.email.text.toString().isNotEmpty() || binding.password.text.toString().isNotEmpty()) {
                    // do nothing
                }
            }
        }

        launchWhenCreate {
            viewModel.showTextInCorrect.collect { isShow ->
                if(isShow){
                    Toast.makeText(requireContext(),R.string.sign_In_Failed_gmail_password,Toast.LENGTH_SHORT).show()
                    viewModel.clearShowTextInCorrect()
                }
            }
        }
    }

    private fun clickButtonLogIn() {
        binding.apply {
            btnLogIn.setOnClickListener {
                if (email.text.toString().isEmpty() || password.text.toString().isEmpty()){
                    Toast.makeText(requireContext(), R.string.please_enter_your_email_and_password, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (isNetworkConnected) {
                    viewModel.signIn(
                        UserAuth(
                            email.text.toString(),
                            password.text.toString(),
                            "",
                            "",
                            ""
                        )
                    )
                }
            }
        }
    }

    private fun clickSignUp() {
        binding.textSignUpState.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): LoginFragmentBinding =
        LoginFragmentBinding.inflate(layoutInflater)
}