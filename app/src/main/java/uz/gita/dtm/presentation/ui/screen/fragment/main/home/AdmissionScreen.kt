package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.data.models.data.Data
import uz.gita.dtm.data.models.data.Data.Companion.admissionList
import uz.gita.dtm.databinding.ScreenAdmissionBinding
import uz.gita.dtm.presentation.adapter.AdmissionAdapter
import uz.gita.dtm.presentation.ui.viewmodel.AdmissionViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.AdmissionViewModelImpl

@AndroidEntryPoint
class AdmissionScreen : Fragment(R.layout.screen_admission) {

    private val binding: ScreenAdmissionBinding by viewBinding(ScreenAdmissionBinding::bind)
    private val viewModel: AdmissionViewModel by viewModels<AdmissionViewModelImpl>()
    private val adapter: AdmissionAdapter by lazy { AdmissionAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter.onCLickItem {
            when (it.name) {
                "Shaxsiy ma'lumotlar" -> {
                    viewModel.openOwnerDataScreen()
                }
            }
        }
        adapter.submitList(admissionList)

        binding.rv.adapter = adapter

    }
}