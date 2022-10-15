package uz.gita.dtm.presentation.ui.screen.fragment.main.application

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.databinding.ScreenApplicationBinding
import uz.gita.dtm.presentation.adapter.ApplicationAdapter
import uz.gita.dtm.presentation.ui.screen.dialog.AllServiceDialog
import uz.gita.dtm.presentation.ui.viewmodel.ApplicationViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ApplicationViewModelImpl

@AndroidEntryPoint
class ApplicationScreen : Fragment(R.layout.screen_application) {
    private val binding: ScreenApplicationBinding by viewBinding(ScreenApplicationBinding::bind)
    private val viewModel: ApplicationViewModel by viewModels<ApplicationViewModelImpl>()
    private val adapter: ApplicationAdapter by lazy { ApplicationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.loading.onEach {

            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.message.onEach {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.applicationListFLow.onEach {

            if (it == null) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.rv.adapter = adapter
    }
}