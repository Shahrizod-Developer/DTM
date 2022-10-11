package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData

interface NewsViewModel {
    val newsFlow: Flow<List<News>>
    val loadingFlow: Flow<Boolean>
    val error:Flow<Boolean>//to show place holder maybe
    val message:Flow<String>

    fun openInfo(newsData:News)

}