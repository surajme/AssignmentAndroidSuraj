package com.assignment.android.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.android.data.model.AlbumPictures
import com.assignment.android.data.model.AlbumPicturesItem
import com.assignment.android.databinding.AlbumPhotoItemBinding

class AlbumsPicturesAdapter() :
    RecyclerView.Adapter<AlbumsPicturesAdapter.PicturesViewHolder>(),
    BindableAdapter<AlbumPictures> {

    private lateinit var albumPictures: AlbumPictures

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        return PicturesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        holder.bind(albumPictures[position])
    }

    override fun getItemCount(): Int {
        return if (::albumPictures.isInitialized) {
            albumPictures.size
        } else {
            0
        }
    }

    override fun setData(data: AlbumPictures) {
        albumPictures = data
        notifyDataSetChanged()
    }

    class PicturesViewHolder private constructor(private val binding: AlbumPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlbumPicturesItem) {
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