package uz.gita.dtm.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.service.ServiceRepository
import uz.gita.dtm.domain.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: ServiceRepository
) : HomeUseCase {

    override fun getServiceList(): Flow<ResultData<List<Service>>> = repository.getServiceList()

}