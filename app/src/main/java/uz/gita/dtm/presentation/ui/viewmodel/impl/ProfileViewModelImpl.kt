package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.domain.usecase.AuthUseCase
import uz.gita.dtm.domain.usecase.ProfileUseCase
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.main.MainScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.ProfileViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val authUseCase: AuthUseCase,
    private val navigator: Navigator
) : ProfileViewModel, ViewModel() {
    override val userPresence = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            authUseCase.userPresence().collectLatest {
                userPresence.emit(it)
            }
        }
    }

    override suspend fun openLoginScreen() {
        navigator.navigateTo(MainScreenDirections.actionGlobalLoginScreen())
    }

}