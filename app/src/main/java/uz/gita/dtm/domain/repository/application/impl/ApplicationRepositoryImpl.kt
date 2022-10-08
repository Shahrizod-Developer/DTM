package uz.gita.dtm.domain.repository.application.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.application.ApplicationRepository
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor() : ApplicationRepository {

    val db = Firebase.firestore

//    override fun getApplications(number: String): Flow<List<Application>> {
//
//    }

    override fun addApplication(application: Application) {
        db.collection("applications").document(application.id).set(application)
    }
}