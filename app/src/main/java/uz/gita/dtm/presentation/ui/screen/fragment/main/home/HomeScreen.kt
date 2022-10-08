package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenHomeBinding
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.HomeViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val viewBinding: ScreenHomeBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel:HomeViewModel by viewModels<HomeViewModelImpl>()

}