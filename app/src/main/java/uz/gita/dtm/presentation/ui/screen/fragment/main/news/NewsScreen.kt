package uz.gita.dtm.presentation.ui.screen.fragment.main.news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.databinding.ScreenNewsBinding
import uz.gita.dtm.presentation.adapter.NewsAdapter
import uz.gita.dtm.presentation.ui.viewmodel.NewsViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.NewsViewModelImpl

@AndroidEntryPoint
class NewsScreen : Fragment(R.layout.screen_news) {
    private val viewBinding: ScreenNewsBinding by viewBinding(ScreenNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModels<NewsViewModelImpl>()
    private val adapter: NewsAdapter by lazy { NewsAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.containerNews.adapter = adapter
        adapter.triggerItemClickListener {
            val bundle = Bundle()
            bundle.putSerializable("NEWS",it)
//            findNavController().navigate(NewsScreenDirections.actionNewsScreenToNewsInfoScreen())
//            viewModel.openInfo()
            findNavController().navigate(R.id.action_newsScreen_to_newsInfoScreen, bundle)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.newsFlow.collectLatest {
                adapter.submitList(it)
            }
        }

        viewModel.loadingFlow.onEach {
            adapter.triggerLoadingListener {
                it
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }
}