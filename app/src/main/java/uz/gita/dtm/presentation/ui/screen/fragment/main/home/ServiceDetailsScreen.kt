package uz.gita.dtm.presentation.ui.screen.fragment.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.dtm.R
import uz.gita.dtm.databinding.ScreenServiceDetailBinding

class ServiceDetailsScreen : Fragment(R.layout.screen_service_detail) {

    private val binding: ScreenServiceDetailBinding by viewBinding(ScreenServiceDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}