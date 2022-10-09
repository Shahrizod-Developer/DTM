package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.persondata.Applicant
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import uz.gita.dtm.domain.usecase.HomeUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val applicantRepository: ApplicantRepository,
    private val navigator: Navigator
) : HomeViewModel, ViewModel() {


    override val loading = MutableStateFlow(false)

    override var serviceList = MutableStateFlow(emptyList<Service>())

    init {
        viewModelScope.launch {
            loading.emit(true)
            homeUseCase.getServiceList().collectLatest {
                serviceList.emit(it)
            }
        }
    }

    override suspend fun add(applicant: Education) = applicantRepository.addEducation(applicant)
}