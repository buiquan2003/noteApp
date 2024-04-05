package com.example.noteapp.presentation.factory

import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryHolder {
    val viewModelFactory: ViewModelProvider.Factory
}