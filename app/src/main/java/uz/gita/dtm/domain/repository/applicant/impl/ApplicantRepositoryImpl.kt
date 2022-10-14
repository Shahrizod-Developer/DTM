package uz.gita.dtm.domain.repository.applicant.impl

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override fun getPassport(
        applicantRequest: ApplicantRequest,
        state: Boolean
    ): Flow<ResultData<Passport>> =
        callbackFlow {

            val passport = db.collection("passport").addSnapshotListener { value, error ->

                val data = value!!.documents.map {

                    it.toPassport()
                }.filter {
                    if (state) {
                        it.passportsSeries == applicantRequest.series
                                && it.passportSeriesNumber == applicantRequest.seriesNumber
                    } else {
                        it.passportsSeries == MySharedPreference.series
                                && it.passportSeriesNumber == MySharedPreference.passportNumber
                    }
                }.toList()

                if (ResultData.success(data[0]).isSuccess) {
                    if (state) {
                        MySharedPreference.series = data[0].passportsSeries
                        MySharedPreference.passportNumber = data[0].passportSeriesNumber
                        MySharedPreference.JShShIR = data[0].jShShir
                    }
                }
                trySend(ResultData.success(data[0]))
            }
            awaitClose { passport.remove() }
        }.flowOn(Dispatchers.IO)

    override fun getJShShR(): Flow<String> = flow {
        emit(MySharedPreference.JShShIR.toString())
    }

    override fun addAddress(applicantAddress: ApplicantAddress): Flow<ResultData<String>> =
        callbackFlow {
            db.collection("address")
                .document(applicantAddress.id)
                .set(applicantAddress).addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        trySend(ResultData.success("Doimiy yashash manzili qo'shildi"))
                    }
                }

        }

    override fun getAddress(jShShR: Long): Flow<ResultData<ApplicantAddress>> =
        callbackFlow {

            val address = db.collection("address").addSnapshotListener { value, error ->
                val data = value!!.documents.map {
                    it.toAddress()
                }
                trySend(ResultData.success(data[0]))
            }
            awaitClose { address.remove() }
        }.flowOn(Dispatchers.IO)

    override fun addEducation(education: Education): Flow<ResultData<String>> =
        callbackFlow {
            db.collection("educations").document(education.id).set(education)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        trySend(ResultData.success("Ta'lim muassasasi qo'shildi"))
                    }
                }
        }

    override fun getEducation(jShShR: Long): Flow<ResultData<Education>> = callbackFlow {

        val address = db.collection("educations").addSnapshotListener { value, error ->
            val data = value!!.documents.map {
                it.toEducation()
            }
            trySend(ResultData.success(data[0]))
        }
        awaitClose { address.remove() }
    }.flowOn(Dispatchers.IO)
}