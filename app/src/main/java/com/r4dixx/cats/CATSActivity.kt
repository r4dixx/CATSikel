package com.r4dixx.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.ui.account.AccountSheet
import com.r4dixx.cats.ui.banks.BanksScreen
import org.koin.compose.KoinContext

class CATSActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()
        setContent {
            KoinContext {
                CATSTheme {
                    val navController = rememberNavController()
                    NavHost(navController, CATSRoute.Banks.route) {
                        composable(CATSRoute.Banks.route) {
                            BanksScreen(
                                onAccountClick = { account ->
                                    navController.currentBackStackEntry?.savedStateHandle?.set(CATSRoute.Account.key, account)
                                    navController.navigate(CATSRoute.Account.route)
                                }
                            )
                        }
                        composable(CATSRoute.Account.route) {
                            val account = navController
                                .previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<Account>(CATSRoute.Account.key)

                            account?.let {
                                AccountSheet(
                                    account = it,
                                    onDismiss = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
