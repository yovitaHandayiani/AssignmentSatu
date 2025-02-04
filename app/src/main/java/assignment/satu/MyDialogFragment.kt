package assignment.satu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import assignment.satu.databinding.FragmentMyDialogBinding

class MyDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentMyDialogBinding
    private var dialogListener: DialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the width of the dialog to match the screen width
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,  // Width is MATCH_PARENT
            WindowManager.LayoutParams.WRAP_CONTENT  // Height remains WRAP_CONTENT
        )

        binding.btnSubmit.setOnClickListener {
            dialogListener?.onSubmit(binding.editName.text.toString())
            dialog?.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is Blank1Fragment) {
            this.dialogListener = fragment.dialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.dialogListener
    }

    interface DialogListener {
        fun onSubmit(text: String)
    }
}