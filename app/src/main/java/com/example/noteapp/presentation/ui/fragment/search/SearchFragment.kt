package com.example.noteapp.presentation.ui.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.noteapp.databinding.SearchFragmentBinding
import com.example.noteapp.extensions.BaseFragment

class SearchFragment: BaseFragment<SearchFragmentBinding>() {
    override fun getBinding( inflater: LayoutInflater, container: ViewGroup?): SearchFragmentBinding =
        SearchFragmentBinding.inflate(layoutInflater)
}