package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow

interface ServiceDetailsViewModel {

    val showUseServiceDialogFlow: Flow<Boolean>
    val showAboutServiceDialogFlow: Flow<Boolean>
    val showApplicationDialogFlow: Flow<Boolean>

    fun openLoginScreen()
    fun back()
    fun showAboutServiceDialog()
    fun openAdmissionScreen()
    fun onCLickUseService()
    fun onCLickApplication()
}