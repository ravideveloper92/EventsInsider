package com.example.eventdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventdemo.R
import com.example.eventdemo.data.model.EventDemoResponseModel
import com.example.eventdemo.data.model.Featured
import kotlinx.android.synthetic.main.fragment_list.view.*

class EventAdapter internal constructor(var mItems: ArrayList<Featured>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        val rowItemBinding: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return EventViewHolder(rowItemBinding)
    }

    override fun onBindViewHolder(
        viewHolder: EventViewHolder,
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

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(featured: Featured, viewHolder: EventViewHolder) {
            if (featured.horizontalCoverImage != null) {
                Glide.with(itemView.context)
                    .load(featured.horizontalCoverImage)
                    .into(viewHolder.itemView.thumbnail)
            }
            viewHolder.itemView.txt_category_name.setText(featured.categoryId.name)
            viewHolder.itemView.name.setText(featured.name)
            viewHolder.itemView.venue_date_string.setText(featured.venueDateString)
           viewHolder.itemView.venue_name.setText(featured.venueName)
            viewHolder.itemView.min_price.setText(featured.minPrice.toString())

            //Utility.printLog(TAG, Featured.toString())
        }
    }
    companion object {
        private const val TAG = "EventDemoListAdapte"
    }

}