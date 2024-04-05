package com.example.noteapp.presentation.ui.fragment.calender

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.CalenderFragmentBinding
import com.example.noteapp.extensions.BaseFragment
import com.example.noteapp.presentation.ui.fragment.home.HomeFragment

class CalenderFragment: BaseFragment<CalenderFragmentBinding>() {

    override fun getBinding( inflater: LayoutInflater, container: ViewGroup?): CalenderFragmentBinding =
        CalenderFragmentBinding.inflate(layoutInflater)
}