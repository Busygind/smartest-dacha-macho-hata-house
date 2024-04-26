package com.dacha.core.listener

import com.dacha.model.EnvStateEvent
import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactor.awaitSingle
import org.redisson.Redisson
import org.redisson.config.Config
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

lateinit var envEventHandler: EnvEventHandler

val IODispatcher = Dispatchers.IO
val coroutineScope = CoroutineScope(IODispatcher)

const val ENV_EVENT_QUEUE = "env_state_events"
const val CHUNK_SIZE = 100

fun Application.configureScheduler() {
    val config = Config()
    config.useSingleServer().address = environment.config.property("storage.redis.url").getString()

    val client = Redisson.create(config).reactive()

    envEventHandler = EnvEventHandler()


    val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(10)

    executor.scheduleAtFixedRate(
        {
            coroutineScope.launch {
                val events: List<EnvStateEvent> = client.getQueue<EnvStateEvent>(ENV_EVENT_QUEUE).poll(CHUNK_SIZE)
                    .awaitSingle()

                events.forEach {
                    envEventHandler.handle(it)
                }
            }
        },
        1000,
        100,
        TimeUnit.MILLISECONDS
    )
}