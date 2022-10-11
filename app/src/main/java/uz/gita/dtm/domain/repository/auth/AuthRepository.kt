package uz.gita.dtm.domain.repository.auth

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData

interface AuthRepository {

    suspend fun sendSmsCode(activity: Activity, phoneNumber: String): Flow<ResultData<Unit>>
    suspend fun verificationSmsCode(context: Context, password: String): Flow<ResultData<Unit>>
    suspend fun login(user: User): Flow<ResultData<Unit>>
    suspend fun restorePassword(phoneNumber: String): Flow<ResultData<Unit>>
    suspend fun setNewPassword(password: String): Flow<ResultData<Unit>>
}