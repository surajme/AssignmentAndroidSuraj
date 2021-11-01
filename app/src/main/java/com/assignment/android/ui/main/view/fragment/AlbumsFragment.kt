package com.assignment.android.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.assignment.android.R
import com.assignment.android.ui.main.adapter.AlbumsAdapter
import com.assignment.android.data.api.ApiHelperImpl
import com.assignment.android.data.api.RetrofitBuilder
import com.assignment.android.data.model.Albums
import com.assignment.android.databinding.FragmentAlbumBinding
import com.assignment.android.ui.viewmodel.AlbumsViewModel
import com.assignment.android.ui.base.ViewModelFactory

class AlbumsFragment : Fragment(R.layout.fragment_album) {
    private lateinit var binding: FragmentAlbumBinding

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
    ): View? {
        binding = FragmentAlbumBinding.inflate(
            inflater, container, false
        ).apply {
            this.viewModel = albumViewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        albumViewModel.albumsList.observe(
            viewLifecycleOwner, { albums ->
                setupViewPager(albums)
            }
        )
    }

    private fun setupViewPager(albums: Albums) {
        binding.albumsViewPager.apply {
            adapter = AlbumsAdapter(requireActivity()).apply {
                notifyDataSetChanged()
                setItemsList(albums)
                clearCache()
            }
        }.also {
            TabLayoutMediator(binding.tabs, binding.albumsViewPager) { tabs, pos ->
                tabs.text = albums[pos].title
            }.attach()
        }
    }

    private fun ViewPager2.clearCache() {
        val layoutManager = (this.getChildAt(0) as RecyclerView).layoutManager
        if (layoutManager != null) {
            layoutManager.isItemPrefetchEnabled = false
        }
        val recyclerView: RecyclerView = this.getChildAt(0) as RecyclerView
        recyclerView.setItemViewCacheSize(0)
    }
}

