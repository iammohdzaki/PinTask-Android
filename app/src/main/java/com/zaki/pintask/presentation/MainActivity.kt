package com.zaki.pintask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zaki.pintask.R
import com.zaki.pintask.domain.model.BottomNavigationItem
import com.zaki.pintask.presentation.components.AnimatedAddButton
import com.zaki.pintask.presentation.navigation.Screens
import com.zaki.pintask.presentation.screens.CalenderScreen
import com.zaki.pintask.presentation.screens.HomeScreen
import com.zaki.pintask.presentation.screens.SettingsScreen
import com.zaki.pintask.presentation.theme.AppTheme
import com.zaki.pintask.utils.UiText

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                //Initializing the default selected item
                var navigationSelectedItem by remember {
                    mutableIntStateOf(0)
                }

                /**
                 * By using the rememberNavController()
                 * we can get the instance of the navController
                 */
                val navController = rememberNavController()
                //Scaffold to hold our bottom navigation Bar
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            //Getting the list of bottom navigation items for our data class
                            BottomNavigationItem().getNavigationItems(LocalContext.current)
                                .forEachIndexed { index, navigationItem ->
                                    NavigationBarItem(
                                        label = {
                                            Text(navigationItem.label)
                                        },
                                        icon = {
                                            Icon(
                                                navigationItem.icon,
                                                contentDescription = navigationItem.label
                                            )
                                        },
                                        selected = index == navigationSelectedItem,
                                        onClick = {
                                            navigationSelectedItem = index
                                            navController.navigate(navigationItem.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                    )
                                }
                        }
                    },
                    floatingActionButton = {
                        AnimatedAddButton(
                            value = UiText.StringResource(R.string.new_task),
                            onClick = {

                            }
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        //Map Screens based on their routes
                        composable(Screens.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Screens.Calender.route) {
                            CalenderScreen(navController)
                        }
                        composable(Screens.Settings.route) {
                            SettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}