package uz.gita.dtm.domain.repository.applicant.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import javax.inject.Inject

class ApplicantRepositoryImpl @Inject constructor(): ApplicantRepository {
    override suspend fun getPassport(request: ApplicantRequest): Flow<ResultData<Passport>> {
        TODO("Not yet implemented")
    }

    override suspend fun addAddress(applicantAddress: ApplicantAddress) {
        TODO("Not yet implemented")
    }

    override suspend fun getAddress(jShShR: Long): Flow<ResultData<ApplicantAddress>> {
        TODO("Not yet implemented")
    }

    override suspend fun addEducation(education: Education) {
        TODO("Not yet implemented")
    }

    override suspend fun getEducation(jShShR: Long): Flow<ResultData<Education>> {
        TODO("Not yet implemented")
    }


}