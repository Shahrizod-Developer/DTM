package uz.gita.dtm.presentation.ui.screen.fragment.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenProfileBinding
import uz.gita.dtm.presentation.ui.screen.fragment.main.MainScreen
import uz.gita.dtm.presentation.ui.screen.fragment.main.MainScreenDirections
import uz.gita.dtm.presentation.ui.viewmodel.ProfileViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ProfileViewModelImpl

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.screen_profile) {
    private val binding: ScreenProfileBinding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.userPresence.onEach {
            if (it) {
                binding.enter.visibility = View.GONE
                binding.scroll.visibility = View.VISIBLE
            } else {
                binding.enter.visibility = View.VISIBLE
                binding.scroll.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.enter.setOnClickListener {
            lifecycleScope.launch {
                viewModel.openLoginScreen()
            }
        }
    }
}