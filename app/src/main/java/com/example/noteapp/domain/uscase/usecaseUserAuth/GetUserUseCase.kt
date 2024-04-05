package com.example.noteapp.domain.uscase.usecaseUserAuth

import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.domain.repository.UserAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    operator fun invoke(): Flow<List<UserAuth>> = userAuthRepository.getUserFromDB().flowOn(Dispatchers.IO)
}