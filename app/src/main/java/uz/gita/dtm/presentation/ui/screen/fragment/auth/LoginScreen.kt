package uz.gita.dtm.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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
//        viewModel.openRegistrationScreen.observe(this) {
//            navigation.navigate(LoginScreenDirections.actionLoginScreenToRegistrationScreen())
//        }
//        viewModel.btnBackLiveData.observe(this) {
//            navigation.popBackStack()
//        }
//        viewModel.openVerificationScreen.observe(this) {
//            navigation.navigate(LoginScreenDirections.actionLoginScreenToVerificationScreen())
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recaptchaQuestionLiveData.observe(viewLifecycleOwner) {
            binding.textRecaptcha.text = it
        }

        binding.btnRegister.setOnClickListener {
            viewModel.openRegistrationScreen()
        }
        binding.btnLogin.setOnClickListener {
            if (viewModel.checkRecaptcha(binding.inputAnswer.text.toString().toInt())) {
                Log.d("TTT", binding.inputNumber.text.toString())
                Log.d("TTT", binding.inputPassword.text.toString())
                viewModel.btnLogin(
                    User(
                        binding.inputNumber.text.toString(),
                        binding.inputPassword.text.toString()
                    )
                )
            }
        }
        binding.btnBack.setOnClickListener { viewModel.btnBack() }
        binding.btnRestorePassword.setOnClickListener { viewModel.restorePassword() }
    }
}