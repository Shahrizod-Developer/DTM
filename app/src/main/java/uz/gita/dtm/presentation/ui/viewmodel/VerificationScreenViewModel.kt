package uz.gita.dtm.presentation.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData

interface VerificationScreenViewModel {
    val btnBackLiveData: LiveData<Unit>
    val messageLiveData: LiveData<String>
    val openMainScreenLiveData: LiveData<Unit>
    val openRegisterScreenLiveData: LiveData<Unit>
    fun btnBack()
    fun btnResendSms()
    fun btnRegister(context: Context, code: String)
}