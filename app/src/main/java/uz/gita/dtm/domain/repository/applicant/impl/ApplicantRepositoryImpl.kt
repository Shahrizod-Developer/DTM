package uz.gita.dtm.domain.repository.applicant.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dtm.data.models.mapper.Mapper.toAddress
import uz.gita.dtm.data.models.mapper.Mapper.toEducation
import uz.gita.dtm.data.models.mapper.Mapper.toPassport
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import javax.inject.Inject

class ApplicantRepositoryImpl @Inject constructor() : ApplicantRepository {

    private val db = Firebase.firestore

    override suspend fun getPassport(request: ApplicantRequest): Flow<Passport> =
        callbackFlow {

            val passport = db.collection("passport").addSnapshotListener { value, error ->
                val data = value!!.map {
                    it.toPassport()
                }
                trySend(data[0])
            }
            awaitClose { passport.remove() }
        }

    override suspend fun addAddress(applicantAddress: ApplicantAddress) {
        db.collection("address").document(applicantAddress.id).set(applicantAddress)
    }

    override suspend fun getAddress(jShShR: Long): Flow<ApplicantAddress> = callbackFlow {

        val address = db.collection("address").addSnapshotListener { value, error ->
            val data = value!!.map {
                it.toAddress()
            }
            trySend(data[0])
        }
        awaitClose { address.remove() }
    }

    override suspend fun addEducation(education: Education) {
        db.collection("educations").document(education.id).set(education)
    }

    override suspend fun getEducation(jShShR: Long): Flow<Education> = callbackFlow {

        val address = db.collection("educations").addSnapshotListener { value, error ->
            val data = value!!.map {
                it.toEducation()
            }
            trySend(data[0])
        }
        awaitClose { address.remove() }
    }
}