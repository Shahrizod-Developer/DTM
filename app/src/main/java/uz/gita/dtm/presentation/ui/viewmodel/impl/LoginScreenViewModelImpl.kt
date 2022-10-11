package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCaseImpl
) : LoginScreenViewModel, ViewModel() {

    override val btnBackLiveData = MutableLiveData<Unit>()
    override val messageLiveData = MutableLiveData<Int>()
    override val openRegistrationScreen = MutableLiveData<Unit>()
    override val openMainScreen = MutableLiveData<Unit>()
    override val messageForPhoneNumber = MutableLiveData<Int>()
    override val messageForPassword = MutableLiveData<Int>()
    override val recaptchaQuestionLiveData = MutableLiveData<String>()


    override fun btnBack() {
        btnBackLiveData.value = Unit
    }

    private var answer = ""

    init {
        recaptchaQuestionLiveData.value = getRecaptcha()
    }

    override fun btnLogin(userData: User) {
        if (userData.phoneNumber.isEmpty()) {
            messageForPhoneNumber.value = R.string.no_number
        }
        if (userData.password.isEmpty()) {
            messageForPassword.value = R.string.no_password
        } else {
            viewModelScope.launch {
                authUseCase.login(userData).collectLatest {
                    when (it) {
                        is ResultData.Error -> {
                            messageLiveData.value = R.string.app_name
                        }
                        is ResultData.Success -> {
                            openMainScreen.value = Unit
                        }
                        is ResultData.Message -> {
                            messageLiveData.value = R.string.app_name
                        }
                    }
                }
            }
        }
    }

    override fun openRegistrationScreen() {
        openRegistrationScreen.value = Unit
    }

    override fun checkRecaptcha(value: String): Boolean {
        return value == answer
    }

    override fun restorePassword() {

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