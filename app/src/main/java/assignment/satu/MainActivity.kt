package assignment.satu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import assignment.satu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //List of class
    private val data1 = mutableListOf<PenyajianData>()
    private val data2 = mutableListOf<ProductSerupaData>()

    private lateinit var adapter1: PenyajianAdapter
    private lateinit var adapter2: ProductSerupaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this creates layout Manager
        binding.rvContainer1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvContainer2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        data1.add(PenyajianData("SERVICE CHARGE DINE IN", "2.000+", false))
        data1.add(PenyajianData("Kirim Beku (Panaskan Sendiri)", "", false))
        data2.add(
            ProductSerupaData(
                image = R.drawable.super_supreme_chicken_pan_pizza,
                title = "Super Supreme Chicken Pan Pizza",
                price = "Rp77.000",
                discPrice = "Rp67.760",
                discBool = true
            )
        )
        data2.add(
            ProductSerupaData(
                image = R.drawable.italian_meatballs_pizza_pan_pizza,
                title = "Italian Meatballs Pizza Pan Pizza",
                price = "Rp78.000",
                discPrice = "Rp68.640",
                discBool = true
            )
        )
        data2.add(
            ProductSerupaData(
                image = R.drawable.frankfurter_bbq_pan_pizza,
                title = "Frankfurter BBQ Pan Pizza",
                price = "Rp78.000",
                discPrice = "",
                discBool = false
            )
        )
        data2.add(
            ProductSerupaData(
                image = R.drawable.supreme,
                title = "Super Supreme Beef Pan Pizza",
                price = "Rp77.000",
                discPrice = "",
                discBool = false
            )
        )
        data2.add(
            ProductSerupaData(
                image = R.drawable.meat_lovers_pan_pizza,
                title = "Meat Lovers Pan Pizza",
                price = "Rp77.000",
                discPrice = "",
                discBool = false
            )
        )

        // This will pass the List to our Adapter
        adapter1 = PenyajianAdapter(data1) { selectedPenyajian ->
            data1.forEach { it.bool = false }
            selectedPenyajian.bool = true
            adapter1.notifyDataSetChanged()
        }
        adapter2 = ProductSerupaAdapter(data2){ selectedProduct ->

            val intent = Intent(this, ActivityProductDetail::class.java).apply {
                putExtra("EXTRA_TITLE", selectedProduct.title)
                putExtra("EXTRA_PRICE", selectedProduct.price)
                putExtra("EXTRA_IMAGE", selectedProduct.image)
                putExtra("EXTRA_DISC", selectedProduct.discPrice)
                putExtra("EXTRA_BOOL", selectedProduct.discBool)
            }
            startActivity(intent)
        }

        // Set the adapter to the RecyclerView using the ViewBinding
        binding.rvContainer1.adapter = adapter1
        binding.rvContainer2.adapter = adapter2

        binding.bTextIconButton.setOnClickListener {
            val intentDestination = Intent(this, ActivityFragment::class.java)
            startActivity(intentDestination)
        }

//        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
//            data = Uri.parse("mailto:example@example.com") // Replace with the email address
//            putExtra(Intent.EXTRA_SUBJECT, "Subject of the email") // Optional: Email subject
//            putExtra(Intent.EXTRA_TEXT, "Body of the email") // Optional: Email body
//        }

        binding.mdTopAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    // Handle favorite icon press
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExaWE3OXY1Nmh0eGNpeHNlMmp5ejRzaWtnODMxcWdtMzMydnYzMHNwaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/GJACwlJbtiWrYeWoqM/giphy.gif")
                    startActivity(intent)
                    true
                }
                R.id.share -> {
                    // Handle search icon press
                    val intent = intent
                    val uri = intent.data

                    // Handle the deep link if the URI matches the defined scheme and host
                    if (uri != null && uri.scheme == "myapp" && uri.host == "pizzaapp") {
                        // Optionally, you can extract more data from the URI, such as path segments or query parameters
                        Log.d("DeepLink", "App opened with deep link: $uri")
                    }

                    // Create a hardcoded deep link URL with query parameters
                    val deepLink = "myapp://pizzaapp/products?category=vegan&sort=price&filter=cheese"
                    val intentt = Intent(Intent.ACTION_VIEW, Uri.parse(deepLink))
                    val urii: Uri? = intentt.data
                    val category = urii?.getQueryParameter("category")  // Should return "vegan"
                    val sort = urii?.getQueryParameter("sort")          // Should return "price"
                    val filter = urii?.getQueryParameter("filter")
                    Log.d("DeepLink", "Category: $category")  // "vegan"
                    Log.d("DeepLink", "Sort: $sort")          // "price"
                    Log.d("DeepLink", "Filter: $filter")

                    //pop up share sheet
                    val sharelntent = Intent (Intent.ACTION_SEND)
                    sharelntent.type = "text/plain"
                    sharelntent.putExtra(Intent.EXTRA_TEXT, "Check this out! $uri")
                    startActivity (Intent.createChooser (sharelntent, "Share via"))
                    true
                }
                else -> false
            }
        }

        binding.bIconButton.setOnClickListener {
            val intent = Intent(this, ActivityIntent::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}