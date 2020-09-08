package com.example.eventdemo.ui

import android.content.Context
import java.util.*

abstract class PagerItems<T : PagerItem?> protected constructor(val context: Context) :
    ArrayList<T>()

