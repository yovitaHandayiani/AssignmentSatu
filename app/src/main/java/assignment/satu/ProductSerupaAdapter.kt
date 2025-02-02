package assignment.satu

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip

class ProductSerupaAdapter(var listProductSerupa: List<ProductSerupaData>): RecyclerView.Adapter<ProductSerupaAdapter.ViewHolder>(){
    class ViewHolder(namaView: View):RecyclerView.ViewHolder(namaView){
        val imageURL: ImageView = namaView.findViewById(R.id.ivcPizza)
        val title: TextView = namaView.findViewById(R.id.tvcTitle)
        val priceNormal: TextView = namaView.findViewById(R.id.tvcPriceNormal)
        val priceDisc: TextView = namaView.findViewById(R.id.tvcPriceDisc)
        val chip: Chip = namaView.findViewById(R.id.mdcChip)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductSerupaAdapter.ViewHolder {
        val tampilan = LayoutInflater.from(parent.context)
            .inflate(R.layout.produk_serupa, parent, false)
        return ViewHolder(tampilan)
    }

    override fun onBindViewHolder(
        holder: ProductSerupaAdapter.ViewHolder,
        position: Int
    ) {
        val currData = listProductSerupa[position]
        holder.imageURL.setImageResource(currData.image)
        holder.title.text = currData.title
        holder.priceNormal.text = currData.price
        holder.chip.isVisible = false
        holder.priceDisc.isVisible = false
        if(currData.discBool == true){
            holder.chip.isVisible = true
            holder.priceDisc.isVisible = true
            holder.priceDisc.text = currData.discPrice
            holder.priceDisc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount(
    ): Int {
       return listProductSerupa.size
    }

}