package uz.gita.dtm.domain.repository.application

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.app.App
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData

interface ApplicationRepository {

    fun getApplications(): Flow<ResultData<List<Application>>>

    fun getApplicationByJShSHR(): Flow<ResultData<Application>>

    suspend fun addApplication(application: Application)

}