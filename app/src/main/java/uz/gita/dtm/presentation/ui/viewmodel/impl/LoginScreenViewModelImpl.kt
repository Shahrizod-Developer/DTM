package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
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

    override val btnBackLiveData = MutableSharedFlow<Unit>()
    override val messageLiveData = MutableSharedFlow<String>()
    override val openRegistrationScreen = MutableSharedFlow<Unit>()
    override val openMainScreen = MutableSharedFlow<Unit>()
    override val messageForPhoneNumber = MutableSharedFlow<Int>()
    override val messageForPassword = MutableSharedFlow<Int>()
    override val recaptchaQuestionLiveData = MutableSharedFlow<String>()
    override val loadingLiveData = MutableSharedFlow<Boolean>()


    override fun btnBack() {
        viewModelScope.launch {
            btnBackLiveData.emit(Unit)
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
                                openMainScreen.emit(Unit)
                                loadingLiveData.emit(false)
                            }
                            is ResultData.Message -> {
                                it.message.onText {
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
        viewModelScope.launch { openRegistrationScreen.emit(Unit) }
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