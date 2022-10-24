package com.example.androidclass.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.androidclass.MainActivity
import com.example.androidclass.R
import com.example.androidclass.data.MusicRepository
import com.example.androidclass.databinding.DialogAddMusicInfoBinding
import com.example.androidclass.fragments.ListFragment
import com.example.androidclass.generator.Generator
import com.example.androidclass.model.MusicInfo

class AddMusicInfoDialog : DialogFragment(R.layout.dialog_add_music_info) {
    private var _binding: DialogAddMusicInfoBinding? = null
    private val binding: DialogAddMusicInfoBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogAddMusicInfoBinding.bind(view)

        with(binding) {
            btnAdd.setOnClickListener {
                if (isAllValid()) {
                    val title = edTitle.text.toString()
                    val item: MusicInfo = when (rgType.checkedRadioButtonId) {
                        R.id.rb_playlist -> Generator.generateRandomItem(
                            title,
                            Generator.TYPE_PLAYLIST
                        )
                        R.id.rb_action -> Generator.generateRandomItem(title, Generator.TYPE_ACTION)
                        else -> Generator.generateRandomItem(title, Generator.TYPE_ARTIST)
                    }

                    notifyAdapter(item)

                    dialog?.hide()
                    Toast.makeText(context, getString(R.string.added), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun notifyAdapter(item: MusicInfo) {
        val listFragment =
            activity?.supportFragmentManager?.findFragmentByTag(MainActivity.FRAGMENT_LIST_TAG)
        listFragment?.let {
            if (listFragment is ListFragment) {
                listFragment.adapter.submitList(
                    MusicRepository.insertToRandomPosition(item).toMutableList()
                )
            }
        }
    }

    private fun isAllValid(): Boolean {
        with(binding) {
            val text: String = edTitle.text.toString()
            if (text.isEmpty() || text.isBlank()) {
                edTitle.error = getString(R.string.error_invalid_title)
                return false
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
