package uz.gita.dtm.presentation.ui.viewmodel

interface LockViewModel {
    val savedPin: String

    fun checkPinCode(pinCode: String)
}