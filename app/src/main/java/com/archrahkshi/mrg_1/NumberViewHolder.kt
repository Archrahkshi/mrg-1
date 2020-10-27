package com.archrahkshi.mrg_1

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textViewNumber: TextView = view.findViewById(R.id.textViewNumber)

    fun bind(number: Int, clickListener: (Int) -> Unit) {
        textViewNumber.apply {
            text = number.toString()
            setTextColor(if (number % 2 == 1) Color.BLUE else Color.RED)
            setOnClickListener { clickListener(number) }
        }
    }
}
