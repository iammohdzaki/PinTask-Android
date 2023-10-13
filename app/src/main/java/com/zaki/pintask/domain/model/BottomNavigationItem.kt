package com.zaki.pintask.domain.model

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.ui.graphics.vector.ImageVector
import com.zaki.pintask.R
import com.zaki.pintask.presentation.navigation.Screens

/**
 * Created by Zaki on 13-10-2023.
 */
data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun getNavigationItems(context: Context): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = context.getString(R.string.my_tasks),
                icon = Icons.Filled.TaskAlt,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = context.getString(R.string.calender),
                icon = Icons.Filled.CalendarMonth,
                route = Screens.Calender.route
            ),
            BottomNavigationItem(
                label = context.getString(R.string.settings),
                icon = Icons.Filled.Settings,
                route = Screens.Settings.route
            ),
        )
    }
}
