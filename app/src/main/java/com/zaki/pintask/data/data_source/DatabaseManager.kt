package com.zaki.pintask.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zaki.pintask.domain.model.Task

/**
 * Created by Zaki on 04-10-2023.
 */
@Database(
    entities = [Task::class],
    version = 1
)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}