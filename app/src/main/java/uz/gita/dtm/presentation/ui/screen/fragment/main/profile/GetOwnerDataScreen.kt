package uz.gita.dtm.presentation.ui.screen.fragment.main.profile

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
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.databinding.ScreenGetOwnerDataBinding
import uz.gita.dtm.presentation.ui.viewmodel.GetOwnerDataViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.GetOwnerDataViewModelImpl

@AndroidEntryPoint
class GetOwnerDataScreen : Fragment(R.layout.screen_get_owner_data) {

    private val binding: ScreenGetOwnerDataBinding by viewBinding(ScreenGetOwnerDataBinding::bind)
    private val viewModel: GetOwnerDataViewModel by viewModels<GetOwnerDataViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.updateOwnerData.setOnClickListener {
            viewModel.onCLickConfirm(
                ApplicantRequest(
                    binding.series.text.toString(),
                    binding.number.text.toString().toLong()
                ),
                true
            )
        }

        viewModel.loading.onEach {

            if (it) {
                binding.lottieLoading.visibility = View.VISIBLE
            } else {
                binding.lottieLoading.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.message.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }
}