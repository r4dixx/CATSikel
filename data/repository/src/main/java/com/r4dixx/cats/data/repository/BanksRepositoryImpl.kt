package com.r4dixx.cats.data.repository

import com.r4dixx.cats.data.api.source.BanksAPIRawDataSource
import com.r4dixx.cats.data.local.source.BanksLocalDataSource
import com.r4dixx.cats.domain.model.Account
import com.r4dixx.cats.domain.model.Bank
import com.r4dixx.cats.domain.repository.BanksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BanksRepositoryImpl(
//    private val api: BanksAPIDataSource,
    private val raw: BanksAPIRawDataSource,
    private val local: BanksLocalDataSource,
) : BanksRepository {

    override fun getBanks(): Flow<List<Bank>> = flow {
/*        emitAll(getBanksFromApiAndSave())
    }.catch {*/
        emitAll(getBanksFromLocal())
    }.catch {
        emitAll(getBanksFromRaw())
    }

/*    private fun getBanksFromApiAndSave(): Flow<List<Bank>> = api.getBanks().map { apiBanks ->
        val domainBanks = apiBanks
            .map { it.toDomainBank() }
            .takeIf { it.isNotEmpty() } ?: throw Exception("API source is empty.")
        val localBanks = domainBanks.map { it.toLocalBank() }
        local.upsertBanksData(localBanks)
        domainBanks
    }*/

    private fun getBanksFromLocal(): Flow<List<Bank>> =
        local.getBanksWithAccounts().map { localBanks ->
            localBanks
                .map { it.toDomainBank() }
                .takeIf { it.isNotEmpty() } ?: throw Exception("Local source is empty.")
        }

    private fun getBanksFromRaw(): Flow<List<Bank>> = raw.getBanks().map { rawBanks ->
        rawBanks
            .map { it.toDomainBank() }
            .takeIf { it.isNotEmpty() } ?: throw Exception("Raw source is empty.")
    }

    override fun getAccount(id: Long): Flow<Account> =
        local.getAccountWithOperations(id).map { localAccount ->
            localAccount.toDomainAccount()
        }
}