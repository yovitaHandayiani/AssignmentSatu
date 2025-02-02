package assignment.satu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import assignment.satu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this creates a vertical layout Manager
        binding.rvContainer1.layoutManager = LinearLayoutManager(this)
        binding.rvContainer2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //List of class
        val data1 = mutableListOf<PenyajianData>()
        val data2 = mutableListOf<ProductSerupaData>()

        data1.add(PenyajianData("SERVICE CHARGE DINE IN", "2.000+", false))
        data1.add(PenyajianData("Kirim Beku (Panaskan Sendiri)", "", false))

        data2.add(
            ProductSerupaData(
                image = R.drawable.super_supreme_chicken_pan_pizza,
                title = "Super Supreme Chicken Pan Pizza",
                price = "Rp77.000",
                discPrice = "Rp67.760",
                discBool = true
            ))
        data2.add(
            ProductSerupaData(
                image = R.drawable.italian_meatballs_pizza_pan_pizza,
                title = "Italian Meatballs Pizza Pan Pizza",
                price = "Rp78.000",
                discPrice = "Rp68.640",
                discBool = true
            ))
        data2.add(
            ProductSerupaData(
                image = R.drawable.frankfurter_bbq_pan_pizza,
                title = "Frankfurter BBQ Pan Pizza",
                price = "Rp78.000",
                discPrice = "",
                discBool = false
            ))
        data2.add(
            ProductSerupaData(
                image = R.drawable.supreme,
                title = "Super Supreme Beef Pan Pizza",
                price = "Rp77.000",
                discPrice = "",
                discBool = false
            ))
        data2.add(
            ProductSerupaData(
                image = R.drawable.meat_lovers_pan_pizza,
                title = "Meat Lovers Pan Pizza",
                price = "Rp77.000",
                discPrice = "",
                discBool = false
            ))

        // This will pass the ArrayList to our Adapter
        lateinit var adapter1: PenyajianAdapter
        adapter1 = PenyajianAdapter(data1) { selectedPenyajian ->
            data1.forEach { it.bool = false }
            selectedPenyajian.bool = true
            adapter1.notifyDataSetChanged()
        }

        // Set the adapter to the RecyclerView using the ViewBinding
        binding.rvContainer1.adapter = adapter1

        lateinit var adapter2: ProductSerupaAdapter
        adapter2 = ProductSerupaAdapter(data2)
        binding.rvContainer2.adapter = adapter2
    }
}