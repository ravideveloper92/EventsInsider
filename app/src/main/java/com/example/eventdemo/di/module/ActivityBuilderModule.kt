package com.example.eventdemo.di.module

import com.example.eventdemo.ui.MainActivity2
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity2
}
