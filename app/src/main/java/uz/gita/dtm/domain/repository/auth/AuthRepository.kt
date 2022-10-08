package uz.gita.dtm.domain.repository.auth

import android.app.Activity
import android.content.Context
import uz.gita.dtm.data.models.auth.User

interface AuthRepository {

    suspend fun sendSmsCode(activity: Activity, phoneNumber: String)
    suspend fun verificationSmsCode(context: Context, password: String)
    suspend fun login(user: User)

}