package com.example.androidclass.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private var data: MutableList<MyModel> = mutableListOf()
    var myInterface: MyInterface? = null

    fun setData(newData: List<MyModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent, myInterface)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}
