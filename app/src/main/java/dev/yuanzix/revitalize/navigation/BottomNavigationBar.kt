package dev.yuanzix.revitalize.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val navItems =
        listOf(NavRoutes.Home, NavRoutes.Attendance, NavRoutes.Performance, NavRoutes.Others)
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(item.iconId),
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.screen)
                }
            )
        }
    }
}