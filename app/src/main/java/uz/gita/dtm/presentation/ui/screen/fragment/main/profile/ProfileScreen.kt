package uz.gita.dtm.presentation.ui.screen.fragment.main.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenProfileBinding
import uz.gita.dtm.presentation.ui.viewmodel.ProfileViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ProfileViewModelImpl

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.screen_profile) {
    private val viewBinding: ScreenProfileBinding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

}