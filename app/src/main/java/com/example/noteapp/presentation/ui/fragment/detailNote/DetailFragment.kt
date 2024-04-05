package com.example.noteapp.presentation.ui.fragment.detailNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.databinding.DetailFragmentBinding
import com.example.noteapp.extensions.BaseFragment

class DetailFragment: BaseFragment<DetailFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): DetailFragmentBinding = DetailFragmentBinding.inflate(layoutInflater)

}