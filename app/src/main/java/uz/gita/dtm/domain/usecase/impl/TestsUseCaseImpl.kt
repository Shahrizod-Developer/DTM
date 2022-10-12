package uz.gita.dtm.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.tests.NewsLetter
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.newsletter.TestsRepository
import uz.gita.dtm.domain.usecase.TestsUseCase
import javax.inject.Inject

class TestsUseCaseImpl @Inject constructor(
    private val repository: TestsRepository
) : TestsUseCase {
    override fun getAllNewsLetters(): Flow<ResultData<List<NewsLetter>>> =
        repository.getAllNewsLetter()
}