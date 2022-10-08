package uz.gita.dtm.domain.usecase.impl

import uz.gita.dtm.domain.repository.service.ServiceRepository
import uz.gita.dtm.domain.usecase.ServiceUseCase
import javax.inject.Inject

class ServiceUseCaseImpl @Inject constructor(
    private val serviceRepository: ServiceRepository
) : ServiceUseCase {

}