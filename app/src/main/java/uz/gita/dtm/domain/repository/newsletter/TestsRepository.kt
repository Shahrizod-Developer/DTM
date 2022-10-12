package uz.gita.dtm.domain.repository.newsletter

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.tests.NewsLetter
import uz.gita.dtm.data.utils.ResultData

interface TestsRepository {

    fun getAllNewsLetter(): Flow<ResultData<List<NewsLetter>>>
}