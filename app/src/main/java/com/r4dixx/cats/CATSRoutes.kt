package com.r4dixx.cats

sealed class CATSRoute(val route: String) {
    data object Banks : CATSRoute("route_banks")
    data object Account : CATSRoute("route_account")
}
