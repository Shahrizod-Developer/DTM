package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.dtm.presentation.ui.viewmodel.VerificationScreenViewModel
import javax.inject.Inject

@HiltViewModel
class VerificationScreenViewModelImpl @Inject constructor(
    private val useCase: AuthUseCaseImpl
) : VerificationScreenViewModel,
    ViewModel() {
    override val btnBackLiveData = MutableLiveData<Unit>()
    override val messageLiveData = MutableLiveData<String>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val openRegisterScreenLiveData = MutableLiveData<Unit>()

    override fun btnBack() {
        btnBackLiveData.value = Unit
    }

    override fun btnResendSms() {
        openRegisterScreenLiveData.value = Unit
    }

    override fun btnRegister(context: Context, code: String) {
        if (code.length == 6) {
            viewModelScope.launch {
                useCase.verificationSmsCode(context, code).collectLatest {
                    when (it) {
                        is ResultData.Success -> {
                            openMainScreenLiveData.postValue(Unit)
                        }
                        is ResultData.Message -> {
//                        messageLiveData.postValue(i)
                        }
                        is ResultData.Error -> {

                        }
                    }

                }
            }
        } else {
            messageLiveData.value = "tekshiruv kodi xato"
        }
    }
}