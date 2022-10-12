package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenHomeBinding
import uz.gita.dtm.presentation.adapter.NewsLetterAdapter
import uz.gita.dtm.presentation.adapter.ServiceAdapter
import uz.gita.dtm.presentation.ui.screen.dialog.AllServiceDialog
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.HomeViewModelImpl
import java.util.*


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter: ServiceAdapter by lazy { ServiceAdapter() }
    private val adapterTest: NewsLetterAdapter by lazy { NewsLetterAdapter() }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.loading.onEach {

            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadingNewsLetter.onEach {

            if (it) {
                binding.progressNews.visibility = View.VISIBLE
            } else {
                binding.progressNews.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.message.onEach {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        adapter.onCLickItem {
            viewModel.openServiceDetail(it)
        }

        binding.search.setOnClickListener {
            val dialog = AllServiceDialog()
            dialog.show(childFragmentManager, "")
        }

        viewModel.serviceList.onEach {

            if (it == null) {
                binding.text.visibility = View.VISIBLE
            } else {
                binding.text.visibility = View.GONE
            }
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        viewModel.newsLettersList.onEach {

            if (it == null) {
                binding.textNews.visibility = View.VISIBLE
            } else {
                binding.textNews.visibility = View.GONE
            }
            adapterTest.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        binding.viewPager.adapter = adapter
        binding.rvNews.adapter = adapterTest
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.viewPager)
//        snapHelper.attachToRecyclerView(binding.rvNews)

    }
}