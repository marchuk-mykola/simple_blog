package com.marchuk.test.core.presentation.mvvm

import androidx.navigation.NavController
import timber.log.Timber

/**
 * Abstract class that handles properly navController
 */
abstract class BaseNavigator {

    protected var navController: NavController? = null

    /**
     * Bind navController for using them in navigation events
     */
    fun bind(navController: NavController) {
        this.navController = navController
    }

    /**
     * Unbind navController to avoid memory leaks
     */
    fun unbind() {
        navController = null
    }

}