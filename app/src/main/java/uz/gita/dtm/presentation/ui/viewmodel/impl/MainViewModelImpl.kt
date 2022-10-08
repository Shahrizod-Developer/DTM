package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dtm.domain.usecase.MainUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val navigator: Navigator
): MainViewModel, ViewModel()