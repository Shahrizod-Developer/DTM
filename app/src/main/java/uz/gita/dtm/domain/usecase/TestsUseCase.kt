package uz.gita.dtm.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.tests.NewsLetter
import uz.gita.dtm.data.utils.ResultData

interface TestsUseCase {

    fun getAllNewsLetters(): Flow<ResultData<List<NewsLetter>>>
}