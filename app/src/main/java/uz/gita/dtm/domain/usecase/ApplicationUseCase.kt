package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData

interface ApplicationUseCase {

    fun getApplications(): Flow<ResultData<List<Application>>>

    fun getApplicationByJShSHR(): Flow<ResultData<Application>>

    suspend fun addApplication(application: Application)
}