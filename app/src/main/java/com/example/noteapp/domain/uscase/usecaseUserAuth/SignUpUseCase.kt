package com.example.noteapp.domain.uscase.usecaseUserAuth

import androidx.lifecycle.ViewModel
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.domain.repository.UserAuthRepository
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    operator fun  invoke(userAuth: UserAuth): Flow<Resource<Boolean>> = userAuthRepository.signUp(userAuth).flowOn(Dispatchers.IO)
}