package com.zaki.pintask.presentation.navigation

/**
 * Created by Zaki on 12-10-2023.
 */
sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Calender : Screens("calender")
    object Settings : Screens("settings")
    object TaskEditor : Screens("task_editor")
    object About : Screens("about")
}
