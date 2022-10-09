package uz.gita.dtm.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.service.Service

interface HomeViewModel {

    val serviceList: LiveData<List<Service>>

    suspend fun add(applicant: Education)
}