package uz.gita.dtm.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.news.NewsRepository
import uz.gita.dtm.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsUseCase {
    override fun getAllNews(): Flow<ResultData<List<News>>> {
        return newsRepository.getAllNews()
    }

}