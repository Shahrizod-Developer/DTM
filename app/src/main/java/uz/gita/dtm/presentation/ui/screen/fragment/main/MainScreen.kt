package uz.gita.dtm.presentation.ui.screen.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenMainBinding

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val navView = binding.navView
        val navController =
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
//                R.id.screenRecipe -> hideBottomNav()
//                R.id.screenAdvanced -> hideBottomNav()
//                R.id.screenArticle -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }
    private fun showBottomNav() {
        binding.navView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.navView.visibility = View.GONE
    }
}