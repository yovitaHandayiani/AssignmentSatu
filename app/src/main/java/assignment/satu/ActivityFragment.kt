package assignment.satu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assignment.satu.databinding.ActivityFragmentBinding

class ActivityFragment : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val fragment1 = Blank1Fragment()
        val fragment = mFragmentManager.findFragmentByTag(Blank1Fragment::class.java.simpleName)
        if(fragment !is Blank1Fragment){
            mFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment1, Blank1Fragment::class.java.simpleName)
                .commit()
        }
        val fragment2 = Blank2Fragment()
    }
}