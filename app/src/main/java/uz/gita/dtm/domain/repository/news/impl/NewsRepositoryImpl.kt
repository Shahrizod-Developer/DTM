package uz.gita.dtm.domain.repository.news.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor():NewsRepository {
    override fun getAllNews(): Flow<ResultData<News>> {
        TODO("Not yet implemented")
    }
}