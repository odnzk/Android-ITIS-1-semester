package com.example.androidclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidclass.databinding.ActivityMainBinding
import com.example.androidclass.fragments.FirstFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().run {
            add(R.id.fragments_container, FirstFragment.getInstance())
            setReorderingAllowed(true)
            commit()
        }
    }
}
