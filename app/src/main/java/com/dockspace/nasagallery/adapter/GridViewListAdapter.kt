package com.dockspace.nasagallery.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dockspace.nasagallery.R
import com.dockspace.nasagallery.databinding.ImageViewItemBinding
import com.dockspace.nasagallery.model.DataModel
import com.dockspace.nasagallery.model.DataModelItem

class GridViewListAdapter(
    private var dataModel: DataModel,
    private val imageClickInterface: ImageClickInterface
) : RecyclerView.Adapter<GridViewListAdapter.ImageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val binding = ImageViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ImageListViewHolder(binding)

        viewHolder.itemView.setOnClickListener {
            imageClickInterface.onImageClicked(dataModel[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ImageListViewHolder, position: Int) {

        viewHolder.setBindings(dataModel[position])
    }

    override fun getItemCount(): Int {
        return if (dataModel.isNotEmpty()) dataModel.size else 0
    }

    fun addImages(dataModel: DataModel){
        this.dataModel = dataModel
    }

    class ImageListViewHolder(private var binding: ImageViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setBindings(dataModelItem: DataModelItem){
            val options = RequestOptions()
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_image)
            Glide.with(binding.imgViewTemp.context)
                .load(dataModelItem.url)
                .apply(options)
                .into(binding.imgViewTemp)
        }
    }

    interface ImageClickInterface{
        fun onImageClicked(dataModelItem: DataModelItem, position: Int)
    }

}