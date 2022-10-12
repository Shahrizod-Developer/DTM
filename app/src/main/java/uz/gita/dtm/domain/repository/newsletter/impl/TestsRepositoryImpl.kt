package uz.gita.dtm.domain.repository.newsletter.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import uz.gita.dtm.data.models.mapper.Mapper.toNewsLetters
import uz.gita.dtm.data.models.tests.NewsLetter
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.newsletter.TestsRepository
import javax.inject.Inject

class TestsRepositoryImpl @Inject constructor() : TestsRepository {

    val db = Firebase.firestore

    override fun getAllNewsLetter(): Flow<ResultData<List<NewsLetter>>> = callbackFlow {

        val newsLetters = db.collection("newsletter").addSnapshotListener { value, error ->
            val data = value?.documents?.map {
                it.toNewsLetters()
            }!!.toList()
            trySend(ResultData.success(data))
        }

        awaitClose { newsLetters.remove() }
    }.flowOn(Dispatchers.IO)
}