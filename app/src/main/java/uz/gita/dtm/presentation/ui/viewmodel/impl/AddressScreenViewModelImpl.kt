package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.ApplicantUseCase
import uz.gita.dtm.domain.usecase.impl.ApplicantUseCaseImpl
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.AddressScreenViewModel
import javax.inject.Inject

@HiltViewModel
class AddressScreenViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val useCase: ApplicantUseCase
) : AddressScreenViewModel, ViewModel() {
    override val loading = MutableStateFlow(false)
    override val message = MutableStateFlow("")
    override val addressFLow = MutableStateFlow(ApplicantAddress("", "", "", ""))

    init {
        viewModelScope.launch {

            useCase.getAddress(MySharedPreference.JShShIR).collectLatest {
                when (it) {
                    is ResultData.Error -> {
                        loading.emit(false)
                    }
                    is ResultData.Success -> {
                        addressFLow.emit(it.data)
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

    override fun onClickConfirm(address: ApplicantAddress) {
        viewModelScope.launch {

            useCase.addAddress(address).collectLatest {
                when (it) {
                    is ResultData.Error -> {
                        loading.emit(false)
                    }
                    is ResultData.Success -> {
                        message.emit(it.data)
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