package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.ApplicationUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.ApplicationViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModelImpl @Inject constructor(
    private val useCase: ApplicationUseCase,
    private val navigator: Navigator
) : ApplicationViewModel, ViewModel() {
    override val loading = MutableStateFlow(false)
    override val message = MutableStateFlow("")


    override val applicationListFLow: MutableStateFlow<List<Application>> = MutableStateFlow(
        emptyList()
    )

    init {

        viewModelScope.launch {
            useCase.getApplications().collectLatest {
                when (it) {
                    is ResultData.Error -> {
                        loading.emit(false)
                    }
                    is ResultData.Success -> {
                        applicationListFLow.emit(it.data)
                        loading.emit(false)
                    }
                    is ResultData.Message -> {
                        message.emit(it.message.toString())
                    }
                }
            }
        }
    }

    override fun openApplicationDesc(application: Application) {

    }
}