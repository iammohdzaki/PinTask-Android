package com.zaki.pintask.domain.repository

import androidx.lifecycle.LiveData
import com.zaki.pintask.data.data_source.TaskDao
import com.zaki.pintask.domain.model.Task
import com.zaki.pintask.domain.repository.TaskRepository
import javax.inject.Inject

/**
 * Created by Zaki on 05-10-2023.
 */
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override fun getTasks(): LiveData<List<Task>> {
        return taskDao.getTasks()
    }

    override fun getTaskById(taskId: Int): LiveData<Task> {
        return taskDao.getTaskById(taskId)
    }
}