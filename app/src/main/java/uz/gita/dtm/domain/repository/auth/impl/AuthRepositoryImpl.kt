package uz.gita.dtm.domain.repository.auth.impl

import android.app.Activity
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.domain.repository.auth.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun getSmsCode(activity: Activity, phoneNumber: String) {
        TODO("Not yet implemented")
    }

    override suspend fun login(user: User) {
        TODO("Not yet implemented")
    }
}