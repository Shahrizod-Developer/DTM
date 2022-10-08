package uz.gita.dtm.presentation.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData

interface LoginScreenViewModel {
    val messageLiveData: LiveData<Int>
    val openRegistrationScreen: LiveData<Unit>
    val messageForPhoneNumber: LiveData<Int>
    val messageForPassword: LiveData<Int>
    val recaptchaQuestionLiveData: LiveData<String>

    fun btnLogin(activity: Activity, phoneNumber: String, password: String)
    fun openRegistrationScreen()
    fun checkRecaptcha(value: Int): Boolean

}