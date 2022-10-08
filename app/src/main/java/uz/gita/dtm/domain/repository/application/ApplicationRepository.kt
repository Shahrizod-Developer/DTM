package uz.gita.dtm.domain.repository.application

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.app.App
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData

interface ApplicationRepository {

    fun getApplications(number: String): Flow<List<Application>>

    fun addApplication(application: Application)

}