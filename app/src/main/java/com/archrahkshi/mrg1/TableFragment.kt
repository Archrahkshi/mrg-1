package com.archrahkshi.mrg1

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_table.*

const val NUMBER = "number"
const val COLOR = "color"
const val LAST_NUMBER = "last_number"
const val BASE_MAX_NUMBER = 100
const val SPAN_COUNT_VERTICAL = 3
const val SPAN_COUNT_HORIZONTAL = 4

class TableFragment : Fragment() {

    private val numbers = (1..BASE_MAX_NUMBER).toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_table, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null)
            numbers.addAll((BASE_MAX_NUMBER + 1)..savedInstanceState.getInt(LAST_NUMBER))

        updateTable(scrollDown = false)

        buttonAdd.setOnClickListener {
            numbers.apply { add(last() + 1) }
            updateTable(scrollDown = true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(LAST_NUMBER, numbers.last())
    }

    private fun updateTable(scrollDown: Boolean) {
        recyclerView.apply {
            adapter = NumberAdapter(numbers) {
                fragmentManager?.beginTransaction()?.replace(
                    R.id.frameLayoutMain,
                    BigNumberFragment().apply {
                        arguments = Bundle().apply {
                            putInt(NUMBER, it)
                            putInt(COLOR, if (it % 2 == 1) Color.BLUE else Color.RED)
                        }
                    }
                )?.addToBackStack(null)?.commit()
            }
            layoutManager = GridLayoutManager(
                context,
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                    SPAN_COUNT_VERTICAL
                else
                    SPAN_COUNT_HORIZONTAL,
                RecyclerView.VERTICAL,
                false
            ).apply {
                if (scrollDown) scrollToPosition(numbers.size - 1)
            }
        }
    }
}
