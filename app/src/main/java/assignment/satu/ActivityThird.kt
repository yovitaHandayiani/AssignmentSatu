package assignment.satu

import User
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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

        // PRIMITIVE DATA
        val btnBack = findViewById<Button>(R.id.btnBack)
        val num = findViewById<TextView>(R.id.tvNum3)
        val col = findViewById<TextView>(R.id.tvCol3)
        val numValue = intent.getIntExtra("EXTRA_NUMBER", 0)
        val colorValue = intent.getStringExtra("EXTRA_COLOR")
        num.text = numValue.toString()
        col.text = colorValue

        // In PARCELABLE OBJECT
        val user = intent.getParcelableExtra<User>("user_data")
        if (user != null) {
            // Use the received Parcelable object
            Log.d("User Info", "Name: ${user.name}, Age: ${user.age}")
        }

        //IMAGE FROM GALLERY
        val selectedImageView: ImageView = findViewById(R.id.myImageView)
        // Get the image URI passed from MainActivity
        val imageUri: Uri? = intent.data
        // Display the selected image using setImageURI
        selectedImageView.setImageURI(imageUri)

        btnBack.setOnClickListener {
            finish()
        }
    }
}