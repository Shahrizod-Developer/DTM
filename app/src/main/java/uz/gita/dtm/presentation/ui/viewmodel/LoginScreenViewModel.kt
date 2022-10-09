package uz.gita.dtm.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData

interface LoginScreenViewModel {
    val messageLiveData: LiveData<Int>
    val openRegistrationScreen: LiveData<Unit>
    val messageForPhoneNumber: LiveData<Int>
    val messageForPassword: LiveData<Int>
    val recaptchaQuestionLiveData: LiveData<String>

    fun btnLogin(userData: User)
    fun openRegistrationScreen()
    fun checkRecaptcha(value: Int): Boolean
}