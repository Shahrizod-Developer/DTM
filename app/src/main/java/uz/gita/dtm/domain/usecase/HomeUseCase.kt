package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.utils.ResultData

interface HomeUseCase {

    fun getServiceList(): Flow<ResultData<List<Service>>>
}