package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.viewmodel.ScreenNewsInfoViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenNewsInfoViewModelImpl @Inject constructor(
    private val navigator:Navigator
): ScreenNewsInfoViewModel, ViewModel() {

    override fun backToNews() {
        viewModelScope.launch {
            navigator.back()
        }
    }
}