package com.zaki.pintask.domain.repository

import androidx.lifecycle.LiveData
import com.zaki.pintask.domain.model.Task

/**
 * Created by Zaki on 05-10-2023.
 */
interface TaskRepository {

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getTasks(): LiveData<List<Task>>

    fun getTaskById(taskId: Int): LiveData<Task>

}