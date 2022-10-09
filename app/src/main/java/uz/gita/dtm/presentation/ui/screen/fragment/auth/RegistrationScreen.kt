package uz.gita.dtm.presentation.ui.screen.fragment.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.dtm.R
import uz.gita.dtm.presentation.ui.viewmodel.RegistrationScreenViewModel

class RegistrationScreen : Fragment() {

    companion object {
        fun newInstance() = RegistrationScreen()
    }

    private lateinit var viewModel: RegistrationScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_registration, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}