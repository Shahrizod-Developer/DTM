package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User

interface LoginScreenViewModel {
    val btnBackLiveData: Flow<Unit>
    val messageLiveData: Flow<String>
    val openRegistrationScreen: Flow<Unit>
    val openMainScreen: Flow<Unit>
    val messageForPhoneNumber: Flow<Int>
    val messageForPassword: Flow<Int>
    val recaptchaQuestionLiveData: Flow<String>
    val loadingLiveData: Flow<Boolean>

    fun btnBack()
    fun btnLogin(userData: User)
    fun openRegistrationScreen()
    fun checkRecaptcha(value: String): Boolean
    fun restorePassword()
}