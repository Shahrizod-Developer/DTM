package uz.gita.dtm.presentation.ui.screen.fragment.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenLoginBinding
import uz.gita.dtm.databinding.ScreenVerificationBinding
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.VerificationScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.LoginScreenViewModelImpl
import uz.gita.dtm.presentation.ui.viewmodel.impl.VerificationScreenViewModelImpl

@AndroidEntryPoint
class VerificationScreen : Fragment(R.layout.screen_verification) {

    private val binding: ScreenVerificationBinding by viewBinding(ScreenVerificationBinding::bind)
    private val viewModel: VerificationScreenViewModel by viewModels<VerificationScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }

}