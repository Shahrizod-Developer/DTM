package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.auth.impl.AuthRepositoryImpl
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import javax.inject.Inject
import kotlin.properties.Delegates
import kotlin.random.Random

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val repositoryImpl: AuthRepositoryImpl
) : LoginScreenViewModel, ViewModel() {

    override val messageLiveData = MutableLiveData<Int>()
    override val openRegistrationScreen = MutableLiveData<Unit>()
    override val messageForPhoneNumber = MutableLiveData<Int>()
    override val messageForPassword = MutableLiveData<Int>()
    override val recaptchaQuestionLiveData = MutableLiveData<String>()

    private var answer by Delegates.notNull<Int>()

    init {
        recaptchaQuestionLiveData.value = getRecaptcha()
    }

    override fun btnLogin(userData: User){
        viewModelScope.launch {
            repositoryImpl.login(userData).last()
            when()
        }
    }

    override fun openRegistrationScreen() {
        openRegistrationScreen.value = Unit
    }

    override fun checkRecaptcha(value: Int): Boolean {
        return value == answer
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
            answer = a + b
        } else if (amal == '*') {
            text = "$a * $b = "
            answer = a * b
        } else if (amal == '-') {
            if (a >= b) {
                text = "$a - $b = "
                answer = a - b
            } else {
                text = "$b - $a = "
                answer = b - a
            }
        }
        return text
    }
}