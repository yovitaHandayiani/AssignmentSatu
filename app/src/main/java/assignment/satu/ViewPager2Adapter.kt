package assignment.satu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter (fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2  // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Blank1Fragment()  // First tab
            1 -> Blank2Fragment()  // Second tab
//            2 -> TabFragment3()  // Third tab
            else -> Blank1Fragment()
        }
    }
}