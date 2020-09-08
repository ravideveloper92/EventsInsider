package com.example.eventdemo.ui

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.collection.SparseArrayCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.lang.ref.WeakReference


class FragmentPagerItemAdapter(fm: FragmentManager?, pages: FragmentPagerItems) :
    FragmentPagerAdapter(fm!!) {
    private val pages: FragmentPagerItems
    private val holder: SparseArrayCompat<WeakReference<Fragment>>
    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return getPagerItem(position).instantiate(pages.context, position)
    }

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        val item = super.instantiateItem(container, position)
        if (item is Fragment) {
            holder.put(position, WeakReference<Fragment>(item as Fragment))
        }
        return item
    }

    override fun destroyItem(
        @NonNull container: ViewGroup,
        position: Int,
        @NonNull `object`: Any
    ) {
        holder.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getPagerItem(position).title
    }

    override fun getPageWidth(position: Int): Float {
        return super.getPageWidth(position)
    }

    fun getPage(position: Int): Fragment? {
        val weakRefItem: WeakReference<Fragment>? = holder[position]
        return weakRefItem?.get()
    }

    protected fun getPagerItem(position: Int): FragmentPagerItem {
        return pages.get(position)!!
    }

    init {
        this.pages = pages
        holder = SparseArrayCompat<WeakReference<Fragment>>(pages.size)
    }
}

