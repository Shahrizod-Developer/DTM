package uz.gita.dtm.presentation.ui.screen.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.dtm.databinding.DialogAllservicesBinding

class AllServiceDialog : BottomSheetDialogFragment() {

    private var _dialogBinding: DialogAllservicesBinding? = null
    private val dialogBinding get() = _dialogBinding!!

    private var dialogDismissClickListener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _dialogBinding = DialogAllservicesBinding.inflate(inflater, container, false)
        return dialogBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialogBinding.ivDismissDialog.setOnClickListener { dialogDismissClickListener?.invoke() }
    }

    fun triggerDialogDismiss(block:()->Unit) {
        dialogDismissClickListener = block
    }

    override fun onDestroy() {
        super.onDestroy()
        _dialogBinding = null
    }
}