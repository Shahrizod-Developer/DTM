package uz.gita.dtm.presentation.ui.screen.fragment.splash

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenSplashBinding
import uz.gita.dtm.presentation.ui.viewmodel.SplashViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.SplashViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewBinding: ScreenSplashBinding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel:SplashViewModel by viewModels<SplashViewModelImpl>()
}