package com.example.noteapp.presentation.ui.fragment.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.domain.uscase.usecaseUserAuth.SignUpUseCase
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {

     val isSignUpSuccess = MutableStateFlow(false)

    fun register(userAuth: UserAuth) {
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase(userAuth).collect { resutl ->
                when (resutl) {
                    is Resource.Success -> isSignUpSuccess.value = true
                    else -> isSignUpSuccess.value = false
                }
            }
        }
    }
}