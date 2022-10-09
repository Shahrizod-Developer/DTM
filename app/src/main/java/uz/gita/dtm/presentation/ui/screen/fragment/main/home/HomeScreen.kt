package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.data.models.persondata.Applicant
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport
import uz.gita.dtm.databinding.ScreenHomeBinding
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.HomeViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val viewBinding: ScreenHomeBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.loading
        viewModel.serviceList.onEach {

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        lifecycleScope.launchWhenCreated {
            viewModel.add(
                Education(
                    id = "0",
                    name = "1",
                    region = "1",
                    district = "1",
                    institution = "1",
                    yearOfGraduation = "1",
                    documentNumber = 1L,
                    documentSeries = "1"
                )
            )
        }
    }
}