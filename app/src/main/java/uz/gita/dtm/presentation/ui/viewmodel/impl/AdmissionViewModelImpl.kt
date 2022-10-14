package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.home.AdmissionScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.AdmissionViewModel
import javax.inject.Inject

@HiltViewModel
class AdmissionViewModelImpl @Inject constructor(
    private val navigator: Navigator
) : AdmissionViewModel, ViewModel() {
    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun openOwnerDataScreen() {
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
}