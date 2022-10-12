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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.databinding.ScreenRegistrationBinding
import uz.gita.dtm.presentation.ui.viewmodel.RegistrationScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.RegistrationScreenViewModelImpl

@AndroidEntryPoint
class RegistrationScreen : Fragment(R.layout.screen_registration) {

    private val binding: ScreenRegistrationBinding by viewBinding(ScreenRegistrationBinding::bind)
    private val viewModel: RegistrationScreenViewModel by viewModels<RegistrationScreenViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recaptchaQuestionLiveData.onEach {
            binding.textRecaptcha.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.loadingLiveData.onEach {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.messageLiveData.onEach {
            Toast.makeText(
                requireContext(),
                getString(it),
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.messageForPhoneNumber.onEach {
            Toast.makeText(
                requireContext(),
                getString(it),
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.messageForPassword.onEach {
            Toast.makeText(
                requireContext(),
                getString(it),
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        binding.btnBack.setOnClickListener {
            viewModel.btnBack()
        }
        binding.btnRegister.setOnClickListener {
            if (viewModel.checkRecaptcha(binding.inputAnswer.text.toString())) {
                viewModel.btnRegister(
                    requireActivity(),
                    User(binding.inputNumber.text.toString(), binding.inputPassword.text.toString())
                )
            } else {
                Toast.makeText(requireContext(), getText(R.string.text7), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}