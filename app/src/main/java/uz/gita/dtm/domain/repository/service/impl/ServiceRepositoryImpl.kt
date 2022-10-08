package uz.gita.dtm.domain.repository.service.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.service.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor():ServiceRepository {
    override fun getServiceList(): Flow<List<Service>> {
        TODO("Not yet implemented")
    }

    override fun getApplicationById(applicantId: String): Flow<Application> {
        TODO("Not yet implemented")
    }
}