package uz.gita.dtm.presentation.ui.screen.fragment.main.news.info


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.databinding.ScreenNewsInfoBinding
import uz.gita.dtm.presentation.ui.viewmodel.ScreenNewsInfoViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.NewsInfoViewModelImpl
import java.text.SimpleDateFormat

@AndroidEntryPoint
class NewsInfoScreen : Fragment(R.layout.screen_news_info) {
    private val viewBinding: ScreenNewsInfoBinding by viewBinding(ScreenNewsInfoBinding::bind)
    private val viewModel: ScreenNewsInfoViewModel by viewModels<NewsInfoViewModelImpl>()
    private val args: NewsInfoScreenArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.ivBackToNews.setOnClickListener {
            viewModel.backToNews()
        }
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = dateFormat.format(args.news.date)

        viewBinding.tvItemDate.text = date
        viewBinding.tvItemContent.text = args.news.desc
        Picasso
            .get()
            .load(args.news.image)
            .into(viewBinding.ivItem, object : Callback {
                override fun onSuccess() {
                    viewBinding.lottieLoading.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    viewBinding.lottieLoading.visibility = View.GONE
                    viewBinding.ivItem.setImageResource(R.drawable.bachelor_cap_svgrepo_com)
                }
            })
    }
}
