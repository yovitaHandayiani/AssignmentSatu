package assignment.satu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    // Declare the ActivityResultLauncher to handle the result
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*RECYCLER VIEW*/
        //setContentView(R.layout.penyajian_recycler_view)
        // getting the recyclerview by its id
        val recyclerview1 = findViewById<RecyclerView>(R.id.rvContainer1)
        val recyclerview2 = findViewById<RecyclerView>(R.id.rvContainer2)

        // this creates a vertical layout Manager
        recyclerview1.layoutManager = LinearLayoutManager(this)
        recyclerview2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // ArrayList of class ItemsViewModel
        val data1 = mutableListOf<PenyajianData>()
        val data2 = mutableListOf<ProductSerupaData>()

        // This loop will create 20 Views containing
        // the image with the count of view

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

        // This will pass the ArrayList to our Adapter
        lateinit var adapter1: PenyajianAdapter
        adapter1 = PenyajianAdapter(data1) { selectedPenyajian ->
            data1.forEach { it.bool = false }
            selectedPenyajian.bool = true
            adapter1.notifyDataSetChanged()
        }
        // Setting the Adapter with the recyclerview
        recyclerview1.adapter = adapter1

        lateinit var adapter2: ProductSerupaAdapter
        adapter2 = ProductSerupaAdapter(data2)
        recyclerview2.adapter = adapter2

        //INTENT
        val toolbar = findViewById<MaterialToolbar>(R.id.mdTopAppBar)
        val title = findViewById<TextView>(R.id.tvMenuTitle)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    // Extract data from the returned Intent
                    val data = result.data
                    val num = data?.getIntExtra("EXTRA_NUMBER", 0)
                    val color = data?.getStringExtra("EXTRA_COLOR") ?: "No color"
                    val title = findViewById<TextView>(R.id.tvMenuTitle)
                    title.text = "Data: $num & $color"
                }
            }

        toolbar.setNavigationOnClickListener {
            //Handle navigation icon press
            val intent = Intent(this, ActivityEmpty::class.java)

            // No need to call getStringExtra or getIntExtra here,
            // because we are passing data back from ActivityEmpty when it finishes.
            resultLauncher.launch(intent)  // Launch ActivityEmpty and expect a result
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    // Handle favorite icon press
                    val intent = intent.(intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExaWE3OXY1Nmh0eGNpeHNlMmp5ejRzaWtnODMxcWdtMzMydnYzMHNwaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/GJACwlJbtiWrYeWoqM/giphy.gif")
                    startActivity(intent)
                    true
                }
                R.id.share -> {
                    // Handle search icon press
                    true
                }
                else -> false
            }
        }

    }
}
