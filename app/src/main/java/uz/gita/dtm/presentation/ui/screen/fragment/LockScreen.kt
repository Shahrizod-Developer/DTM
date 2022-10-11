package uz.gita.dtm.presentation.ui.screen.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenLockBinding
import uz.gita.dtm.presentation.ui.viewmodel.LockViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.LockViewModelImpl

@AndroidEntryPoint
class LockScreen : Fragment(R.layout.screen_lock) {
    private val viewBinding: ScreenLockBinding by viewBinding(ScreenLockBinding::bind)
    private val viewModel: LockViewModel by viewModels<LockViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}