package uz.gita.dtm.presentation.ui.screen.tests

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.data.models.data.Database
import uz.gita.dtm.databinding.ScreenTestBinding
import uz.gita.dtm.presentation.adapter.QuestionAdapter

@AndroidEntryPoint
class TestScreen : Fragment(R.layout.screen_test) {

    private val binding: ScreenTestBinding by viewBinding(ScreenTestBinding::bind)
    private val adapter: QuestionAdapter by lazy { QuestionAdapter() }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.title.text = "1-axborotnoma"

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.finish.setOnClickListener {
            findNavController().popBackStack()
        }
//        binding.about.setOnClickListener {
//
//        }

        adapter.submitList(Database.instance?.initQuestions())
        binding.firstSubject.adapter = adapter
    }
}