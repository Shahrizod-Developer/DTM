package uz.gita.dtm.presentation.ui.screen.fragment.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenRegistrationBinding
import uz.gita.dtm.presentation.ui.viewmodel.RegistrationScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.RegistrationScreenViewModelImpl

@AndroidEntryPoint
class RegistrationScreen : Fragment(R.layout.screen_registration) {

    private val binding: ScreenRegistrationBinding by viewBinding(ScreenRegistrationBinding::bind)
    private val viewModel: RegistrationScreenViewModel by viewModels<RegistrationScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }

}