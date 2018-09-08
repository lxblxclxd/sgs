package com.seu.biz.event.impl

import com.seu.biz.event.IGameEvent
import com.seu.biz.player.Player
import com.seu.vo.table.common.Deck

class PlayerEventImpl(private val player: Player, eventName: String, params: List<Any>? = null) : IGameEvent {

    override val obj: Any = player
    override val eventType: String = "player_$eventName"
    override var source: Any? = null
    override var resultSet: Any? = null
    lateinit var event: (Player) -> Any
    lateinit var description: String


    init {
        when (eventName) {
            "getCardsFromDeck" -> {
                if (params == null || params.size < 2) throw Exception("创建事件错误-无法完成摸牌动作")
                drawCards(
                        params[0] as Deck,
                        params[1] as Int,
                        params.getOrNull(2) as String?
                )
            }
        }
    }

    override fun execute(): PlayerEventImpl {
        resultSet = event(player)
        return this
    }

    private fun drawCards(source: Deck, num: Int, method: String?) {
        event = { p: Player ->
            p.getCardsFromDeck(source, num, method)
        }
        val m = when (method) {
            "random" -> "随机"
            "bottom" -> "底"
            else -> "顶"
        }
        description = "从牌堆${m}摸了${num}张牌"
    }

    override fun toString(): String {
        return "$player$description$resultSet"
    }
}