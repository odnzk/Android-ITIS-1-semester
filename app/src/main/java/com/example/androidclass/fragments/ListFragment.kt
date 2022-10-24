package com.example.androidclass.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.androidclass.MainActivity
import com.example.androidclass.R
import com.example.androidclass.data.MusicRepository
import com.example.androidclass.databinding.FragmentListBinding
import com.example.androidclass.dialog.AddMusicInfoDialog
import com.example.androidclass.rv.MusicInfoListAdapter
import com.example.androidclass.rv.SimpleVerticalDividerItemDecorator


class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    val adapter = MusicInfoListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        setUpAdapter()

        with(binding) {
            rv.addItemDecoration(SimpleVerticalDividerItemDecorator(4))
            rv.adapter = adapter
        }
    }

    private fun setUpAdapter() {
        adapter.submitList(MusicRepository.loadData().toMutableList())
        adapter.glide = Glide.with(this)
        adapter.onArtistListener = ::onArtistClickListener
        adapter.onPlaylistImageListener = ::onPlaylistImageListener
    }

    private fun onPlaylistImageListener(uuid: String) {
        adapter.submitList(MusicRepository.changePlaylistImage(uuid).toMutableList())
    }

    private fun onArtistClickListener() {
        AddMusicInfoDialog().show(
            requireActivity().supportFragmentManager,
            MainActivity.ADD_ITEM_DIALOG_TAG
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
