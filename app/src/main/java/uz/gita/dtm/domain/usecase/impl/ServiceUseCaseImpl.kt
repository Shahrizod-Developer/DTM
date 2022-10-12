package uz.gita.dtm.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.service.ServiceRepository
import uz.gita.dtm.domain.usecase.ServiceUseCase
import javax.inject.Inject

class ServiceUseCaseImpl @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ServiceUseCase {
    override fun getApplicationById(): Flow<ResultData<Application>> =
        serviceRepository.getApplicationById()
}