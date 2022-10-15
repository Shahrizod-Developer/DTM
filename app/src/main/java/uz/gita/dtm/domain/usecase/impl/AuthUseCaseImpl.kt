package uz.gita.dtm.domain.usecase.impl

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.domain.repository.auth.impl.AuthRepositoryImpl
import uz.gita.dtm.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepositoryImpl
) : AuthUseCase {
    override fun sendSmsCode(activity: Activity, phoneNumber: String) =
        repository.sendSmsCode(activity, phoneNumber)

    override fun verificationSmsCode(context: Context, password: String) =
        repository.verificationSmsCode(context, password)

    override fun login(user: User) = repository.login(user)

    override fun restorePassword(phoneNumber: String) =
        repository.restorePassword(phoneNumber)

    override fun setNewPassword(password: String) = repository.setNewPassword(password)

    override fun userPresence(): Flow<Boolean> = repository.userPresence()

    override fun applicantPresence(): Flow<Boolean> = repository.applicantPresence()
}