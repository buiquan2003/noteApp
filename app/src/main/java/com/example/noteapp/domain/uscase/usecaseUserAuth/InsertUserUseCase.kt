package com.example.noteapp.domain.uscase.usecaseUserAuth

import com.example.noteapp.data.model.CustomFailure
import com.example.noteapp.data.model.Failure
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.domain.repository.UserAuthRepository
import com.example.noteapp.extensions.Either
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository

): UseCase<UserAuth, Unit>() {
    override suspend fun run(param: UserAuth): Either<Failure, Unit> {
       return try {
           Either.Success(userAuthRepository.insertToDB(param))
       } catch (e: Exception) {
           Either.Failure(CustomFailure(e))
       }
    }

}