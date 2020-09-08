package com.example.eventdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import com.example.eventdemo.R
import com.example.eventdemo.base.BaseActivityt
import com.example.eventdemo.data.model.EventDemoResponseModel
import com.example.eventdemo.data.model.Featured
import com.example.eventdemo.databinding.ActivityMainBinding
import com.example.eventdemo.util.StateLiveData
import com.example.eventdemo.util.Utility
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity2 : BaseActivityt<MainViewModel, ActivityMainBinding>(),View.OnClickListener {
    val TAG: String = com.example.eventdemo.ui.MainActivity2::class.java.simpleName
    var mAdapter: EventDemoListAdapter? = null
    lateinit var binding: ActivityMainBinding
    lateinit var myviewModel: MainViewModel


/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
*/

    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java


    override fun onCreate(
        instance: Bundle?,
        viewModel: MainViewModel,
        binding: ActivityMainBinding
    ) {
        Utility.printLog(TAG, "create")
        this.binding = binding
        this.myviewModel = viewModel
        binding.btnRetry.setOnClickListener(this)
        binding.loading = true
        callAPI()
    }

    private fun callAPI() {
        val pages = FragmentPagerItems(this)
        this.myviewModel.EventDemoListLiveData?.observe(this, Observer { listStateLiveData ->
            when (listStateLiveData.status) {
                StateLiveData.DataStatus.LOADING -> binding.loading = true
                StateLiveData.DataStatus.ERROR -> {
                    binding.loading = false
                    binding.loading = null
                    binding.txtNoResult.text = "No Internet"
                    binding.ivNoResult.setImageResource(android.R.drawable.dark_header)
                    binding.btnRetry.visibility = View.VISIBLE
                }
                StateLiveData.DataStatus.SUCCESS -> {
                    binding.loading = false
                    val  eventDemoResponseModel =listStateLiveData.data as EventDemoResponseModel
                    eventDemoResponseModel.groups.forEach{
                        pages.add(FragmentPagerItem.of(it, DemoFragment::class.java,Bundle().apply {
                            putString("type", it)
                            putParcelable("model", eventDemoResponseModel)
                        }
                        ))
                    }
                    val adapter = FragmentPagerItemAdapter(
                        supportFragmentManager, pages
                    )
                    viewpager.adapter = adapter
                    viewpagertab.setViewPager(viewpager)
                }
            }
        })
    }
    private fun setShadiMatchesAdapter(
        matchesList: List<Featured>?,
        binding: ActivityMainBinding
    ) {
        Utility.printLog(TAG, "setShadiMatchesAdapter")
        if (mAdapter == null) {
            mAdapter = matchesList?.let {
                EventDemoListAdapter(
                    this,
                    it as MutableList<Featured>, this::onClick
                )
            }
/*
            binding.rvUsers.setLayoutManager(
                LinearLayoutManager(
                    this,
                    RecyclerView.VERTICAL,
                    false
                )
            )
*/
            //   binding.rvUsers.setAdapter(mAdapter)
        } else {
            mAdapter?.setItems(matchesList)
        }
    }

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onClick(view: View?) {
        var item: Featured? = null
        if (view?.id == R.id.accept_button || view?.id == R.id.reject_buttuon) {
            item = view?.getTag(R.string.click_tag) as Featured
        }
        when (view?.id) {
            R.id.btn_retry -> {
                binding.loading = true
                myviewModel.loadData()
            }
            R.id.accept_button -> {
                Utility.printLog(TAG, "accepted " + item.toString())
                //myviewModel.onAccept(item)
            }
            R.id.reject_buttuon -> {
                Utility.printLog(TAG, "reject " + item.toString())
                //myviewModel.onReject(item)
            }


        }

    }
}
