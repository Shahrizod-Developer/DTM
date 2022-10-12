package uz.gita.dtm.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.models.tests.NewsLetter

interface HomeViewModel {

    val loading: Flow<Boolean>
    val loadingNewsLetter: Flow<Boolean>
    val serviceList: Flow<List<Service>>
    val newsLettersList: Flow<List<NewsLetter>>
    val message: Flow<String>

    suspend fun add(applicant: Education)

    fun openServiceDetail(service: Service)
}