package com.seu.biz.event.impl

import com.seu.biz.event.IGameEvent

class ClientEventImpl(private val any: Any, eventName: String, params: List<Any>? = null) : IGameEvent {
    override val resultSet: Any? = null
    override var source: Any? = null
    override val eventType: String = "client"
    override val obj: Any = ""

    override fun execute(): IGameEvent {
        return this
    }
}