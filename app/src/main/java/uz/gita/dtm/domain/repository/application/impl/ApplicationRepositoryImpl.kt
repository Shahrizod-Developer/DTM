package uz.gita.dtm.domain.repository.application.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.application.ApplicationRepository
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor() : ApplicationRepository {
    override fun getApplications(number: String): Flow<ResultData<Application>> {
        TODO("Not yet implemented")
    }

    override fun addApplication(application: Application) {
        TODO("Not yet implemented")
    }
}