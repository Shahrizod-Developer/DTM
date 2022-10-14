package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.request.ApplicantRequest

interface GetOwnerDataViewModel {

    val loading: Flow<Boolean>
    val message: Flow<String>

    val jShShRFlow: Flow<String>
    fun back()
    fun onCLickConfirm(applicantRequest: ApplicantRequest, state:Boolean)
}