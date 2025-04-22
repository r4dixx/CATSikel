package com.r4dixx.cats

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.feature.account.ui.AccountSheetScaffold
import com.r4dixx.cats.feature.banks.ui.BanksScaffold
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                CATSTheme {
                    val navController = rememberNavController()

                    NavHost(navController, CATSRoute.Banks.ROUTE) {
                        composable(CATSRoute.Banks.ROUTE) {
                            BanksScaffold(
                                viewModel = koinViewModel(),
                                onAccountClick = { accountId -> navController.navigate(CATSRoute.Account.createRoute(accountId)) },
                            )
                        }

                        dialog(
                            route = CATSRoute.Account.ROUTE,
                            arguments = CATSRoute.Account.arguments,
                            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
                        ) { backStackEntry ->
                            val accountId = backStackEntry.arguments?.getLong(CATSRoute.Account.ARG_ACCOUNT_ID)

                            val windowProvider = LocalView.current.parent as? DialogWindowProvider
                            windowProvider?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

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