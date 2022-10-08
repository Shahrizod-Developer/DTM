package uz.gita.dtm.domain.repository.application.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dtm.data.models.mapper.Mapper.toApplication
import uz.gita.dtm.data.models.mapper.Mapper.toPassport
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.application.ApplicationRepository
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor() : ApplicationRepository {

    private val db = Firebase.firestore

    override fun getApplications(number: String): Flow<List<Application>> =
        callbackFlow {

            val applications = db.collection("applications").addSnapshotListener { value, error ->
                val data = value?.map {
                    it.toApplication()
                }
                trySend(data ?: emptyList())
            }
            awaitClose { applications.remove() }
        }

    override fun addApplication(application: Application) {
        db.collection("applications").document(application.id).set(application)
    }
}