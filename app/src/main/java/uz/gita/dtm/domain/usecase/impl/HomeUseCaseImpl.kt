package uz.gita.dtm.domain.usecase.impl

import uz.gita.dtm.domain.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: ServiceRepository
) : HomeUseCase {

    override fun getServiceList(): Flow<List<Service>> = repository.getServiceList()

}