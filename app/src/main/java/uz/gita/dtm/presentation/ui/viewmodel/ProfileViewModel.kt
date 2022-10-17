package uz.gita.dtm.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest

interface ProfileViewModel {

    val userPresence: Flow<Boolean>
    val showOwnerDialogLiveData: LiveData<Unit>
    val showOwnerDialog: LiveData<Unit>
    val passportData: Flow<Passport>
    val loading: Flow<Boolean>
    val message: Flow<String>

    suspend fun getOwnerData(applicantRequest: ApplicantRequest, state: Boolean)

    suspend fun openLoginScreen()

    suspend fun onclickOwnerData()

    fun openGetOwnerDataScreen()

    suspend fun onClickAddress()
}