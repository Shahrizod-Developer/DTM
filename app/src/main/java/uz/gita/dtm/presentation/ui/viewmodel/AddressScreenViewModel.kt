package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.ApplicantAddress

interface AddressScreenViewModel {

    val loading: Flow<Boolean>
    val message: Flow<String>
    val addressFLow: Flow<ApplicantAddress>

    fun back()

    fun onClickConfirm(address: ApplicantAddress)
}