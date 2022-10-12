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
import uz.gita.dtm.data.models.tests.NewsLetter
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import uz.gita.dtm.domain.usecase.HomeUseCase
import uz.gita.dtm.domain.usecase.TestsUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.MainScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val testsUseCase: TestsUseCase,
    private val applicantRepository: ApplicantRepository,
    private val navigator: Navigator
) : HomeViewModel, ViewModel() {


    override val loading = MutableStateFlow(false)
    override val loadingNewsLetter = MutableStateFlow(false)
    override val message = MutableStateFlow("")

    override var serviceList = MutableStateFlow(emptyList<Service>())
    override val newsLettersList = MutableStateFlow(emptyList<NewsLetter>())


    init {
        viewModelScope.launch {
            loading.emit(true)
            homeUseCase.getServiceList().collectLatest {

                when (it) {
                    is ResultData.Error -> {
                        loading.emit(false)
                    }
                    is ResultData.Success -> {
                        serviceList.emit(it.data)
                        loading.emit(false)
                    }
                    is ResultData.Message -> {
                        message.emit(it.message.toString())
                    }
                }

            }
        }
        viewModelScope.launch {
            loadingNewsLetter.emit(true)
            testsUseCase.getAllNewsLetters().collectLatest {
                when (it) {
                    is ResultData.Error -> {
                        loadingNewsLetter.emit(false)
                    }
                    is ResultData.Success -> {
                        newsLettersList.emit(it.data)
                        loadingNewsLetter.emit(false)
                    }
                    is ResultData.Message -> {
                        message.emit(it.message.toString())
                    }
                }
            }
        }
    }

    override suspend fun add(applicant: Education) = applicantRepository.addEducation(applicant)

    override fun openServiceDetail(service: Service) {
        viewModelScope.launch {
            navigator.navigateTo(
                MainScreenDirections.actionMainScreenToServiceDetailsScreen(
                    service
                )
            )
        }
    }
}