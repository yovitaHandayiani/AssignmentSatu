package assignment.satu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import assignment.satu.Blank2Fragment.Companion.EXTRA_VALUE
import assignment.satu.databinding.FragmentProductDetailBinding

class FragmentProductDetail : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var getTitle: String = ""
        var getPrice: String = ""
        var getImage: Int = 0
        var getDisc: String = ""
        var getBool: Boolean = false


        //get bundle yang dari activity
        if (arguments != null) {
            // Receive the passed data from arguments
            arguments?.let {
                getTitle = it.getString("EXTRA_TITLE", "Default Title")
                getPrice = it.getString("EXTRA_PRICE").toString()
                getImage = it.getInt("EXTRA_IMAGE", R.drawable.xiaolongbao)
                getDisc = it.getString("EXTRA_DISC").toString()
                getBool = it.getBoolean("EXTRA_BOOL", false)
            }
            Log.d("IntentData", "Title: $getTitle")
            Log.d("IntentData", "Price: $getPrice")
            Log.d("IntentData", "Image: $getImage")
            Log.d("IntentData", "Discount: $getDisc")
            Log.d("IntentData", "Bool: $getBool")

            if(getBool == true){
                binding.tvMenuTitleFragment.text = getTitle
                binding.tvMenuPriceFragment.text = getPrice.toString()
                binding.ivProductFragment.setImageResource(getImage)
                binding.tvMenuBeforePriceFragment.isVisible = true
                binding.mdChipFragment.isVisible = true
                Log.d("IntentData", "Image resource ID: ${getTitle} ${getPrice} ${getImage} ${getDisc} ${getBool}")
            }else{
                binding.tvMenuTitleFragment.text = getTitle
                binding.tvMenuPriceFragment.text = getPrice.toString()
                binding.ivProductFragment.setImageResource(getImage)
                binding.tvMenuBeforePriceFragment.text = getDisc.toString()
                binding.tvMenuBeforePriceFragment.isVisible = false
                binding.mdChipFragment.isVisible = false
                Log.d("IntentData", "Image resource ID: ${getTitle} ${getPrice} ${getImage} ${getDisc} ${getBool}")
            }
        }
        binding.mdTopAppBarFragment.setNavigationOnClickListener{
            // Pop the fragment from the back stack (return to the previous fragment)
//            parentFragmentManager.popBackStack()
            // Finish the current activity and go back to the MainActivity
//            activity?.finish()

            if (parentFragmentManager.backStackEntryCount > 0) {
                // Check if the previous stack is a fragment and pop it
                parentFragmentManager.popBackStack()
            } else {
                // If there is no fragment in the stack, go back to the activity
                activity?.finish()
            }
        }

        binding.bTextIconButtonFragment.setOnClickListener {

        }
    }
}