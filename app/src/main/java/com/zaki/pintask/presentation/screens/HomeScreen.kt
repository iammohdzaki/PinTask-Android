package com.zaki.pintask.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zaki.pintask.R
import com.zaki.pintask.domain.model.Task
import com.zaki.pintask.presentation.components.EmptyTask
import com.zaki.pintask.presentation.components.TaskView
import com.zaki.pintask.presentation.theme.sansFontFamily
import com.zaki.pintask.utils.DateUtils
import com.zaki.pintask.utils.TaskType

/**
 * Created by Zaki on 05-10-2023.
 */
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier.padding(16.dp)
    ) {
        val (tvHeading, tvSubHeading, clEmpty, taskList) = createRefs()
        val tasks = listOf(
            Task(1, "Submit the docs to college", TaskType.PINNED, 240L, 340L),
            Task(2, "Remove Auto Subscription for Netflix and Disney + Hotstar,Remove Auto Subscription for Netflix and Disney + Hotstar", TaskType.NOTIFY, 240L, 340L,true)
        )
        Text(
            text = stringResource(R.string.your_tasks),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = sansFontFamily
            ),
            modifier = Modifier
                .constrainAs(tvHeading) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = DateUtils.formatTo(System.currentTimeMillis()),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = sansFontFamily,
            ),
            modifier = Modifier
                .constrainAs(tvSubHeading) {
                    top.linkTo(tvHeading.bottom)
                    start.linkTo(parent.start)
                }
        )
        if (tasks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(taskList) {
                        top.linkTo(tvSubHeading.bottom)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
            ) {
                items(tasks) { task ->
                    TaskView(task = task)
                }
            }
        } else {
            EmptyTask(
                modifier = modifier
                    .constrainAs(clEmpty) {
                        top.linkTo(tvSubHeading.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

