package assignment.satu

import android.content.Intent
import android.hardware.camera2.params.BlackLevelPattern
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.viewpager2.widget.ViewPager2
import assignment.satu.databinding.FragmentIntentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragmentIntent : Fragment() {
    private lateinit var binding: FragmentIntentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBottomNavigation.setOnItemSelectedListener { item ->
            // Get the activity to access its fragment manager and container
            val fragmentContainer = requireActivity().findViewById<View>(R.id.fragment_container_product_detail)
            Log.d("FragmentTransaction", "Fragment container: $fragmentContainer")

            if (fragmentContainer != null) {
                // Access the Activity's FragmentManager for fragment transactions
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()

                when (item.itemId) {
                    R.id.page_1 -> {
                        // Replace with Blank1Fragment in the activity container
                        fragmentTransaction?.replace(
                            R.id.fragment_container_product_detail,  // The fragment container in the Activity layout
                            Blank1Fragment(),
                            Blank1Fragment::class.java.simpleName
                        )
                        fragmentTransaction?.addToBackStack(null)  // Optionally add to the back stack
                        fragmentTransaction?.commit()
                        true
                    }
                    R.id.page_2 -> {
                        // Replace with Blank2Fragment in the activity container
                        fragmentTransaction?.replace(
                            R.id.fragment_container_product_detail,  // The fragment container in the Activity layout
                            Blank2Fragment(),
                            Blank2Fragment::class.java.simpleName
                        )
                        fragmentTransaction?.addToBackStack(null)  // Optionally add to the back stack
                        fragmentTransaction?.commit()
                        true
                    }
                    else -> false
                }
            } else {
                Log.e("FragmentTransaction", "Fragment container not found in the activity!")
                false
            }
        }

//        binding.btBottomNavigation.setOnItemReselectedListener { item ->
//            when(item.itemId) {
//                R.id.page_1 -> {
//                    // Respond to navigation item 1 reselection
//                    true
//                }
//                R.id.page_2 -> {
//                    // Respond to navigation item 2 reselection
//                    true
//                }
//                else -> false
//            }
//        }

        // Set up the ViewPager2 with the TabAdapter
//        val adapter = ViewPager2Adapter(this)  // Use 'this' as the FragmentActivity context
        val adapter = ViewPager2Adapter(requireActivity())
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = true // If you want to make any adjustments like enabling/disabling the swipe feature, you can use the setUserInputEnabled() method on ViewPager2.
//        binding.viewPager.isUserInputEnabled = false //Disable swipe (if you want to prevent the user from swiping between pages):
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Handle page change, e.g., update TabLayout selection
                Log.d("PageChanged", "Page selected: $position")
            }
        })

        // Link the TabLayout and ViewPager2 using TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab 1"
//                    tab.icon = getDrawable(R.drawable.cart_shopping_svgrepo_com)
                    tab.icon = context?.getDrawable(R.drawable.cart_shopping_svgrepo_com)
                }

                1 -> {
                    tab.text = "Tab 2"
//                    tab.icon = getDrawable(R.drawable.back_svgrepo_com)
                    tab.icon = context?.getDrawable(R.drawable.cart_shopping_svgrepo_com)
                }
            }
        }.attach()

        // for Tab layout
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                // Handle tab select
//                when (tab?.position) {
//                    0 -> { /* Show content for Tab 1 */ }
//                    1 -> { /* Show content for Tab 2 */ }
//                    2 -> { /* Show content for Tab 3 */ }
//                }
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                // Handle tab reselect
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                // Handle tab unselect
//            }
//        })

    }

}