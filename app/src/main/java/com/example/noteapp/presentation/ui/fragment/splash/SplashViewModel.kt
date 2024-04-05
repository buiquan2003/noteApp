package com.example.noteapp.presentation.ui.fragment.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor(
): ViewModel() {
    val isInSplash = MutableStateFlow(true)

    init {
        isInSplash.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(3000)
                isInSplash.value = false
            }
        }
    }
}