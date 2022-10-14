package uz.gita.dtm.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.applicant.impl.ApplicantRepositoryImpl
import uz.gita.dtm.domain.usecase.ApplicantUseCase
import javax.inject.Inject

class ApplicantUseCaseImpl @Inject constructor(
    private val repository: ApplicantRepositoryImpl
) : ApplicantUseCase {
    override suspend fun getPassportData(
        applicantRequest: ApplicantRequest,
        state: Boolean
    ): Flow<ResultData<Passport>> =
        repository.getPassport(applicantRequest, state)

    override fun addAddress(applicantAddress: ApplicantAddress): Flow<ResultData<String>> =
        repository.addAddress(applicantAddress)

    override fun getAddress(jShShR: Long): Flow<ResultData<ApplicantAddress>> =
        repository.getAddress(jShShR)

    override fun addEducation(education: Education): Flow<ResultData<String>> =
        repository.addEducation(education)

    override fun getEducation(jShShR: Long): Flow<ResultData<Education>> =
        repository.getEducation(jShShR)

    override fun getJShShR(): Flow<String> = repository.getJShShR()
}