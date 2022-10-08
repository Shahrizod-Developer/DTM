package uz.gita.dtm.presentation.ui.screen.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenMainBinding
import uz.gita.dtm.presentation.navigation.navController
import uz.gita.dtm.presentation.ui.viewmodel.MainViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewBinding:ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel:MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val navController = requireActivity().findNavController(R.id.fragmentContainerView2).addOnDestinationChangedListener{_,destination,_->
//            when (destination.id) {

//            }

        }
    }

    private fun showBottomNav() {
//        viewBinding.bnv.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
//        viewBinding.bnv.visibility = View.GONE

    }
