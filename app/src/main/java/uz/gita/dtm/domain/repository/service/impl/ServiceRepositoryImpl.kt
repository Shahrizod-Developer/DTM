package uz.gita.dtm.domain.repository.service.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dtm.data.models.mapper.Mapper.toApplication
import uz.gita.dtm.data.models.mapper.Mapper.toService
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.service.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor() : ServiceRepository {

    private val db = Firebase.firestore

    override fun getServiceList(): Flow<List<Service>> = callbackFlow {

        val serviceList = db.collection("services").addSnapshotListener { value, error ->
            val data = value?.map {
                it.toService()
            }
            trySend(data ?: emptyList())
        }
        awaitClose { serviceList.remove() }
    }

    override fun getApplicationById(applicantId: String): Flow<Application> = callbackFlow {


        val application = db.collection("applications").addSnapshotListener { value, error ->
            val data = value?.map {
                it.toApplication()
            }?.filter { it -> it.id == applicantId }?.toList()?.get(0)

            trySend(data!!)
        }
        awaitClose { application.remove() }
    }
}