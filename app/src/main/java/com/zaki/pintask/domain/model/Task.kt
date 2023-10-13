package com.zaki.pintask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zaki.pintask.utils.RoomConstants

@Entity(tableName = RoomConstants.TASK_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val taskLabel: String,
    val taskType: Int,
    val createdOn: Long,
    val expireOn: Long
)
