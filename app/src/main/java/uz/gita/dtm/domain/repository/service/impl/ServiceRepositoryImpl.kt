package uz.gita.dtm.domain.repository.service.impl

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import uz.gita.dtm.data.models.mapper.Mapper.toApplication
import uz.gita.dtm.data.models.mapper.Mapper.toService
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.service.ServiceRepository
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor() : ServiceRepository {

    private val db = Firebase.firestore


    override fun getServiceList(): Flow<ResultData<List<Service>>> = callbackFlow {
        val serviceList = db.collection("services").addSnapshotListener { value, error ->
            val data = value?.documents?.map {
                it.toService()
            }!!.toList()
            trySend(ResultData.success(data))
        }

        awaitClose { serviceList.remove() }
    }.flowOn(Dispatchers.IO)

    override fun getApplicationById(): Flow<ResultData<Application>> =
        callbackFlow {


            val application = db.collection("applications").addSnapshotListener { value, error ->
                val data = value?.documents?.map {
                    it.toApplication()
                }?.filter { it -> it.id == MySharedPreference.JShShIR }?.toList()?.get(0)

                trySend(ResultData.success(data!!))
            }
            awaitClose { application.remove() }
        }.flowOn(Dispatchers.IO)
}