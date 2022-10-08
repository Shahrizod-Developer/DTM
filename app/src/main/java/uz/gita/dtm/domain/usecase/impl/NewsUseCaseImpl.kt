package uz.gita.dtm.domain.usecase.impl

import uz.gita.dtm.domain.repository.news.NewsRepository
import uz.gita.dtm.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsUseCase {
}