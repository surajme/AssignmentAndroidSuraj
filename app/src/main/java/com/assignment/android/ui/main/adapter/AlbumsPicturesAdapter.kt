package com.assignment.android.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.android.data.model.Images
import com.assignment.android.data.model.ImagesItems
import com.assignment.android.databinding.AlbumPhotoItemBinding

class AlbumsPicturesAdapter() :
    RecyclerView.Adapter<AlbumsPicturesAdapter.PicturesViewHolder>(),
    BindableAdapter<Images> {

    private lateinit var images: Images

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        return PicturesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return if (::images.isInitialized) {
            images.size
        } else {
            0
        }
    }

    override fun setData(data: Images) {
        images = data
        notifyDataSetChanged()
    }

    class PicturesViewHolder private constructor(private val binding: AlbumPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImagesItems) {
            binding.albumPictureItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PicturesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumPhotoItemBinding.inflate(layoutInflater, parent, false)

                return PicturesViewHolder(binding)
            }
        }
    }
}