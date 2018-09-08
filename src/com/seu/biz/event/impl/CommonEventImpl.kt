package com.seu.biz.event.impl

import com.seu.biz.event.IGameEvent

class CommonEventImpl:IGameEvent {
    override val obj: Any = ""
    override val eventType: String = ""
    override val resultSet: Any? = null
    override var source: Any? = null

    override fun execute(): IGameEvent {
        return this
    }

}