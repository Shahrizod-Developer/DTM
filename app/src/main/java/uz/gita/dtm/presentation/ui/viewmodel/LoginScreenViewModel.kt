package uz.gita.dtm.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.dtm.data.models.auth.User

interface LoginScreenViewModel {
    val btnBackLiveData: LiveData<Unit>
    val messageLiveData: LiveData<Int>
    val openRegistrationScreen: LiveData<Unit>
    val openVerificationScreen: LiveData<Unit>
    val messageForPhoneNumber: LiveData<Int>
    val messageForPassword: LiveData<Int>
    val recaptchaQuestionLiveData: LiveData<String>

    fun btnBack()
    fun btnLogin(userData: User)
    fun openRegistrationScreen()
    fun checkRecaptcha(value: Int): Boolean
    fun restorePassword()
}