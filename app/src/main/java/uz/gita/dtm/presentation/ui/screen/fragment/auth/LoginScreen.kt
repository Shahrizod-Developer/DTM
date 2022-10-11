package uz.gita.dtm.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.databinding.ScreenLoginBinding
import uz.gita.dtm.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.LoginScreenViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.recaptchaQuestionLiveData.collectLatest {
                binding.textRecaptcha.text = it
            }
        }


        binding.btnBack.setOnClickListener { viewModel.btnBack() }
        binding.btnLogin.setOnClickListener {
            if (viewModel.checkRecaptcha(binding.inputAnswer.text.toString())) {
                viewModel.btnLogin(
                    User(
                        binding.inputNumber.text.toString(),
                        binding.inputPassword.text.toString()
                    )
                )
            } else {
                Toast.makeText(requireContext(), R.string.text7, Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegister.setOnClickListener { viewModel.openRegistrationScreen() }
        binding.btnRestorePassword.setOnClickListener { viewModel.restorePassword() }
    }
}