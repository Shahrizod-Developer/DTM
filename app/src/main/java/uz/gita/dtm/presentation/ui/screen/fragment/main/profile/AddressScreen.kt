package uz.gita.dtm.presentation.ui.screen.fragment.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.databinding.ScreenAddressBinding
import uz.gita.dtm.presentation.ui.viewmodel.AddressScreenViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.AddressScreenViewModelImpl

@AndroidEntryPoint
class AddressScreen : Fragment(R.layout.screen_address) {

    private val binding: ScreenAddressBinding by viewBinding(ScreenAddressBinding::bind)
    private val viewModel: AddressScreenViewModel by viewModels<AddressScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.addressFLow.onEach {
            binding.address.setText(it.address)
            binding.region.setText(it.region)
            binding.region.setText(it.region)
        }

        binding.back.setOnClickListener {
            viewModel.back()
        }

        binding.confirm.setOnClickListener {
            viewModel.onClickConfirm(
                ApplicantAddress(
                    id = "",
                    region = binding.region.text.toString(),
                    district = binding.district.text.toString(),
                    address = binding.address.text.toString()
                )
            )
        }
    }
}