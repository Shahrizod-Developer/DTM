package uz.gita.dtm.presentation.ui.screen.dialog

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.data.models.service.Service
import uz.gita.dtm.databinding.DialogAllServicesBinding
import uz.gita.dtm.presentation.adapter.SearchServiceAdapter
import uz.gita.dtm.presentation.ui.viewmodel.HomeViewModel
import uz.gita.dtm.presentation.ui.viewmodel.impl.HomeViewModelImpl
import java.util.*


@AndroidEntryPoint
class AllServiceDialog : BottomSheetDialogFragment() {


    private lateinit var binding: DialogAllServicesBinding
    private val adapter: SearchServiceAdapter by lazy { SearchServiceAdapter() }
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DialogAllServicesBinding.inflate(inflater, container, false)

        binding.rv.adapter = adapter

        binding.deleteText.setOnClickListener {
            if (binding.searchService.text.toString().isNotEmpty()) {
                binding.searchService.setText("")
            }
        }
        binding.arrow.setOnClickListener {
            this.dismiss()
        }

        binding.searchService.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val result = arrayListOf<Service>()

                adapter.currentList.forEach {
                    if (it.title?.toUpperCase()
                            ?.contains(p0.toString().toUpperCase())!!
                    ) result.add(it)
                }
                adapter.submitList(result)
                binding.rv.adapter = adapter
            }


            override fun afterTextChanged(p0: Editable?) {

                if (p0.toString().isNotEmpty()) {
                    val result = arrayListOf<Service>()

                    adapter.currentList.forEach {
                        if (it.title.toUpperCase(Locale.ROOT)
                                ?.startsWith(p0.toString().toUpperCase())!!
                        ) result.add(it)
                    }
                    adapter.submitList(result)
                    binding.rv.adapter = adapter
                } else {
                    viewModel.serviceList.onEach {
                        adapter.submitList(it)
                    }.launchIn(viewLifecycleOwner.lifecycleScope)

                    binding.rv.adapter = adapter
                }
            }
        })

        return binding.root
    }

}