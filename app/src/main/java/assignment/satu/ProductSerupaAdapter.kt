package assignment.satu

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import assignment.satu.databinding.ProdukSerupaBinding

class ProductSerupaAdapter(var listProductSerupa: List<ProductSerupaData>) :
    RecyclerView.Adapter<ProductSerupaAdapter.ViewHolder>() {
    class ViewHolder(val binding: ProdukSerupaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ProdukSerupaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currData = listProductSerupa[position]

        // Access views using the binding object
        holder.binding.ivcPizza.setImageResource(currData.image)
        holder.binding.tvcTitle.text = currData.title
        holder.binding.tvcPriceNormal.text = currData.price
        holder.binding.mdcChip.isVisible = false
        holder.binding.tvcPriceDisc.isVisible = false

        // Check for discount and show price details if applicable
        if (currData.discBool) {
            holder.binding.mdcChip.isVisible = true
            holder.binding.tvcPriceDisc.isVisible = true
            holder.binding.tvcPriceDisc.text = currData.discPrice
            holder.binding.tvcPriceDisc.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount(
    ): Int {
        return listProductSerupa.size
    }

}