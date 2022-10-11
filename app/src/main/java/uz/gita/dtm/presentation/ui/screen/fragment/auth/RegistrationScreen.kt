package uz.gita.dtm.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.databinding.ScreenRegistrationBinding
import uz.gita.dtm.presentation.ui.viewmodel.RegistrationScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.RegistrationScreenViewModelImpl

@AndroidEntryPoint
class RegistrationScreen : Fragment(R.layout.screen_registration) {

    private val binding: ScreenRegistrationBinding by viewBinding(ScreenRegistrationBinding::bind)
    private val viewModel: RegistrationScreenViewModel by viewModels<RegistrationScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel.btnBackLiveData.observe(this) {
//            navigation.popBackStack()
//        }
//        viewModel.openVerificationScreen.observe(this) {
//            navigation.navigate(RegistrationScreenDirections.actionRegistrationScreenToVerificationScreen())
//        }
//        viewModel.messageForPassword.observe(this) {
//            Toast.makeText(requireContext(), getText(it), Toast.LENGTH_SHORT).show()
//        }
//        viewModel.messageForPhoneNumber.observe(this) {
//            Toast.makeText(requireContext(), getText(it), Toast.LENGTH_SHORT).show()
//        }
//        viewModel.messageLiveData.observe(this) {
//            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.recaptchaQuestionLiveData.observe(viewLifecycleOwner) {
//            binding.textRecaptcha.text = it
//        }
//        binding.btnBack.setOnClickListener {
//            viewModel.btnBack()
//        }
//        binding.btnRegister.setOnClickListener {
//            if (viewModel.checkRecaptcha(binding.inputAnswer.text.toString())) {
//                viewModel.btnRegister(
//                    requireActivity(),
//                    User(binding.inputNumber.text.toString(), binding.inputPassword.text.toString())
//                )
//            } else {
//                Toast.makeText(requireContext(), getText(R.string.recaptcha), Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }
}