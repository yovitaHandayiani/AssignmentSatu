package assignment.satu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
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
        val recyclerview = findViewById<RecyclerView>(R.id.rvContainer)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = mutableListOf<PenyajianData>()

        // This loop will create 20 Views containing
        // the image with the count of view

        data.add(PenyajianData("SERVICE CHARGE DINE IN", "2.000+", false))
        data.add(PenyajianData("Kirim Beku (Panaskan Sendiri)", "", false))


        // This will pass the ArrayList to our Adapter
        lateinit var adapter: PenyajianAdapter
        adapter = PenyajianAdapter(data) { selectedPenyajian ->
            data.forEach { it.bool = false }
            selectedPenyajian.bool = true
            adapter.notifyDataSetChanged()
        }

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}