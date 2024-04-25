package com.dacha.core.plugins

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
object DatabaseManager {
    private val dispatcher = Dispatchers.IO

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(dispatcher) {
            transaction { block() }
        }
}
