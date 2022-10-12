package uz.gita.dtm.presentation.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User

interface RegistrationScreenViewModel {


    val recaptchaQuestionLiveData: Flow<String>
    val messageForPhoneNumber: Flow<Int>
    val messageForPassword: Flow<Int>
    val messageLiveData: Flow<Int>
    val loadingLiveData: Flow<Boolean>

    fun btnBack()
    fun checkRecaptcha(value: String): Boolean
    fun btnRegister(activity: Activity, user: User)
}