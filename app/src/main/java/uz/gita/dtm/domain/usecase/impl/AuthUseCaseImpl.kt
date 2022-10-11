package uz.gita.dtm.domain.usecase.impl

import android.app.Activity
import android.content.Context
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.domain.repository.auth.impl.AuthRepositoryImpl
import uz.gita.dtm.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepositoryImpl
) : AuthUseCase {
    override suspend fun sendSmsCode(activity: Activity, phoneNumber: String) =
        repository.sendSmsCode(activity, phoneNumber)

    override suspend fun verificationSmsCode(context: Context, password: String) =
        repository.verificationSmsCode(context, password)

    override suspend fun login(user: User) = repository.login(user)

    override suspend fun restorePassword(phoneNumber: String) =
        repository.restorePassword(phoneNumber)

    override suspend fun setNewPassword(password: String) = repository.setNewPassword(password)
}