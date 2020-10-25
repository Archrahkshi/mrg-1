package com.archrahkshi.mrg_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter(
    private val data: List<Int>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<NumberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NumberViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.number_cell, parent, false)
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }
}