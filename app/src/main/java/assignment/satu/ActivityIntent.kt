package assignment.satu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assignment.satu.databinding.ActivityIntentBinding

class ActivityIntent : AppCompatActivity() {
    private lateinit var binding: ActivityIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_intent)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextActivity.setOnClickListener {
            val numValue = binding.etNum.text.toString().toIntOrNull() ?: 0
            val colValue = binding.etText.text.toString()

            val intent = Intent(this, ActivityReceiveIntent::class.java).apply {
                putExtra("EXTRA_NUMBER", numValue)
                putExtra("EXTRA_COLOR", colValue)
            }
            startActivity(intent)
            binding.etNum.text = null
            binding.etText.text = null
        }
    }
}