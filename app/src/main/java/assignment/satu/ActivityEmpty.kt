package assignment.satu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityEmpty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val number = findViewById<EditText>(R.id.etNum)
        val color = findViewById<EditText>(R.id.etCol)

        val btn = findViewById<Button>(R.id.btnBack)
        btn.setOnClickListener{
            val numValue = number.text.toString().toInt()
            val colValue = color.text.toString()
            intent.putExtra("EXTRA_NUMBER", numValue)
            intent.putExtra("EXTRA_COLOR", colValue)
            setResult(RESULT_OK, intent)
            finish()
        }

        val btn2 = findViewById<Button>(R.id.btn3)
        btn2.setOnClickListener {
            val numValue = number.text.toString().toInt()
            val colValue = color.text.toString()

            val intent = Intent(this, ActivityThird::class.java).also {
                it.putExtra("EXTRA_NUMBER", numValue)
                it.putExtra("EXTRA_COLOR", colValue)
            }
            startActivity(intent)
        }


    }
}