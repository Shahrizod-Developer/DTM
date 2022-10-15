package uz.gita.dtm.presentation.ui.screen.fragment.main.profile

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenOwnerDataBinding
import uz.gita.dtm.presentation.ui.viewmodel.OwnerDataViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.OwnerDataViewModelImpl
import java.text.SimpleDateFormat


@AndroidEntryPoint
class OwnerDataScreen : Fragment(R.layout.screen_owner_data) {

    private val binding: ScreenOwnerDataBinding by viewBinding(ScreenOwnerDataBinding::bind)
    private val viewModel: OwnerDataViewModel by viewModels<OwnerDataViewModelImpl>()
    private lateinit var dateFormat: SimpleDateFormat

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dateFormat = SimpleDateFormat("dd-MM-yyyy")

        viewModel.loading.onEach {

            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.message.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.updateOwnerData.setOnClickListener {
            viewModel.openUpdateOwnerDataScreen()
        }

        viewModel.passportData.onEach {
            if (it != null) {
                binding.birthday.text = dateFormat.format(it.birthday)
                binding.fatherName.text = it.fatherName
                binding.firstName.text = it.firstName
                binding.lastName.text = it.lastName
                binding.series.text = it.passportsSeries + it.passportSeriesNumber
                binding.jShShR.text = it.jShShir.toString()

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.bachelor_cap_svgrepo_com)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
                    .dontTransform()

                Glide.with(requireContext()).load(it.image).apply(options).into(binding.image)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}