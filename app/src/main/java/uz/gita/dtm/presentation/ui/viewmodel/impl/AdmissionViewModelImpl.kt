package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.data.Data.Companion.state
import uz.gita.dtm.domain.usecase.AuthUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.home.AdmissionScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.AdmissionViewModel
import javax.inject.Inject

@HiltViewModel
class AdmissionViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val authUseCase: AuthUseCase
) : AdmissionViewModel, ViewModel() {
    override val showForOwnerDialogFLow = MutableSharedFlow<Boolean>()


    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun onClickOwnerData() {
        viewModelScope.launch {
            authUseCase.applicantPresence().collectLatest {
                if (it) {
                    state.emit(false)
                    openOwnerDataScreen()
                } else {
                    showForOwnerDialogFLow.emit(true)
                }
            }
        }
    }

    private fun openOwnerDataScreen() {
        viewModelScope.launch {
            navigator.navigateTo(AdmissionScreenDirections.actionAdmissionScreenToOwnerDataScreen())
        }
    }

    override fun openAddressScreen() {

    }

    override fun openInstitutionScreen() {

    }

    override fun openCertificateScreen() {

    }

    override fun openBenefitScreen() {

    }

    override fun openChooseDirectionScreen() {

    }

    override fun openGetOwnerDataScreen() {
        viewModelScope.launch {
            navigator.navigateTo(AdmissionScreenDirections.actionAdmissionScreenToGetOwnerDataScreen())
        }
    }
}