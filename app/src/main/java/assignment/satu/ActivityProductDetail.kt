package assignment.satu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assignment.satu.databinding.ActivityProductDetailBinding

class ActivityProductDetail : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //this is what would be opened after produk serupa is clicked
        //xml must be empty, because u will attach fragment (which has the view) to this activity
        
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getTitle = intent.getStringExtra("EXTRA_TITLE")
        val getPrice = intent.getIntExtra("EXTRA_PRICE", 0)
        val getImage = intent.getIntExtra("EXTRA_IMAGE", R.drawable.xiaolongbao)
        val getDisc = intent.getIntExtra("EXTRA_DISC", 0)
//        val getBool = intent.getBooleanExtra("EXTRA_BOOL", false)

        //attach fragment to this activity
        val mFragmentManager = supportFragmentManager
        val fragmentDestination = FragmentProductDetail()
        val fragment = mFragmentManager.findFragmentByTag(FragmentProductDetail::class.java.simpleName)

        if(fragment !is FragmentProductDetail){
            mFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_product_detail, fragmentDestination, FragmentProductDetail::class.java.simpleName)
                .commit()
        }

        //translating data passed from activity to fragment
        val bundle = Bundle()
        bundle.putString("EXTRA_TITLE", getTitle)
        bundle.putInt("EXTRA_PRICE", getPrice)
        bundle.putInt("EXTRA_IMAGE", getImage)
        bundle.putInt("EXTRA_DISC", getDisc)
        bundle.putBoolean("EXTRA_BOOL", intent.getBooleanExtra("EXTRA_BOOL", false))
        fragmentDestination.arguments = bundle
    }
}