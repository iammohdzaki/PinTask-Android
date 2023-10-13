package com.zaki.pintask.data.data_source.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.zaki.pintask.data.data_source.DatabaseManager
import com.zaki.pintask.data.data_source.TaskDao
import com.zaki.pintask.domain.model.Task
import com.zaki.pintask.util.getOrAwaitValue
import com.zaki.pintask.utils.TaskType
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Zaki on 05-10-2023.
 */
@SmallTest
@HiltAndroidTest
class TaskDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("task_test_db")
    lateinit var databaseManager: DatabaseManager
    lateinit var taskDao: TaskDao


    @Before
    fun setup() {
        hiltRule.inject()
        taskDao = databaseManager.getTaskDao()
    }

    @After
    fun tearDown() {
        databaseManager.close()
    }

    @Test
    fun insertTask() = runTest {
        val task = Task(1, "Task Number 1", TaskType.NOTIFY, 240L, 340L)
        taskDao.insertTask(task)

        val tasks = taskDao.getTasks().getOrAwaitValue()
        println(tasks)
        Truth.assertThat(tasks).contains(task)
    }

    @Test
    fun updateTask() = runTest {
        val task = Task(1, "Task Number 1", TaskType.NOTIFY, 240L, 340L)
        taskDao.insertTask(task)
        println(task)
        val updatedTask = Task(1, "Updated Task Number 1", TaskType.NOTIFY, 240L, 340L)
        taskDao.updateTask(updatedTask)
        val tasks = taskDao.getTasks().getOrAwaitValue()
        println(updatedTask)
        Truth.assertThat(tasks).contains(updatedTask)
    }

    @Test
    fun deleteTask() = runTest {
        val task = Task(1, "Task Number 1", TaskType.NOTIFY, 240L, 340L)
        taskDao.insertTask(task)
        println(task)

        val tasks = taskDao.getTasks().getOrAwaitValue()
        println(tasks)

        taskDao.deleteTask(task)

        val updatedTasks = taskDao.getTasks().getOrAwaitValue()
        Truth.assertThat(updatedTasks).doesNotContain(task)
    }

    @Test
    fun getTasks() = runTest {
        val task1 = Task(1, "Task Number 1", TaskType.NOTIFY, 240L, 340L)
        val task2 = Task(2, "Task Number 2", TaskType.NOTIFY, 240L, 340L)
        val task3 = Task(3, "Task Number 3", TaskType.NOTIFY, 240L, 340L)

        taskDao.insertTask(task1)
        taskDao.insertTask(task2)
        taskDao.insertTask(task3)

        val tasks = taskDao.getTasks().getOrAwaitValue()
        Truth.assertThat(tasks).containsAtLeast(task1, task2, task3)
    }

    @Test
    fun getTaskById() = runTest {
        val task1 = Task(1, "Task Number 1", TaskType.NOTIFY, 240L, 340L)
        val task2 = Task(2, "Task Number 2", TaskType.NOTIFY, 240L, 340L)
        val task3 = Task(3, "Task Number 3", TaskType.NOTIFY, 240L, 340L)

        taskDao.insertTask(task1)
        taskDao.insertTask(task2)
        taskDao.insertTask(task3)

        val tasks = taskDao.getTaskById(2).getOrAwaitValue()
        Truth.assertThat(tasks).isEqualTo(task2)
    }
}