package assignment.satu

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assignment.satu.databinding.ActivityReceiveIntentBinding

class ActivityReceiveIntent : AppCompatActivity() {
    private lateinit var binding: ActivityReceiveIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_receive_intent)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding = ActivityReceiveIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // PRIMITIVE DATA
        var temp = intent.getIntExtra("EXTRA_NUMBER", 0).toString()
        var tempp = intent.getStringExtra("EXTRA_COLOR")
        binding.tvNum.text = temp
        binding.tvText.text = tempp 
        Log.d("ActivityReceiveIntent", "Number: $temp, Color: $tempp")
        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}