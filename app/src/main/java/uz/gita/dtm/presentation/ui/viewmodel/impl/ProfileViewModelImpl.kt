package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.ApplicantUseCase
import uz.gita.dtm.domain.usecase.AuthUseCase
import uz.gita.dtm.domain.usecase.ProfileUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.MainScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.ProfileViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val useCase: ApplicantUseCase,
    private val authUseCase: AuthUseCase,
    private val navigator: Navigator
) : ProfileViewModel, ViewModel() {
    override val userPresence = MutableStateFlow(false)
    override val showOwnerDialogLiveData = MutableLiveData<Unit>()
    override val showOwnerDialog = MutableLiveData<Unit>()
    override val passportData = MutableStateFlow(Passport("", "", "",
        "", 1, "", 1, 1))
    override val loading = MutableStateFlow(false)
    override val message = MutableStateFlow("")

    override suspend fun getOwnerData(applicantRequest: ApplicantRequest, state: Boolean) {

        authUseCase.userPresence().collectLatest {
            if (it) {
                useCase.getPassportData(applicantRequest, state).collectLatest {
                    when (it) {
                        is ResultData.Error -> {
                            loading.emit(false)
                        }
                        is ResultData.Success -> {
                            passportData.emit(it.data)
                            loading.emit(false)
                        }
                        is ResultData.Message -> {
                            message.emit(it.message.toString())
                        }
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            authUseCase.userPresence().collectLatest {
                userPresence.emit(it)
            }
        }
    }

    override suspend fun openLoginScreen() {
        navigator.navigateTo(MainScreenDirections.actionGlobalLoginScreen())
    }

    override suspend fun onclickOwnerData() {
        viewModelScope.launch {
            authUseCase.applicantPresence().collectLatest {
                if (it) {
                    navigator.navigateTo(MainScreenDirections.actionMainScreenToOwnerDataScreen())
                } else {
                    showOwnerDialogLiveData.postValue(Unit)
                }
            }
        }
    }

    override fun openGetOwnerDataScreen() {
        viewModelScope.launch {
            navigator.navigateTo(MainScreenDirections.actionMainScreenToGetOwnerDataScreen())
        }
    }

    override suspend fun onClickAddress() {
        viewModelScope.launch {
            authUseCase.applicantPresence().collectLatest {
                if (it) {
                    navigator.navigateTo(MainScreenDirections.actionMainScreenToAddressScreen())
                } else {
                    showOwnerDialog.postValue(Unit)
                }
            }
        }
    }

}