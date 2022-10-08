package uz.gita.dtm.presentation.ui.screen.fragment.main.application

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenApplicationBinding
import uz.gita.dtm.presentation.ui.viewmodel.ApplicationViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ApplicationViewModelImpl

@AndroidEntryPoint
class ApplicationScreen:Fragment(R.layout.screen_application) {
    private val viewBinding: ScreenApplicationBinding by viewBinding(ScreenApplicationBinding::bind)
    private val viewModel: ApplicationViewModel by viewModels<ApplicationViewModelImpl>()
}