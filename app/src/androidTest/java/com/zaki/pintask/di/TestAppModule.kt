package com.zaki.pintask.di

import android.content.Context
import androidx.room.Room
import com.zaki.pintask.data.data_source.DatabaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Zaki on 05-10-2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named("task_test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, DatabaseManager::class.java)
            .allowMainThreadQueries()
            .build()

}