package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.splash.SplashScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigator: Navigator
) : SplashViewModel, ViewModel() {

    init {
        if (MySharedPreference.pinCode == "") {
            viewModelScope.launch {
                delay(1000)
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
            }
        } else {
            viewModelScope.launch {
                delay(1000)
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLockScreen())
            }
        }
    }
}