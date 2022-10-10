package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenHomeBinding
import uz.gita.dtm.presentation.adapter.ServiceAdapter
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.HomeViewModelImpl
import java.util.*


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter: ServiceAdapter by lazy { ServiceAdapter(requireContext()) }

    @SuppressLint("FragmentLiveDataObserve")
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

        viewModel.serviceList.onEach {

            if (it == null) {
                binding.text.visibility = View.VISIBLE
            } else {
                binding.text.visibility = View.GONE
            }
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.viewPager.adapter = adapter

        binding.viewPager.currentItem = 2
        val timer = Timer()
        timer.scheduleAtFixedRate(timerTask(), 1000, 3000)
    }

    private fun timerTask(): TimerTask {

        val timer = object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    if (binding.viewPager.currentItem < adapter.currentList.size - 1) {
                        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                    } else {
                        binding.viewPager.currentItem = 0
                        cancel()
                    }
                }
            }
        }
        return timer
    }
}