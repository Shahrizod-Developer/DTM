package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.utils.ResultData

interface ApplicantUseCase {

    suspend fun getPassportData(
        applicantRequest: ApplicantRequest,
        state: Boolean
    ): Flow<ResultData<Passport>>

    fun addAddress(applicantAddress: ApplicantAddress): Flow<ResultData<String>>

    fun getAddress(jShShR: Long): Flow<ResultData<ApplicantAddress>>

    fun addEducation(education: Education): Flow<ResultData<String>>

    fun getEducation(jShShR: Long): Flow<ResultData<Education>>

    fun getJShShR(): Flow<String>
}