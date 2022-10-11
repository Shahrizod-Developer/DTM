package uz.gita.dtm.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.presentation.navigation.Navigator
import uz.gita.dtm.presentation.ui.screen.fragment.LockScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.LockViewModel
import javax.inject.Inject

@HiltViewModel
class LockViewModelImpl @Inject constructor(
    private val navigator: Navigator
): LockViewModel, ViewModel() {
    override val savedPin: String = MySharedPreference.pinCode


    override fun checkPinCode(pinCode: String) {
        if (savedPin == pinCode) {
            viewModelScope.launch {
                navigator.navigateTo(LockScreenDirections.actionLockScreenToMainScreen())
            }
        }
    }
}