package com.zaki.pintask.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.PushPin
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaki.pintask.domain.model.Task
import com.zaki.pintask.presentation.theme.green
import com.zaki.pintask.presentation.theme.sansFontFamily
import com.zaki.pintask.utils.DateUtils
import com.zaki.pintask.utils.TaskType
import com.zaki.pintask.utils.UiText

/**
 * Created by Zaki on 15-10-2023.
 */
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun TaskView(task: Task) {

    var isCompleted by remember {
        mutableStateOf(task.isCompleted)
    }

    val displayDate = when (val date: UiText = DateUtils.formatTime(System.currentTimeMillis())) {
        is UiText.DynamicString -> date.asString()
        else -> {
            date.asString(LocalContext.current)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            IconToggleButton(
                checked = isCompleted,
                onCheckedChange = {
                    isCompleted = !isCompleted
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                val transition = updateTransition(isCompleted, label = "")
                val tint by transition.animateColor(label = "iconColor") { isChecked ->
                    if (isChecked) green else MaterialTheme.colorScheme.onSurface
                }
                val size by transition.animateDp(
                    transitionSpec = {
                        if (false isTransitioningTo true) {
                            keyframes {
                                durationMillis = 250
                                30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                                35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                                40.dp at 75 // ms
                                35.dp at 150 // ms
                            }
                        } else {
                            spring(stiffness = Spring.StiffnessVeryLow)
                        }
                    },
                    label = "Size"
                ) { 30.dp }
                Icon(
                    imageVector = if (!isCompleted) Icons.Rounded.RadioButtonUnchecked else Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(size)
                )
            }
            Column {
                Text(
                    text = task.taskLabel,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = sansFontFamily
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp, end = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CalendarMonth,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = displayDate,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = sansFontFamily
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Icon(
                        imageVector = if (task.taskType == TaskType.NOTIFY) Icons.Rounded.Notifications else Icons.Rounded.PushPin,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskViewPreview() {
    TaskView(task = Task(1, "Task Number 1", TaskType.NOTIFY, 240L, 340L, true))
}