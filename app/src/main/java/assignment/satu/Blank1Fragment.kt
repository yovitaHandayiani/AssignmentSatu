package assignment.satu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import assignment.satu.databinding.FragmentBlank1Binding


class Blank1Fragment : Fragment() {
    private lateinit var binding: FragmentBlank1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank1, container, false)
        binding = FragmentBlank1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            val frament2 = Blank2Fragment()
            val bundle2 = Bundle()
            bundle2.putString(Blank2Fragment.EXTRA_VALUE, "value")
            frament2.arguments = bundle2

            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(
                        R.id.fragment_container,
                        frament2,
                        Blank2Fragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
        }
        binding.btnDialog.setOnClickListener {
            val mDialogFragment = MyDialogFragment()
            val mFragmentManager = childFragmentManager
            mDialogFragment.show(mFragmentManager, MyDialogFragment::class.java.simpleName)

        }
    }

    var dialogListener: MyDialogFragment.DialogListener = object : MyDialogFragment.DialogListener {
        override fun onSubmit(text: String) {
            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
        }

    }
}