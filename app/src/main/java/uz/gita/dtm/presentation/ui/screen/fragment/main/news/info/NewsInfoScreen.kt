package uz.gita.dtm.presentation.ui.screen.fragment.main.news.info


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenNewsInfoBinding
import uz.gita.dtm.presentation.ui.viewmodel.ScreenNewsInfoViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.ScreenNewsInfoViewModelImpl

@AndroidEntryPoint
class NewsInfoScreen : Fragment(R.layout.screen_news_info) {
    private val viewBinding:ScreenNewsInfoBinding by viewBinding(ScreenNewsInfoBinding::bind)
    private val viewModel:ScreenNewsInfoViewModel by viewModels<ScreenNewsInfoViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.ivBackToNews.setOnClickListener { viewModel.backToNews() }
    }

}