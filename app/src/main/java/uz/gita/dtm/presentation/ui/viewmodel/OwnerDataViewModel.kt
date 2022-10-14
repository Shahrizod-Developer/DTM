package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Passport

interface OwnerDataViewModel {

    val passportData: Flow<Passport>
    val loading: Flow<Boolean>
    val message: Flow<String>

    fun back()
    fun openUpdateOwnerDataScreen()
}