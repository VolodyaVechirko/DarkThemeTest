package com.vvechirko.darkthemetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class Adapter(var data: List<Data> = emptyList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1 else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 1) Header(parent.inflate(R.layout.item_header))
        else Holder(parent.inflate(R.layout.item_analytics))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.bind(data[position - 1])
        }
    }

    override fun getItemCount(): Int = data.size + 1
}

class Header(view: View) : RecyclerView.ViewHolder(view) {
    init {
        (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
    }
}

class Holder(view: View) : RecyclerView.ViewHolder(view) {

    private val title = view.findViewById<TextView>(R.id.textView1)
    private val data1 = view.findViewById<TextView>(R.id.textView2)
    private val data2 = view.findViewById<TextView>(R.id.textView3)
    private val layout = view.findViewById<LinearLayout>(R.id.layout)

    fun bind(it: Data) {
        title.text = it.title
        data1.text = it.data1
        data2.text = it.data2

        data2.isVisible = it.data2 != null

        if (layout.childCount == 4) {
            layout.removeView(layout.getChildAt(3))
        }

        when (it.chart) {
            "chart1" -> layout.addView(layout.inflate(R.layout.chart1))
            "chart2" -> layout.addView(layout.inflate(R.layout.chart2))
        }
    }
}

fun ViewGroup.inflate(resId: Int) = LayoutInflater.from(context)
    .inflate(resId, this, false)