package uz.gita.dtm.presentation.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface VerificationScreenViewModel {
    val messageLiveData: Flow<Int>
    val loadingLiveData: Flow<Boolean>

    fun btnBack()
    fun btnResendSms()
    fun btnRegister(context: Context, code: String)
}