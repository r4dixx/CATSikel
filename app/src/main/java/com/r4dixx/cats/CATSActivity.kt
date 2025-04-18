package com.r4dixx.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.design.theme.Dimension.spacingLarge
import com.r4dixx.cats.design.theme.SystemBarStyle
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.ui.account.AccountSheet
import com.r4dixx.cats.ui.banks.BanksScreen
import org.koin.compose.KoinContext

class CATSActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(SystemBarStyle.status, SystemBarStyle.navigation)
        setContent {
            KoinContext {
                CATSTheme {
                    val navController = rememberNavController()
                    NavHost(navController, CATSRoute.Banks.route) {
                        composable(CATSRoute.Banks.route) {
                            BanksScreen(
                                onAccountClick = { account ->
                                    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
                                    savedStateHandle?.set(CATSRoute.Account.KEY_ACCOUNT, account)
                                    navController.navigate(CATSRoute.Account.route)
                                }
                            )
                        }
                        composable(CATSRoute.Account.route) {
                            val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
                            val account = savedStateHandle?.get<Account>(CATSRoute.Account.KEY_ACCOUNT)

                            if (account != null) {
                                AccountSheet(
                                    account = account,
                                    onDismiss = { navController.popBackStack() },
                                    modifier = Modifier
                                        .statusBarsPadding()
                                        .navigationBarsPadding()
                                        .padding(vertical = spacingLarge)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
