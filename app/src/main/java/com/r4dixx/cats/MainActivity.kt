package com.r4dixx.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.r4dixx.cats.design.theme.CATSSystemBarStyle
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.feature.account.ui.view.AccountSheetScaffold
import com.r4dixx.cats.feature.banks.ui.view.BanksScaffold
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(CATSSystemBarStyle.status, CATSSystemBarStyle.navigation)
        setContent {
            KoinAndroidContext {
                CATSTheme {
                    val navController = rememberNavController()

                    NavHost(navController, CATSRoute.Banks.ROUTE) {
                        composable(CATSRoute.Banks.ROUTE) {
                            BanksScaffold(
                                viewModel = koinViewModel(),
                                onAccountClick = { bankName, accountId ->
                                    navController.navigate(
                                        CATSRoute.Account.createRoute(bankName, accountId)
                                    )
                                }
                            )
                        }

                        composable(
                            route = CATSRoute.Account.ROUTE,
                            arguments = CATSRoute.Account.arguments
                        ) { backStackEntry ->
                            val bankName = backStackEntry.arguments?.getString(CATSRoute.Account.ARG_BANK_NAME)
                            val accountId = backStackEntry.arguments?.getLong(CATSRoute.Account.ARG_ACCOUNT_ID)

                            if (bankName != null && accountId != null) {
                                AccountSheetScaffold(
                                    viewModel = koinViewModel(parameters = {
                                        parametersOf(
                                            bankName,
                                            accountId
                                        )
                                    }),
                                    onBack = { navController.popBackStack() },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}