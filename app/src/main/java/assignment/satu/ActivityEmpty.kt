package assignment.satu

import User
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityEmpty : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

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

        val parcelableOBJ = findViewById<Button>(R.id.parcelableOBJ)
        parcelableOBJ.setOnClickListener {
            val user = User("Alice", 30)
            val intent = Intent(this, ActivityThird::class.java)
            intent.putExtra("user_data", user)
            startActivity(intent)

        }

        val imageFromGallery = findViewById<Button>(R.id.imageFromGallery)
        val pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    // Get the selected image URI from the result data
                    val selectedImageUri: Uri? = result.data?.data

                    // Pass the image URI to the second activity
                    val intent = Intent(this, ActivityThird::class.java).apply {
                        data = selectedImageUri
                    }
                    startActivity(intent)
                }
            }
        imageFromGallery.setOnClickListener {
            val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageIntent.type = "image/*"  // Only allow images

            // Launch the image picker activity using the result launcher
            pickImageLauncher.launch(pickImageIntent)

        }


        val imageFromGalleryMultiple = findViewById<Button>(R.id.imageFromGalleryMultiple)
        // Registering the activity result callback for picking multiple images
        val pickMultipleImagesLauncher =
            registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
                if (uris != null && uris.isNotEmpty()) {
                    // Loop through the URIs and display images
                    uris.forEachIndexed { index, uri ->
                        // Display the image in an ImageView (you can create multiple ImageViews dynamically or use a RecyclerView)
                        // Here we are using the first ImageView for demonstration purposes.
                        val imageView =
                            findViewById<ImageView>(R.id.capturedImageView) // ImageView for displaying the first image
                        imageView.setImageURI(uri)
                        // You can save the URIs in a list to display multiple images or perform any other task
                    }
                } else {
                    Toast.makeText(this, "No images selected", Toast.LENGTH_SHORT).show()
                }
            }
        // Button to trigger image picker
        imageFromGalleryMultiple.setOnClickListener {
            // Launch the image picker activity with MIME type "image/*" to allow multiple image selection
            pickMultipleImagesLauncher.launch("image/*")
        }
        //to show that multiple image, use recycler view



    }
}
