package com.andef.mybooks.presentation.main

import androidx.compose.foundation.border
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.mybooks.R
import com.andef.mybooks.navigation.NavigationState

@Composable
fun MainBottomBar(navigationState: NavigationState) {
    NavigationBar(
        modifier = Modifier.border(1.dp, colorResource(R.color.light_gray)),
        containerColor = colorResource(R.color.white)
    ) {
        val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination
        for (item in items) {
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                selected = isInHierarchy(currentDestination, item),
                onClick = {
                    navigationState.navigateTo(item.route)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(item.icon),
                        tint = getColor(currentDestination, item),
                        contentDescription = stringResource(item.contentDescriptionResId),
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.titleResId),
                        color = getColor(currentDestination, item),
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}

@Composable
private fun getColor(currentDestination: NavDestination?, item: MainBottomBarItem): Color {
    return if (isInHierarchy(currentDestination, item)) {
        colorResource(R.color.blue)
    } else {
        colorResource(R.color.light_gray)
    }
}

private fun isInHierarchy(
    currentDestination: NavDestination?,
    item: MainBottomBarItem
): Boolean {
    return currentDestination?.hierarchy?.any { it.route == item.route } == true
}

private val items = listOf(
    MainBottomBarItem.FindScreenItem,
    MainBottomBarItem.FavouriteScreenItem
)