package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor() : LoginScreenViewModel, ViewModel() {

    override val messageLiveData = MutableLiveData<Int>()
    override val openRegistrationScreen = MutableLiveData<Unit>()
    override val messageForPhoneNumber = MutableLiveData<Int>()
    override val messageForPassword = MutableLiveData<Int>()
    override val recaptchaQuestionLiveData = MutableLiveData<String>()

    private var answer by Delegates.notNull<Int>()

    init {
        recaptchaQuestionLiveData.value = getRecaptcha()
    }

    override fun btnLogin(activity: Activity, phoneNumber: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun openRegistrationScreen() {
        openRegistrationScreen.value = Unit
    }

    override fun checkRecaptcha(value: Int): Boolean {
        return value == answer
    }

    fun getRecaptcha(): String {
        answer = 2
        return "1 + 1 = "
    }
}