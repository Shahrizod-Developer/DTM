package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application

interface ApplicationViewModel {


    val loading: Flow<Boolean>
    val message: Flow<String>
    val applicationListFLow: Flow<List<Application>>

    fun openApplicationDesc(application: Application)

}