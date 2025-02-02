package assignment.satu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import assignment.satu.databinding.PenyajianBinding

class PenyajianAdapter(
    var listPenyajian: List<PenyajianData>, var selectedPenyajian: (PenyajianData) -> Unit
) : RecyclerView.Adapter<PenyajianAdapter.ViewHolder>() {
    // Holds the views for adding it to image and text
    class ViewHolder(val binding: PenyajianBinding) : RecyclerView.ViewHolder(binding.root)

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        return ViewHolder(
            PenyajianBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currSelectedData = listPenyajian[position]

        holder.binding.rbDescPenyajian.text = currSelectedData.desc
        holder.binding.rbPenyajian.text = currSelectedData.price
        holder.binding.rbPenyajian.isChecked = currSelectedData.bool

        // Handle RadioButton click
        holder.binding.rbPenyajian.setOnClickListener {
            currSelectedData.bool = true
            selectedPenyajian(currSelectedData)
            notifyDataSetChanged()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return listPenyajian.size
    }
}