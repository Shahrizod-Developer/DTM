package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.NewsUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.NewsViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val navigator: Navigator
) : NewsViewModel, ViewModel() {
    override val newsFlow: MutableSharedFlow<ResultData<List<News>>>  = MutableSharedFlow()

    init {
        viewModelScope.launch {
            newsUseCase.getAllNews().collectLatest {
                    newsFlow.emit(it)
                }
//            newsFlow.
            }
        }
}