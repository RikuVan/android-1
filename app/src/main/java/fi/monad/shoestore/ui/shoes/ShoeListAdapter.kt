package fi.monad.shoestore.ui.shoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fi.monad.shoestore.R
import fi.monad.shoestore.data.model.Shoe

// resource: https://github.com/android/views-widgets-samples/blob/main/RecyclerViewKotlin/app/src/main/java/com/example/recyclersample/flowerList/FlowersAdapter.kt

class ShoeListAdapter(private val onClick: (Shoe) -> Unit):  ListAdapter<Shoe, ShoeListAdapter.ShoesViewHolder>(ShoeDiffCallback) {

    class ShoesViewHolder(itemView: View, val onClick: (Shoe) -> Unit):  RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById((R.id.shoe_name_text))
        private val imgView: ImageView = itemView.findViewById((R.id.shoe_image))
        private val sizeView: TextView = itemView.findViewById((R.id.shoe_size))
        private val companyView: TextView = itemView.findViewById((R.id.company))
        private var currentShoes: Shoe? = null


        init {
            // navigate to detail view with selected shoe
            itemView.setOnClickListener {
                currentShoes?.let {
                    onClick(it)
                }
            }
        }

        fun bind(shoe: Shoe) {
            currentShoes = shoe
            nameView.text = shoe.name
            sizeView.text = "Size ${shoe.size}"
            companyView.text = shoe.company
            imgView.setImageResource(R.drawable.shoes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_shoe_item, parent, false)
        return ShoesViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.bind(shoe)
    }

}

object ShoeDiffCallback : DiffUtil.ItemCallback<Shoe>() {
    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id
    }
}