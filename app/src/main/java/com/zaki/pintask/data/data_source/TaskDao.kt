package com.zaki.pintask.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zaki.pintask.domain.model.Task
import com.zaki.pintask.utils.RoomConstants

/**
 * Created by Zaki on 04-10-2023.
 */
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM ${RoomConstants.TASK_TABLE}")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM ${RoomConstants.TASK_TABLE} WHERE taskId = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

}