package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dtm.domain.usecase.ApplicationUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.ApplicationViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModelImpl @Inject constructor(
    private val applicationUseCase: ApplicationUseCase,
    private val navigator: Navigator
): ApplicationViewModel, ViewModel()