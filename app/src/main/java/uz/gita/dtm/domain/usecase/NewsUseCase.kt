package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData

interface NewsUseCase {
    fun getAllNews(): Flow<ResultData<List<News>>>
}