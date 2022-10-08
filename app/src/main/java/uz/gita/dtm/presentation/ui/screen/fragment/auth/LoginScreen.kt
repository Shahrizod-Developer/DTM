package uz.gita.dtm.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenLoginBinding
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.LoginScreenViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            viewModel.btnLogin(requireActivity(), "+99891594308", "12345678")
        }
    }
}