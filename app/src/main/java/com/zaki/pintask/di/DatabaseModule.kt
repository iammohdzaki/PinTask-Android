package com.zaki.pintask.di

import android.content.Context
import androidx.room.Room
import com.zaki.pintask.BuildConfig
import com.zaki.pintask.data.data_source.DatabaseManager
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by Zaki on 04-10-2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, DatabaseManager::class.java, BuildConfig.DATABASE_NAME)

    fun provideTaskDao(
        databaseManager: DatabaseManager
    ) = databaseManager.getTaskDao()
}