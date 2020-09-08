package com.example.eventdemo.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

class FragmentPagerItem protected constructor(
    title: CharSequence?,
    width: Float,
    private val className: String,
    private val args: Bundle
) :
    PagerItem(title!!, width) {

    fun instantiate(
        context: Context?,
        position: Int
    ): Fragment {
        setPosition(args, position)
        return Fragment.instantiate(context!!, className, args)
    }

    companion object {
        private const val TAG = "FragmentPagerItem"
        private const val KEY_POSITION = "$TAG:Position"
        fun of(
            title: CharSequence?,
            clazz: Class<out Fragment?>
        ): FragmentPagerItem {
            return of(title, 1f, clazz)
        }

        fun of(
            title: CharSequence?,
            clazz: Class<out Fragment?>,
            args: Bundle
        ): FragmentPagerItem {
            return of(title, 1f, clazz, args)
        }

        @JvmOverloads
        fun of(
            title: CharSequence?, width: Float,
            clazz: Class<out Fragment?>, args: Bundle = Bundle()
        ): FragmentPagerItem {
            return FragmentPagerItem(title, width, clazz.name, args)
        }

        fun hasPosition(args: Bundle?): Boolean {
            return args != null && args.containsKey(KEY_POSITION)
        }

        fun getPosition(args: Bundle): Int {
            return if (hasPosition(args)) args.getInt(
                KEY_POSITION
            ) else 0
        }

        fun setPosition(args: Bundle, position: Int) {
            args.putInt(KEY_POSITION, position)
        }
    }

}