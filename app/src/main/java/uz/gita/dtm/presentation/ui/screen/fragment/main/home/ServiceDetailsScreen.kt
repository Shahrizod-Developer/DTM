package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.databinding.DialogAboutServiceBinding
import uz.gita.dtm.databinding.DialogUseServiceBinding
import uz.gita.dtm.databinding.ScreenServiceDetailBinding
import uz.gita.dtm.presentation.ui.viewmodel.ServiceDetailsViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ServiceDetailsViewModelImpl

@AndroidEntryPoint
class ServiceDetailsScreen : Fragment(R.layout.screen_service_detail) {

    private val binding: ScreenServiceDetailBinding by viewBinding(ScreenServiceDetailBinding::bind)
    private val viewModel: ServiceDetailsViewModel by viewModels<ServiceDetailsViewModelImpl>()
    private val args: ServiceDetailsScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.title.text = args.service.title

        binding.useService.setOnClickListener {
            viewModel.onCLickUseService()
        }
        binding.aboutService.setOnClickListener {
            viewModel.showAboutServiceDialog()
        }
        binding.serviceApplication.setOnClickListener {
            viewModel.onCLickApplication()
        }

        binding.back.setOnClickListener {
            viewModel.back()
        }

        lifecycleScope.launch {
            viewModel.showAboutServiceDialogFlow.collectLatest {
                if (it) {
                    showAboutService(args.service)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.showApplicationDialogFlow.collectLatest {
                if (it) {
                    showUseServiceDialog()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.showUseServiceDialogFlow.collectLatest {
                if (it) {
                    showUseServiceDialog()
                }
            }
        }
    }

    private fun showUseServiceDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogUseServiceBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialogBinding.enter.setOnClickListener {
            viewModel.openLoginScreen()
            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(dialogBinding.root)
        dialog.show()
    }

    private fun showAboutService(service: Service) {

        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding =
            DialogAboutServiceBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialogBinding.descService.text = service.desc
        dialogBinding.titleService.text = service.title
        dialogBinding.dismiss.setOnClickListener {
            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(dialogBinding.root)
        dialog.show()

    }
}