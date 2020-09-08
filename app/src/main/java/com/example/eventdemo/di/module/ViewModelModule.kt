package com.example.eventdemo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventdemo.di.annotation.ViewModelKey
import com.example.eventdemo.ui.MainViewModel
import com.example.eventdemo.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@SuppressWarnings("WeakerAccess")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory?): ViewModelProvider.Factory?
}