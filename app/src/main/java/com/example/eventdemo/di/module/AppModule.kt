package com.example.eventdemo.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.eventdemo.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application) : Context
    {
        return app.applicationContext
    }

}