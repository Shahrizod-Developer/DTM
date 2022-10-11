package uz.gita.dtm.presentation.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import uz.gita.dtm.data.models.auth.User

interface RegistrationScreenViewModel {
    val btnBackLiveData: LiveData<Unit>
    val openVerificationScreen: LiveData<Unit>
    val recaptchaQuestionLiveData: LiveData<String>
    val messageForPhoneNumber: LiveData<Int>
    val messageForPassword: LiveData<Int>
    val messageLiveData: LiveData<String>

    fun btnBack()
    fun checkRecaptcha(value: String): Boolean
    fun btnRegister(activity: Activity, user: User)
}