package com.example.eventdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventdemo.R
import com.example.eventdemo.data.model.EventDemoResponseModel
import com.example.eventdemo.data.model.Featured
import kotlinx.android.synthetic.main.fragment_demo.*

class DemoFragment : Fragment() {

    var mLayoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()

    }

    private fun setUp() {
        val bundle=arguments
        val eventDemoResponseModel: EventDemoResponseModel? = bundle?.getParcelable("model")
        val type=bundle?.getString("type")
        var mItems= ArrayList<Featured>()
        if (!type.isNullOrEmpty())
        {
            eventDemoResponseModel?.lists?.master_jobject?.forEach { if (it.groupId.name.equals(type)) mItems.add(it) }
        }
        mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager?.setOrientation(RecyclerView.VERTICAL)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.setItemAnimator(DefaultItemAnimator())
        mRecyclerView.adapter=EventAdapter(mItems)
    }

    companion object{
        fun newInstance(eventDemoResponseModel: EventDemoResponseModel):Fragment=DemoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("model", eventDemoResponseModel)
                }
            }

    }
}