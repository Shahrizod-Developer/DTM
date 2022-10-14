package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.ApplicantUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.GetOwnerDataViewModel
import javax.inject.Inject


@HiltViewModel
class GetOwnerDataViewModelImpl @Inject constructor(
    private val useCase: ApplicantUseCase,
    private val navigator: Navigator
) : GetOwnerDataViewModel,
    ViewModel() {

    override val jShShRFlow = MutableStateFlow("")
    override val loading = MutableStateFlow(false)
    override val message = MutableStateFlow("")

    init {
        viewModelScope.launch {
            useCase.getJShShR().collectLatest {
                jShShRFlow.emit(it)
            }
        }
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun onCLickConfirm(applicantRequest: ApplicantRequest, state: Boolean) {
        viewModelScope.launch {
            useCase.getPassportData(applicantRequest, state).collectLatest {
                when (it) {
                    is ResultData.Error -> {
                        loading.emit(false)
                    }
                    is ResultData.Success -> {
                        jShShRFlow.emit(it.data.jShShir.toString())
                        loading.emit(false)
                        message.emit("Yangilandi")
                        navigator.back()
                    }
                    is ResultData.Message -> {
                        message.emit(it.message.toString())
                    }
                }
            }
        }
    }
}