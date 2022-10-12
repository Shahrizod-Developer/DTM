package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dtm.domain.usecase.AuthUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.home.ServiceDetailsScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.ServiceDetailsViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceDetailsViewModelImpl @Inject constructor(
    private val navigator: Navigator, private val authUseCase: AuthUseCase
) : ServiceDetailsViewModel, ViewModel() {

    override val showUseServiceDialogFlow = MutableSharedFlow<Boolean>()
    override var showAboutServiceDialogFlow = MutableSharedFlow<Boolean>()
    override val showApplicationDialogFlow = MutableSharedFlow<Boolean>()

    override fun openLoginScreen() {
        viewModelScope.launch {
            navigator.navigateTo(ServiceDetailsScreenDirections.actionServiceDetailsScreenToLoginScreen())
        }
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    private fun showUseServiceDialog() {
        viewModelScope.launch {
            showUseServiceDialogFlow.emit(true)
        }
    }

    override fun showAboutServiceDialog() {
        viewModelScope.launch {
            showAboutServiceDialogFlow.emit(true)
        }
    }

    override fun showApplicationDialog() {
        viewModelScope.launch {
            showApplicationDialogFlow.emit(true)
        }
    }

    override fun openAdmissionScreen() {
        viewModelScope.launch {
            navigator.navigateTo(ServiceDetailsScreenDirections.actionServiceDetailsScreenToAdmissionScreen())
        }
    }

    override fun openApplicationScreen() {
        viewModelScope.launch {
            navigator.navigateTo(ServiceDetailsScreenDirections.actionServiceDetailsScreenToApplicationScreen2())
        }
    }

    override fun onCLickUseService() {
//        viewModelScope.launch {
//            authUseCase.userPresence().onEach {
//                if (it) {
//                    openAdmissionScreen()
//                } else {
//                    showUseServiceDialog()
//                }
//            }
//        }
    }
}