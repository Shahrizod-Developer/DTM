package uz.gita.dtm.presentation.ui.screen.fragment.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenMainBinding
import uz.gita.dtm.presentation.ui.viewmodel.MainViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewBinding:ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel:MainViewModel by viewModels<MainViewModelImpl>()
}