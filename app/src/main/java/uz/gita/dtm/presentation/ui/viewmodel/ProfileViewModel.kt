package uz.gita.dtm.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow

interface ProfileViewModel {

    val userPresence: Flow<Boolean>

    suspend fun openLoginScreen()
}