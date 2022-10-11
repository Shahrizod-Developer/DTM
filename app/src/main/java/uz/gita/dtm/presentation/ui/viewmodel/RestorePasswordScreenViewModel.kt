package uz.gita.dtm.presentation.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import uz.gita.dtm.data.models.auth.User

interface RestorePasswordScreenViewModel {
    val btnBackLiveData: LiveData<Unit>
    val messageLiveData: LiveData<Int>
    val openVerificationScreen: LiveData<Unit>
    val messageForPhoneNumber: LiveData<Int>
    val messageForPassword: LiveData<Int>
    val recaptchaQuestionLiveData: LiveData<String>

    fun btnBack()
    fun btnLogin(activity: Activity, userData: User)
    fun checkRecaptcha(value: String): Boolean

}