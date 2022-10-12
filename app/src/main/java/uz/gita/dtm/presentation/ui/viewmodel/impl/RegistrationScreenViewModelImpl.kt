package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.auth.RegistrationScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.RegistrationScreenViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RegistrationScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: AuthUseCaseImpl
) :
    RegistrationScreenViewModel,
    ViewModel() {

    override val messageLiveData = MutableSharedFlow<Int>()
    override val messageForPhoneNumber = MutableSharedFlow<Int>()
    override val messageForPassword = MutableSharedFlow<Int>()
    override val recaptchaQuestionLiveData = MutableStateFlow("")
    override val loadingLiveData = MutableStateFlow(false)

    private var answer = ""

    init {
        viewModelScope.launch {
            recaptchaQuestionLiveData.emit(getRecaptcha())
        }
    }

    override fun btnBack() {
        viewModelScope.launch { navigator.back() }
    }

    override fun checkRecaptcha(value: String): Boolean {
        return value == answer
    }

    override fun btnRegister(activity: Activity, user: User) {
        viewModelScope.launch {
            if (user.phoneNumber.isEmpty()) {
                messageForPhoneNumber.emit(R.string.text5)
            }
            if (user.phoneNumber.isEmpty()) {
                messageForPassword.emit(R.string.text6)
            } else {
                loadingLiveData.emit(true)
                MySharedPreference.phoneNumber = user.phoneNumber
                MySharedPreference.password = user.password
                viewModelScope.launch {
                    useCase.sendSmsCode(activity, user.phoneNumber).collectLatest {
                        when (it) {
                            is ResultData.Success -> {
                               navigator.navigateTo(RegistrationScreenDirections.actionRegistrationScreenToVerificationScreen())
                                loadingLiveData.emit(false)
                            }
                            is ResultData.Message -> {
                                it.message.onResource {
                                    messageLiveData.emit(it)
                                }
                                loadingLiveData.emit(false)
                            }
                            is ResultData.Error -> {
                                loadingLiveData.emit(false)
                            }
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