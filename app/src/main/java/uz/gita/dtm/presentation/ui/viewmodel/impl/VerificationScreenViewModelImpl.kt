package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.auth.VerificationScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.VerificationScreenViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,

    private val useCase: AuthUseCaseImpl
) : VerificationScreenViewModel,
    ViewModel() {
    override val messageLiveData = MutableSharedFlow<Int>()
    override val loadingLiveData = MutableStateFlow(false)


    override fun btnBack() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun btnResendSms() {
        viewModelScope.launch {
            navigator.navigateTo(VerificationScreenDirections.actionVerificationScreenToRegistrationScreen())
        }
    }

    override fun btnRegister(context: Context, code: String) {
        viewModelScope.launch {
            if (code.length == 6) {
                loadingLiveData.emit(true)
                viewModelScope.launch {
                    useCase.verificationSmsCode(context, code).collectLatest {
                        when (it) {
                            is ResultData.Success -> {
                                navigator.navigateTo(VerificationScreenDirections.actionVerificationScreenToMainScreen())
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
            } else {
                messageLiveData.emit(R.string.text9)
            }
        }
    }
}