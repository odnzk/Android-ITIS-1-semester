package com.example.androidclass.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.androidclass.R
import com.example.androidclass.data.MusicRepository
import com.example.androidclass.databinding.FragmentListBinding
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
        adapter.onItemClickListener = ::navigateToStaggered
//        adapter.onArtistListener = ::onArtistClickListener
//        adapter.onPlaylistImageListener = ::onPlaylistImageListener
    }

    private fun navigateToStaggered(position: Int) {
        findNavController().navigate(
            R.id.action_listFragment_to_staggeredFragment,
            bundleOf(StaggeredFragment.KEY_COUNT_STAGGERED_ELEMENTS to position)
        )
    }

//    private fun onPlaylistImageListener(uuid: String) {
//        adapter.submitList(MusicRepository.changePlaylistImage(uuid).toMutableList())
//    }
//
//    private fun onArtistClickListener() {
//        AddMusicInfoDialog().show(
//            requireActivity().supportFragmentManager,
//            MainActivity.ADD_ITEM_DIALOG_TAG
//        )
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
