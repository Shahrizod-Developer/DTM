package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import uz.gita.dtm.domain.repository.service.ServiceRepository
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.profile.OwnerDataScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.OwnerDataViewModel
import javax.inject.Inject

@HiltViewModel
class OwnerDataViewModelImpl @Inject constructor(
    private val useCase: ApplicantRepository,
    private val navigator: Navigator
) : OwnerDataViewModel, ViewModel() {
    override val passportData = MutableStateFlow(
        Passport(
            firstName = "",
            lastName = "",
            fatherName = "",
            image = "",
            birthday = 1,
            passportSeriesNumber = 1,
            passportsSeries = "",
            jShShir = 1
        )
    )
    override val loading = MutableStateFlow(false)
    override val message = MutableStateFlow("")

    init {
        viewModelScope.launch {
            useCase.getPassport(ApplicantRequest("", 0), false).collectLatest {
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

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun openUpdateOwnerDataScreen() {
        viewModelScope.launch {
            navigator.navigateTo(OwnerDataScreenDirections.actionOwnerDataScreenToGetOwnerDataScreen())
        }
    }
}