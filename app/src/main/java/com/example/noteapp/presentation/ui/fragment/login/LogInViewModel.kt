package com.example.noteapp.presentation.ui.fragment.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.local.pref.PreferenceHelper
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.domain.uscase.usecaseUserAuth.GetUserUseCase
import com.example.noteapp.domain.uscase.usecaseUserAuth.InsertUserUseCase
import com.example.noteapp.domain.uscase.usecaseUserAuth.SignInUserCase
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUserCase,
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertUserUseCase

): ViewModel() {

    val signInSuccess = MutableStateFlow(false)
    private val _showTextInCorrect = MutableStateFlow(false)
    val showTextInCorrect: Flow<Boolean> = _showTextInCorrect


    fun signIn(userAuth: UserAuth) {
        PreferenceHelper.getInstance().apply {
            userEmail = userAuth.email
            password = userAuth.password
        }
        if(userAuth.email.isNotEmpty() || userAuth.password.isNotEmpty()) {
            viewModelScope.launch {
                getUserUseCase().collect { list ->
                    val isExistedInDB = list.contains(UserAuth(
                        userAuth.email, userAuth.password,"", "", ""
                    ))
                    signInSuccess.value = isExistedInDB

                    if(!isExistedInDB) {
                        signInFirebase(userAuth)
                    }
                }
            }
        }
    }

    fun clearShowTextInCorrect() {
        _showTextInCorrect.value = false
    }

    private fun signInFirebase(userAuth: UserAuth) {
        viewModelScope.launch(Dispatchers.IO) {
            signInUseCase(userAuth).collect {result ->
                when (result) {
                    is Resource.Success -> {
                        signInSuccess.value = true

                        insertToDatabase(userAuth)

                        PreferenceHelper.getInstance().userEmail = userAuth.email
                        PreferenceHelper.getInstance().password = userAuth.password
                    } else -> _showTextInCorrect.value = true
                }
            }
        }
    }

    private fun insertToDatabase(user: UserAuth) {
        insertUserUseCase(user) {
            it.fold({

            }, {
                // Do nothing
            })
        }
    }

}