package si.damjanh.androidhilt.ui.plants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import si.damjanh.androidhilt.R
import si.damjanh.androidhilt.data.model.Plant

class PlantAdapter internal constructor(context: Context) :
    ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = inflater.inflate(R.layout.list_item_plant, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        (holder as PlantViewHolder).apply {
            plantTitle.text = current.name
            Glide.with(plantImage.context)
                .load(current.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(plantImage)
        }
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantTitle: TextView = itemView.findViewById(R.id.plant_item_title)
        val plantImage: ImageView = itemView.findViewById(R.id.plant_item_image)
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}