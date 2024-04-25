package com.dacha.core.listener

import com.dacha.model.EnvStateEvent
import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactor.awaitSingle
import org.redisson.Redisson
import org.redisson.config.Config

lateinit var envEventHandler: EnvEventHandler

val IODispatcher = Dispatchers.IO
val coroutineScope = CoroutineScope(IODispatcher)

fun Application.configureRedisClient() {
    val config = Config()
    config.useSingleServer().address = environment.config.property("storage.redis.url").getString()

    val client = Redisson.create(config).reactive()

    envEventHandler = EnvEventHandler(client)

    val topic = environment.config.property("storage.redis.topic").getString()

    client.getTopic(topic).addListener(EnvStateEvent::class.java) { _, msg ->
        coroutineScope.launch {
            envEventHandler.handle(msg)
        }
    }.block()
}