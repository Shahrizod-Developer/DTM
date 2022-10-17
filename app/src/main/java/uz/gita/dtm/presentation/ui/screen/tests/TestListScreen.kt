package uz.gita.dtm.presentation.ui.screen.tests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.data.models.tests.TestListItem
import uz.gita.dtm.databinding.ScreenTestListBinding
import uz.gita.dtm.presentation.adapter.TestListAdapter

@AndroidEntryPoint
class TestListScreen : Fragment(R.layout.screen_test_list) {

    private val binding: ScreenTestListBinding by viewBinding(ScreenTestListBinding::bind)
    val adapter: TestListAdapter by lazy { TestListAdapter() }
    private val args: TestListScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.title.text = args.test.title
        adapter.submitList(list)
        binding.rv.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter.onCLickItem {
            findNavController().navigate(R.id.action_testListScreen_to_testScreen)
        }
    }

    private val list = arrayListOf(
        TestListItem("1-axborotnoma", 2378346743, "o'zbekcha"),
        TestListItem("1-axborotnoma", 2378346743, "ruscha"),
        TestListItem("2-axborotnoma", 2378346743, "o'zbekcha"),
        TestListItem("3-axborotnoma", 2378346743, "o'zbekcha"),
        TestListItem("4-axborotnoma", 2378346743, "o'zbekcha"),
        TestListItem("5-axborotnoma", 2378346743, "o'zbekcha"),
        TestListItem("6-axborotnoma", 2378346743, "o'zbekcha"),
    )
}