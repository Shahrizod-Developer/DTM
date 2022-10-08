package uz.gita.dtm.domain.repository.auth

import android.app.Activity
import uz.gita.dtm.data.models.auth.User

interface AuthRepository {

    suspend fun getSmsCode(activity: Activity, phoneNumber: String)

    suspend fun login(user: User)

}