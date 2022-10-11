package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.dtm.presentation.ui.viewmodel.RegistrationScreenViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RegistrationScreenViewModelImpl @Inject constructor(private val useCase: AuthUseCaseImpl) :
    RegistrationScreenViewModel,
    ViewModel() {
    override val btnBackLiveData = MutableLiveData<Unit>()
    override val openVerificationScreen = MutableLiveData<Unit>()
    override val recaptchaQuestionLiveData = MutableLiveData<String>()
    override val messageForPhoneNumber = MutableLiveData<Int>()
    override val messageForPassword = MutableLiveData<Int>()
    override val messageLiveData = MutableLiveData<String>()

    private var answer = ""

    init {
        recaptchaQuestionLiveData.value = getRecaptcha()
    }

    override fun btnBack() {
        btnBackLiveData.value = Unit
    }

    override fun checkRecaptcha(value: String): Boolean {
        return value == answer
    }

    override fun btnRegister(activity: Activity, user: User) {
        if (user.phoneNumber.isEmpty()) {
            messageForPhoneNumber.value = R.string.no_number
        }
        if (user.phoneNumber.isEmpty()) {
            messageForPassword.value = R.string.no_password
        } else {
            MySharedPreference.phoneNumber = user.phoneNumber
            MySharedPreference.password = user.password
            viewModelScope.launch {
                useCase.sendSmsCode(activity, user.phoneNumber).collectLatest {
                    when (it) {
                        is ResultData.Success -> {
                            openVerificationScreen.value = Unit
                        }
                        is ResultData.Message -> {
                            messageLiveData.value = it.message.toString()
                        }
                        is ResultData.Error -> {
                            messageLiveData.value = it.error.toString()
                        }
                    }
                }
            }
        }
    }

    private fun getRecaptcha(): String {
        val a = Random.nextInt(1, 10)
        val b = Random.nextInt(1, 10)
        val amals = arrayListOf<Char>('*', '-', '+')
        amals.shuffle()
        val amal = amals[1]
        var text = ""
        if (amal == '+') {
            text = "$a + $b = "
            answer = (a + b).toString()
        } else if (amal == '*') {
            text = "$a * $b = "
            answer = (a * b).toString()
        } else if (amal == '-') {
            if (a >= b) {
                text = "$a - $b = "
                answer = (a - b).toString()
            } else {
                text = "$b - $a = "
                answer = (b - a).toString()
            }
        }
        return text
    }
}