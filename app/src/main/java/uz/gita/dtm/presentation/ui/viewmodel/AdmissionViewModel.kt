package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow

interface AdmissionViewModel {

    val showForOwnerDialogFLow: Flow<Boolean>
    fun back()
    fun onClickOwnerData()
    fun openAddressScreen()
    fun openInstitutionScreen()
    fun openCertificateScreen()
    fun openBenefitScreen()
    fun openChooseDirectionScreen()
    fun openGetOwnerDataScreen()
}