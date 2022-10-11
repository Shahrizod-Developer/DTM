package uz.gita.dtm.presentation.ui.screen.fragment.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenVerificationBinding
import uz.gita.dtm.presentation.ui.viewmodel.VerificationScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.VerificationScreenViewModelImpl

@AndroidEntryPoint
class VerificationScreen : Fragment(R.layout.screen_verification) {

    private val binding: ScreenVerificationBinding by viewBinding(ScreenVerificationBinding::bind)
    private val viewModel: VerificationScreenViewModel by viewModels<VerificationScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openRegisterScreenLiveData.observe(this) {
            if (::timer.isInitialized) {
                timer.cancel()
            }
            navigation.navigate(VerificationScreenDirections.actionVerificationScreenToRegistrationScreen())
        }
        viewModel.openMainScreenLiveData.observe(this) {
            if (::timer.isInitialized) {
                timer.cancel()
            }
            Toast.makeText(requireContext(), "TIZIMGA KIRILDI", Toast.LENGTH_SHORT).show()
        }
        viewModel.btnBackLiveData.observe(this) {
            if (::timer.isInitialized) {
                timer.cancel()
            }
            navigation.popBackStack()
        }
        viewModel.messageLiveData.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegister.setOnClickListener {
            viewModel.btnRegister(requireContext(), binding.inputCode.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        timer = object : CountDownTimer(60_000, 2) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished > 1000) {
                    binding.textTime.text = millisUntilFinished.toString().substring(0, 2)
                } else if (millisUntilFinished > 100) {
                    binding.textTime.text = millisUntilFinished.toString().substring(0, 1)
                } else {
                    binding.textTime.text = "0"
                }
            }

            override fun onFinish() {
                binding.btnResendSms.setOnClickListener { viewModel.btnResendSms() }
            }
        }.start()
    }
}