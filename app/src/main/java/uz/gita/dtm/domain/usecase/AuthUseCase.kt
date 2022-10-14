package uz.gita.dtm.domain.usecase

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData


interface AuthUseCase {

    fun sendSmsCode(activity: Activity, phoneNumber: String): Flow<ResultData<Unit>>

    fun verificationSmsCode(context: Context, password: String): Flow<ResultData<Unit>>

    fun login(user: User): Flow<ResultData<Unit>>

    fun restorePassword(phoneNumber: String): Flow<ResultData<Unit>>

    fun setNewPassword(password: String): Flow<ResultData<Unit>>

    fun userPresence(): Flow<Boolean>
}