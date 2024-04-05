package com.example.noteapp.extensions

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import com.example.noteapp.presentation.factory.ViewModelFactoryHolder

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.injectVM(): Lazy<VM> {
    val holder = (this as? ViewModelFactoryHolder)
        ?: throw IllegalStateException("Activity should implement ViewModelFactoryHolder")
    return ViewModelLazy(VM::class, { viewModelStore }, { holder.viewModelFactory })
}

@MainThread
inline fun <reified VM: ViewModel> Fragment.injectVM(): Lazy<VM> {
    return ViewModelLazy(
        VM::class,
        { viewModelStore },
        {
            (activity as? ViewModelFactoryHolder) ?.viewModelFactory
        ?: throw IllegalArgumentException("Activity should implement ViewModelFactoryHolder")
    })
}

@MainThread
inline fun <reified VM: ViewModel> Fragment.injectActivityVM(): Lazy<VM> {
    return ViewModelLazy(
        VM::class,
        { requireActivity().viewModelStore },
        {
            (activity as? ViewModelFactoryHolder) ?.viewModelFactory
                ?: throw IllegalArgumentException("Activity should implement ViewModelFactoryHolder")
        }
    )
}
