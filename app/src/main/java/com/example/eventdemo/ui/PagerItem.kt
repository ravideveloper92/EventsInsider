package com.example.eventdemo.ui

import android.content.Context
import java.util.*


abstract class PagerItem protected constructor(val title: CharSequence, val width: Float) {

    companion object {
        protected const val DEFAULT_WIDTH = 1f
    }

}