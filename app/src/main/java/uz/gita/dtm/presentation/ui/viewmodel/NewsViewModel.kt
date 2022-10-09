package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData

interface NewsViewModel {
    val newsFlow: Flow<ResultData<List<News>>>

}