package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.service.Service

interface HomeUseCase {

    fun getServiceList():Flow<List<Service>>
}