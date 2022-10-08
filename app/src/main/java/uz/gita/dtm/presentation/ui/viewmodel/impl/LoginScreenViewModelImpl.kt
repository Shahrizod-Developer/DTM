package uz.gita.dtm.presentation.ui.viewmodel.impl

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor() : LoginScreenViewModel, ViewModel() {
    override val messageLiveData: LiveData<Int>
        get() = TODO("Not yet implemented")
    override val openRegistrationScreen: LiveData<Unit>
        get() = TODO("Not yet implemented")
    override val messageForPhoneNumber: LiveData<Int>
        get() = TODO("Not yet implemented")
    override val messageForPassword: LiveData<Int>
        get() = TODO("Not yet implemented")

    override fun btnLogin(activity: Activity, phoneNumber: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun openRegistrationScreen() {
        TODO("Not yet implemented")
    }

}