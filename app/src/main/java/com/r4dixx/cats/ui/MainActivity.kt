package com.r4dixx.cats.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.r4dixx.cats.design.theme.CATSSystemBarStyle
import com.r4dixx.cats.design.theme.CATSTheme
import com.r4dixx.cats.navigation.CATSRoute
import com.r4dixx.cats.ui.account.AccountSheetScaffold
import com.r4dixx.cats.ui.banks.BanksScaffold
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(CATSSystemBarStyle.status, CATSSystemBarStyle.navigation)
        setContent {
            KoinAndroidContext {
                CATSTheme {
                    val navController = rememberNavController()

                    NavHost(navController, CATSRoute.Banks.route) {
                        composable(route = CATSRoute.Banks.route) {
                            BanksScaffold(
                                viewModel = koinViewModel(),
                                onAccountClick = { bank, account ->
                                    viewModel.saveBankAndAccount(bank, account)
                                    navController.navigate(CATSRoute.Account.route)
                                }
                            )
                        }

                        composable(CATSRoute.Account.route) {
                            val uiState by viewModel.state.collectAsStateWithLifecycle()
                            uiState.dataOrNull?.let { data ->
                                AccountSheetScaffold(
                                    viewModel = koinViewModel(parameters = { parametersOf(data.bank, data.account) }),
                                    onDismiss = { navController.popBackStack() },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}