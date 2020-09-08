package com.example.eventdemo.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventdemo.R
import com.example.eventdemo.data.model.Featured
import com.example.eventdemo.ui.EventDemoListAdapter.EventDemoListViewHolder
import com.example.eventdemo.util.Utility
import kotlinx.android.synthetic.main.row_item.view.*
import kotlin.reflect.KFunction1

class EventDemoListAdapter internal constructor(
    val mContext: Context, var mItems: MutableList<Featured>, val mOnClickListener: KFunction1<View?, Unit>
) : RecyclerView.Adapter<EventDemoListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventDemoListViewHolder {
        val rowItemBinding: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return EventDemoListViewHolder(rowItemBinding)
    }

    override fun onBindViewHolder(
        viewHolder: EventDemoListViewHolder,
        position: Int
    ) {
        val item = mItems[position]
        viewHolder.bind(item, viewHolder)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun setItems(list: List<Featured>?) {
        mItems.clear()
        mItems.addAll(list!!)
        notifyDataSetChanged()
    }

    inner class EventDemoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(Featured: Featured, viewHolder: EventDemoListViewHolder) {
            Utility.printLog(TAG, Featured.toString())
        }
    }
    companion object {
        private const val TAG = "EventDemoListAdapte"
    }

}