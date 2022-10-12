package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.auth.LoginScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val authUseCase: AuthUseCaseImpl
) : LoginScreenViewModel, ViewModel() {

    override val messageLiveData = MutableSharedFlow<Int>()
    override val messageForPhoneNumber = MutableSharedFlow<Int>()
    override val messageForPassword = MutableSharedFlow<Int>()
    override val recaptchaQuestionLiveData = MutableStateFlow("")
    override val loadingLiveData = MutableStateFlow(false)


    override fun btnBack() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    private var answer = ""

    init {
        viewModelScope.launch {
            recaptchaQuestionLiveData.emit(getRecaptcha())
        }
    }

    override fun btnLogin(userData: User) {
        viewModelScope.launch {
            if (userData.phoneNumber.length != 13 || !userData.phoneNumber.startsWith("+998")) {
                messageForPhoneNumber.emit(R.string.text5)
            }
            if (userData.password.isEmpty()) {
                messageForPassword.emit(R.string.text6)
            } else {
                loadingLiveData.emit(true)
                viewModelScope.launch {
                    authUseCase.login(userData).collectLatest {
                        when (it) {
                            is ResultData.Error -> {
                                loadingLiveData.emit(false)
                            }
                            is ResultData.Success -> {
                                navigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen())
                                loadingLiveData.emit(false)
                            }
                            is ResultData.Message -> {
                                it.message.onResource {
                                    messageLiveData.emit(it)
                                }
                                loadingLiveData.emit(false)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun openRegistrationScreen() {
        viewModelScope.launch {
            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToRegistrationScreen())
        }
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
        Log.d("TTT", " ${text} ${answer}")
        return text
    }
}