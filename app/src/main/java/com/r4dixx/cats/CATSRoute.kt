package com.r4dixx.cats

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class CATSRoute {
    data object Banks : CATSRoute() {
        const val ROUTE = "route_banks"
    }

    data object Account : CATSRoute() {
        private const val ROUTE_NAME = "route_account"
        const val ARG_ACCOUNT_ID = "accountId"
        const val ROUTE = "$ROUTE_NAME/{$ARG_ACCOUNT_ID}"

        val arguments = listOf(
            navArgument(ARG_ACCOUNT_ID) { type = NavType.LongType }
        )

        fun createRoute(accountId: Long): String {
            return "$ROUTE_NAME/$accountId"
        }
    }
}
