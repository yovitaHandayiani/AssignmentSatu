package assignment.satu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PenyajianAdapter (var listPenyajian: List<PenyajianData>, var selectedPenyajian: (PenyajianData) -> Unit): RecyclerView.Adapter<PenyajianAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.penyajian, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currSelectedData = listPenyajian[position]
        // sets the text to the textview from our itemHolder class
        holder.textView.text = currSelectedData.desc
        holder.rbText.text = currSelectedData.price
        holder.rbText.isChecked = currSelectedData.bool
        holder.rbText.setOnClickListener{
            currSelectedData.bool = true
            selectedPenyajian(currSelectedData)
            notifyDataSetChanged()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return listPenyajian.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.rbDescPenyajian)
        val rbText: RadioButton = itemView.findViewById(R.id.rbPenyajian)
    }
}