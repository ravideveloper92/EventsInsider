package com.example.eventdemo.ui

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

class FragmentPagerItems(context: Context?) :
    PagerItems<FragmentPagerItem?>(context!!) {
    class Creator(context: Context?) {
        private val items: FragmentPagerItems
        fun add(
            @StringRes title: Int,
            clazz: Class<out Fragment?>?
        ): Creator {
            return add(clazz?.let { FragmentPagerItem.of(items.context?.getString(title), it) })
        }

        fun add(
            @StringRes title: Int,
            clazz: Class<out Fragment?>?,
            args: Bundle?
        ): Creator {
            return add(args?.let { clazz?.let { it1 ->
                FragmentPagerItem.of(items.context?.getString(title),
                    it1, it)
            } })
        }

        fun add(
            @StringRes title: Int,
            width: Float,
            clazz: Class<out Fragment?>?
        ): Creator {
            return add(clazz?.let {
                FragmentPagerItem.of(items.context?.getString(title), width,
                    it
                )
            })
        }

        fun add(
            @StringRes title: Int,
            width: Float,
            clazz: Class<out Fragment?>?,
            args: Bundle?
        ): Creator {
            return add(
                clazz?.let {
                    args?.let { it1 ->
                        FragmentPagerItem.of(
                            items.context?.getString(title),
                            width,
                            it,
                            it1
                        )
                    }
                }
            )
        }

        fun add(
            title: CharSequence?,
            clazz: Class<out Fragment?>?
        ): Creator {
            return add(clazz?.let { FragmentPagerItem.of(title, it) })
        }

        fun add(
            title: CharSequence?,
            clazz: Class<out Fragment?>?,
            args: Bundle?
        ): Creator {
            return add(clazz?.let { args?.let { it1 -> FragmentPagerItem.of(title, it, it1) } })
        }

        fun add(item: FragmentPagerItem?): Creator {
            items.add(item)
            return this
        }

        fun create(): FragmentPagerItems {
            return items
        }

        init {
            items = FragmentPagerItems(context)
        }
    }

    companion object {
        fun with(context: Context?): Creator {
            return Creator(context)
        }
    }
}