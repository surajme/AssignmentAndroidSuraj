package com.assignment.android.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.assignment.android.data.model.Albums
import com.assignment.android.ui.main.view.fragment.AlbumPicturesFragment


class AlbumsAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private var noOfItems: Int = 0
    private lateinit var albums: Albums
    fun setItemsList(albums: Albums) {
        this.albums = albums
        noOfItems = albums.size
    }
    override fun getItemCount(): Int {
        return noOfItems
    }

    override fun createFragment(position: Int): Fragment {
        return AlbumPicturesFragment(albums[position])
    }
}