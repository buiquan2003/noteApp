package com.example.noteapp.presentation.ui.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.SplashFragmentBinding
import com.example.noteapp.extensions.BaseFragment
import com.example.noteapp.extensions.coroutine.launchWhenCreate
import com.example.noteapp.extensions.injectActivityVM
import com.example.noteapp.extensions.injectVM
import com.example.noteapp.presentation.ui.activity.MainViewModel

class Splash: BaseFragment<SplashFragmentBinding>() {

    private val shareMainViewModel: MainViewModel by injectActivityVM()

    private val viewModel: SplashViewModel by injectVM()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchWhenCreate {
            viewModel.isInSplash.collect {
                if (!it) {
                    if (shareMainViewModel.userExisted.value) {
                        findNavController().navigate(R.id.action_splash_to_homeFragment2)
                    } else {
                        findNavController().navigate(R.id.action_splash_to_logInFragment)
                    }
                }
            }
        }
    }
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): SplashFragmentBinding =
        SplashFragmentBinding.inflate(layoutInflater)
}