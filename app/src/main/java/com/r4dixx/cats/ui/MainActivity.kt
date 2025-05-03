package com.r4dixx.cats.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.r4dixx.cats.CATSRoute
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.feature.account.ui.AccountSheetScaffold
import com.r4dixx.cats.feature.banks.ui.BanksScaffold
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext {
                val state by viewModel.state.collectAsStateWithLifecycle()

                val systemBarColor = Color.Transparent.toArgb()
                enableEdgeToEdge(
                    statusBarStyle = if (state.isDarkGradient) {
                        SystemBarStyle.dark(systemBarColor)
                    } else {
                        SystemBarStyle.light(systemBarColor, systemBarColor)
                    },
                    navigationBarStyle = if (state.isDarkGradient) {
                        SystemBarStyle.dark(systemBarColor)
                    } else {
                        SystemBarStyle.light(systemBarColor, systemBarColor)
                    }
                )

                CATSTheme(darkGradientEnabled = state.isDarkGradient) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = CATSRoute.Banks.ROUTE,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .systemBarsPadding()
                    ) {
                        composable(CATSRoute.Banks.ROUTE) {
                            BanksScaffold(
                                viewModel = koinViewModel(),
                                onAccountClick = { accountId ->
                                    val route = CATSRoute.Account.createRoute(accountId)
                                    navController.navigate(route)
                                },
                                onIconClick = { viewModel.toggleDynamicColor() }
                            )
                        }

                        composable(
                            route = CATSRoute.Account.ROUTE,
                            arguments = CATSRoute.Account.arguments,
                        ) { backStackEntry ->
                            val accountId = backStackEntry.arguments?.getLong(CATSRoute.Account.ARG_ACCOUNT_ID)
                            AccountSheetScaffold(
                                viewModel = koinViewModel(parameters = { parametersOf(accountId) }),
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}