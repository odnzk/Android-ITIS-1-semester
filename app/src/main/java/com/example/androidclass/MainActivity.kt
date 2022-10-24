package com.example.androidclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidclass.databinding.ActivityMainBinding
import com.example.androidclass.dialog.AddMusicInfoDialog
import com.example.androidclass.fragments.ListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            fabAdd.setOnClickListener {
                showDialog()
            }

            supportFragmentManager
                .beginTransaction()
                .add(fragmentContainer.id, ListFragment(), FRAGMENT_LIST_TAG)
                .commit()

        }
    }

    private fun showDialog() {
        AddMusicInfoDialog().show(supportFragmentManager, ADD_ITEM_DIALOG_TAG)
    }

    companion object {
        const val ADD_ITEM_DIALOG_TAG = "tag-dialog-add"
        const val FRAGMENT_LIST_TAG = "fragment-list"
    }

}
