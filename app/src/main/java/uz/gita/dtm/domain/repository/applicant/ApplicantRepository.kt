package uz.gita.dtm.domain.repository.applicant

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Applicant
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData

interface ApplicantRepository {

    suspend fun getPassport(request: ApplicantRequest): Flow<ResultData<Passport>>

    suspend fun addAddress(applicantAddress: ApplicantAddress)

    suspend fun getAddress(jShShR: Long): Flow<ApplicantAddress>

    suspend fun addEducation(education: Education)

    suspend fun getEducation(jShShR: Long): Flow<Education>

}