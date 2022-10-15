package uz.gita.dtm.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.application.ApplicationRepository
import uz.gita.dtm.domain.usecase.ApplicationUseCase
import javax.inject.Inject

class ApplicationUseCaseImpl @Inject constructor(
    private val repository: ApplicationRepository
) : ApplicationUseCase {
    override fun getApplications(): Flow<ResultData<List<Application>>> =
        repository.getApplications()

    override fun getApplicationByJShSHR(): Flow<ResultData<Application>> =
        repository.getApplicationByJShSHR()

    override suspend fun addApplication(application: Application) =
        repository.addApplication(application)
}