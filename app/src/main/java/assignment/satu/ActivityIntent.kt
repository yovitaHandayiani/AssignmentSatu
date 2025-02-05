package assignment.satu

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import assignment.satu.databinding.ActivityIntentBinding
import java.io.File
import android.Manifest
import android.content.ContentValues
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.widget.ImageView
import android.widget.LinearLayout

class ActivityIntent : AppCompatActivity() {
    private lateinit var binding: ActivityIntentBinding
    private val CAMERA_PERMISSION_REQUEST_CODE = 100

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

        // PRIMITIVE DATA
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

        // PARCELABLE OBJECT
        binding.btnPcbl.setOnClickListener {
            val user = User("Alice", 30)
            val intent = Intent(this, ActivityReceiveIntent::class.java)
            intent.putExtra("user_data", user)
            startActivity(intent)
        }

//        val intent = Intent(this, ActivityReceiveIntent::class.java)
//        intent.putParcelableArrayExtra("user_data_array", userArray) // For Parcelable
//        startActivity(intent)

        //Get 1 Image
            // Register the image picker using ActivityResultContracts.GetContent()
            val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    // Display the image in the ImageView
                    binding.imageView.setImageURI(it)
                    Log.d("ImagePicker", "Selected Image URI: $it")

                    // Now pass the image URI to the next activity
                    val intent = Intent(this, ActivityReceiveIntent::class.java).apply {
                        data = it // Set the image URI as data
                    }
                    startActivity(intent) // Start ActivityThird to display or process the image
                }
            }

            // Set up the button click listener to launch the image picker
            binding.btnPickImage.setOnClickListener {
                pickImageLauncher.launch("image/*") // Trigger the image picker with image filter
            }

        // Capture camera
            // Check and request CAMERA permission if not granted
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
            }

            // Register the activity result for taking a picture preview (camera)
            val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
                bitmap?.let {
                    binding.ivCC.setImageBitmap(it)
                    Log.d("Camera", "Captured Image")
                }
            }

            // Handle button click to launch the camera
            binding.btnCaptureCamera.setOnClickListener {
                // Trigger the camera to take a photo
                takePictureLauncher.launch()
            }

        //Capture&Save
            lateinit var imageUri: Uri

            val takePictureLauncher2 =
                registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                    if (success) {
                        binding.ivCCS.setImageURI(imageUri)  // Set the captured image to ImageView
                        Log.d("Camera", "Image saved at: $imageUri")
                    }
                }

            // Set up the button to capture and save the photo
            binding.btnCaptureCameraAndSave.setOnClickListener {
                // Prepare the URI to save the image
                imageUri = FileProvider.getUriForFile(
                    this,
                    "assignment.satu.fileprovider",  // Correct authority (replace with your actual package name)
                    File(filesDir, "photo.jpg")   // Store image in app's internal storage
                )

//                // save to gallery
//                val contentValues = ContentValues().apply {
//                    put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg") // Image name
//                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // Image type
//                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // Store in "Pictures" directory
//                }
//                // Insert the image into the MediaStore, which returns a content URI
//                imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!

                // Launch the camera to capture the image
                takePictureLauncher2.launch(imageUri)
            }

        //Pick a FILE
            // Register for file picker result
            val pickFileLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
                uri?.let {
                    // Log the URI of the selected file
                    Log.d("FilePicker", "Selected File URI: $it")
                    // You can do further actions with the selected file URI here, such as displaying the file name or opening it.
                }
            }

            // Set up the button to launch the file picker
            binding.btnPickAFile.setOnClickListener {
                // Launch the file picker with filters for PDF and plain text files
                pickFileLauncher.launch(arrayOf("application/pdf", "text/plain"))
            }

        //Request Permissions at Runtime
            val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    Log.d("Permissions", "Camera permission granted")
                } else {
                    Log.d("Permissions", "Camera permission denied")
                }
            }

            binding.btnReqPermRun.setOnClickListener {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }

        //Request Multiple Permissions
            val requestMultiplePermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
                val locationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false

                Log.d("Permissions", "Camera: $cameraGranted, Location: $locationGranted")
            }

    //        binding.btnMultipleReqPermRun.setOnClickListener {
    //            requestMultiplePermissionsLauncher.launch(
    //                arrayOf(
    //                    Manifest.permission.CAMERA,
    //                    Manifest.permission.ACCESS_FINE_LOCATION
    //                )
    //            )
    //        }

            binding.btnMultipleReqPermRun.setOnClickListener {
                val cameraPermissionGranted = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

                val locationPermissionGranted = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

                // If the permissions are not granted, request them
                if (!cameraPermissionGranted || !locationPermissionGranted) {
                    // Check if the user has denied permissions but not selected "Don't ask again"
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ||
                        shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                        // Show a rationale if necessary, then request the permissions
                        Log.d("Permissions", "Requesting permissions again")
                        requestMultiplePermissionsLauncher.launch(
                            arrayOf(
                                Manifest.permission.CAMERA,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        )
                    } else {
                        // Permissions were denied with "Don't ask again", you can open the settings
                        Log.d("Permissions", "Permissions permanently denied, show settings prompt")
                        // Optionally show a message explaining that the user needs to manually enable the permissions
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                } else {
                    Log.d("Permissions", "Permissions already granted")
                }
            }

        //Pick multiple Image
//            // Register the multiple image picker launcher
//            val pickMultipleImagesLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
//                uris.forEach { uri ->
//                    Log.d("ImagePicker", "Selected Image URI: $uri")
//                    // Optionally, you can handle the URIs here, like displaying the first image in an ImageView
//                    // For example, setting the first selected image to an ImageView:
//                    if (uris.isNotEmpty()) {
//                        binding.imageViewId.setImageURI(uris[0]) // Set the first selected image to the ImageView
//                    }
//                }
//            }
//
//            // Set the click listener for the "Pick Multiple Images" button
//            binding.btnMultImage.setOnClickListener {
//                pickMultipleImagesLauncher.launch("image/*") // Allow selecting multiple images
//            }

        // Register the multiple image picker launcher
        val pickMultipleImagesLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            uris.forEach { uri ->
                Log.d("ImagePicker", "Selected Image URI: $uri")
                // Add each selected image as an ImageView in the layout
                val imageView = ImageView(this)
                imageView.setImageURI(uri)  // Set the image URI to the ImageView
//                imageView.layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//                )
                imageView.layoutParams = LinearLayout.LayoutParams(100, 100)
                // Add the ImageView to the layout (LinearLayout or GridLayout)
                binding.llSelectedImages.addView(imageView)
            }
        }

        // Set the click listener for the "Pick Multiple Images" button
        binding.btnMultImage.setOnClickListener {
            pickMultipleImagesLauncher.launch("image/*") // Allow selecting multiple images
        }
    }
}