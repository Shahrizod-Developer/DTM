package uz.gita.dtm.domain.repository.service

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.utils.ResultData

interface ServiceRepository {

    fun getServiceList(): Flow<ResultData<List<Service>>>

    fun getApplicationById(): Flow<ResultData<Application>>
}