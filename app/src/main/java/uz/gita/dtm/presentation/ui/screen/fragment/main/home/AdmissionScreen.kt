package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenAdmissionBinding

@AndroidEntryPoint
class AdmissionScreen : Fragment(R.layout.screen_admission) {

    private val binding: ScreenAdmissionBinding by viewBinding(ScreenAdmissionBinding::bind)

}