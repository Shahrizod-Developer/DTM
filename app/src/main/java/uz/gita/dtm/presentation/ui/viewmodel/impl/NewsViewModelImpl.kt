package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.usecase.NewsUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.news.NewsScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.NewsViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val navigator: Navigator
) : NewsViewModel, ViewModel() {
    override val newsFlow: MutableSharedFlow<List<News>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val loadingFlow: MutableSharedFlow<Boolean> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val error: MutableSharedFlow<Boolean> = MutableSharedFlow()
    override val message: MutableSharedFlow<String> = MutableSharedFlow()

    override fun openInfo() {
        viewModelScope.launch { navigator.navigateTo(NewsScreenDirections.actionNewsScreenToNewsInfoScreen()) }
    }

    init {
        viewModelScope.launch {
            newsUseCase.getAllNews().collectLatest {
                loadingFlow.emit(true)
                when (it) {
                    is ResultData.Success -> {
                        newsFlow.emit(it.data)
                        loadingFlow.emit(false)
                    }
                    is ResultData.Error -> {
                        error.emit(true)
                    }

                    is ResultData.Message -> {
                        message.emit(it.message.toString())
                    }
                }
            }
        }
    }
}