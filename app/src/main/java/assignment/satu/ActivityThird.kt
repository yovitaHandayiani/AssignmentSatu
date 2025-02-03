package assignment.satu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityThird : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<Button>(R.id.btnBack)
        val num = findViewById<TextView>(R.id.tvNum3)
        val col = findViewById<TextView>(R.id.tvCol3)
        val numValue = intent.getIntExtra("EXTRA_NUMBER", 0)
        val colorValue = intent.getStringExtra("EXTRA_COLOR")
        num.text = numValue.toString()
        col.text = colorValue

        btnBack.setOnClickListener {

            finish()
        }
    }
}