package com.example.noteapp.presentation.ui.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.databinding.UserFragmentBinding
import com.example.noteapp.extensions.BaseFragment

class UserFragment: BaseFragment<UserFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): UserFragmentBinding =
        UserFragmentBinding.inflate(layoutInflater)
}