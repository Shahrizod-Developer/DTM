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
import uz.gita.dtm.presentation.ui.viewmodel.RestorePasswordScreenViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RestorePasswordScreenViewModelImpl @Inject constructor(
    private val useCase: AuthUseCaseImpl
) : RestorePasswordScreenViewModel, ViewModel() {
    override val btnBackLiveData = MutableLiveData<Unit>()
    override val messageLiveData = MutableLiveData<Int>()
    override val openVerificationScreen = MutableLiveData<Unit>()
    override val messageForPhoneNumber = MutableLiveData<Int>()
    override val messageForPassword = MutableLiveData<Int>()
    override val recaptchaQuestionLiveData = MutableLiveData<String>()

    private var answer = ""

    init {
        recaptchaQuestionLiveData.value = getRecaptcha()
    }

    override fun btnBack() {
        btnBackLiveData.value = Unit
    }

    override fun btnLogin(activity: Activity, userData: User) {
        MySharedPreference.password = userData.password
        MySharedPreference.phoneNumber = userData.phoneNumber
        if (userData.phoneNumber.isEmpty()) {
            messageForPhoneNumber.value = R.string.text5
        }
        if (userData.password.isEmpty()) {
            messageForPassword.value = R.string.text6
        } else {
            viewModelScope.launch {
                useCase.restorePassword(userData.phoneNumber).collectLatest {
                    when (it) {
                        is ResultData.Success -> {
                            useCase.sendSmsCode(activity, userData.phoneNumber).collectLatest {
                                when (it) {
                                    is ResultData.Success -> {
                                        useCase.setNewPassword(userData.password).collectLatest {
                                            when (it) {
                                                is ResultData.Success -> {}
                                                is ResultData.Message -> {}
                                                is ResultData.Error -> {}
                                            }
                                        }
                                    }
                                    is ResultData.Message -> {}
                                    is ResultData.Error -> {}
                                }
                            }
                        }
                        is ResultData.Message -> {}
                        is ResultData.Error -> {}
                    }
                }
            }
        }
    }

    override fun checkRecaptcha(value: String): Boolean {
        return answer == value
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