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
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //implement fragment
        //this is what would be opened after produk serupa is clicked
        //attach frament here
        //xml should be empty
    }
}