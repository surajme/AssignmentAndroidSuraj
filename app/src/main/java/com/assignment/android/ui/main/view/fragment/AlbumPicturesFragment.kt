package com.assignment.android.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.assignment.android.ui.main.adapter.AlbumsPicturesAdapter
import com.assignment.android.data.api.ApiHelperImpl
import com.assignment.android.data.api.RetrofitBuilder
import com.assignment.android.data.model.Images
import com.assignment.android.data.model.AlbumsItem
import com.assignment.android.databinding.FragmentAlbumPicturesBinding
import com.assignment.android.ui.viewmodel.AlbumsViewModel
import com.assignment.android.ui.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_album_pictures.*

class AlbumPicturesFragment(private val albumsItem: AlbumsItem) : Fragment() {

    private lateinit var binding: FragmentAlbumPicturesBinding
    private lateinit var adapter: AlbumsPicturesAdapter
    private val albumViewModel: AlbumsViewModel by viewModels(
        ownerProducer = { requireActivity() },
        factoryProducer = {
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumPicturesBinding.inflate(
            inflater, container, false
        ).apply {
            this.viewModel = albumViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObservers()
    }

    private fun setUpObservers() {
        albumViewModel.fetchAlbumPictures( albumsItem.id)
        albumViewModel.albumsPictures.observe(
            viewLifecycleOwner, { albumPictures ->
                albumPictures?.let {
                    setUpAdapter(albumPictures)
                }
            }
        )
    }

    private fun setUpAdapter(images: Images) {
            rvPictures.recycledViewPool.clear()
            adapter = AlbumsPicturesAdapter()
            binding.rvPictures.adapter = adapter
    }
}
