package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData

interface ServiceUseCase {

    fun getApplicationById(): Flow<ResultData<Application>>
}