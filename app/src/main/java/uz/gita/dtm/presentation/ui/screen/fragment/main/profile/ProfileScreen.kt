package uz.gita.dtm.presentation.ui.screen.fragment.main.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dtm.R
import uz.gita.dtm.data.models.request.ApplicantRequest
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.databinding.DialogOwnerDataBinding
import uz.gita.dtm.databinding.DialogWhatBinding
import uz.gita.dtm.databinding.ScreenProfileBinding
import uz.gita.dtm.presentation.ui.viewmodel.ProfileViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ProfileViewModelImpl

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.screen_profile) {

    private val binding: ScreenProfileBinding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch {
            viewModel.getOwnerData(
                ApplicantRequest(
                    MySharedPreference.series,
                    MySharedPreference.passportNumber
                ), false
            )

        }
        viewModel.passportData.onEach {
            binding.fullName.text = it.firstName + "\n" + it.lastName + "\n" + it.fatherName

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.bachelor_cap_svgrepo_com)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform()

            Glide.with(requireContext()).load(it.image).apply(options).into(binding.image)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.userPresence.onEach {
            if (it) {
                binding.enter.visibility = View.GONE
                binding.scroll.visibility = View.VISIBLE
            } else {
                binding.enter.visibility = View.VISIBLE
                binding.scroll.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.ownerDataCon.setOnClickListener {
            Log.d("HHH", "Salom")
            lifecycleScope.launch {
                viewModel.onclickOwnerData()
            }
        }
        viewModel.showOwnerDialogLiveData.observe(this) {
            showOwnerDialog()
        }

        viewModel.showOwnerDialog.observe(this) {
            showDialog()
        }

        binding.enter.setOnClickListener {
            lifecycleScope.launch {
                viewModel.openLoginScreen()
            }
        }
        binding.address.setOnClickListener {
            lifecycleScope.launch {
                viewModel.onClickAddress()
            }
        }
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogWhatBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialogBinding.enter.setOnClickListener {
            viewModel.openGetOwnerDataScreen()
            dialog.cancel()
        }
        dialog.setView(dialogBinding.root)
        dialog.show()
    }

    private fun showOwnerDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            DialogOwnerDataBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        dialogBinding.cancel.setOnClickListener {
            dialog.cancel()
        }
        dialogBinding.ok.setOnClickListener {
            viewModel.openGetOwnerDataScreen()
            dialog.cancel()
        }
        dialog.setView(dialogBinding.root)
        dialog.show()
    }
}