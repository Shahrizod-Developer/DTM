package uz.gita.dtm.domain.repository.news

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData

interface NewsRepository {

    fun getAllNews():Flow<List<News>>
}