package uz.gita.dtm.domain.repository.news.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.dtm.data.models.mapper.Mapper.toNews
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    private val db = Firebase.firestore

    override fun getAllNews(): Flow<List<News>> = callbackFlow {

        val news = db.collection("news").addSnapshotListener { value, error ->

            val data = value?.documents?.map {
                it.toNews()
            }
            trySend(data ?: emptyList())
        }
        awaitClose { news.remove() }
    }
}