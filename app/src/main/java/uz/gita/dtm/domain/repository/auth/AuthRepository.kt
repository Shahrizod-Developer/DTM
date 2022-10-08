package uz.gita.dtm.domain.repository.auth

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData

interface AuthRepository {

    suspend fun getSmsCode(activity: Activity, phoneNumber: String)

    suspend fun login(user: User)
}