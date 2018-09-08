package com.seu.biz.event.impl

import com.seu.biz.event.IGameEvent
import com.seu.biz.player.Player
import com.seu.biz.rule.BasicRule
import com.seu.biz.rule.GamePhase
import com.seu.biz.table.ITable

/**
 * 阶段性事件
 * 执行一个回合，执行一个xx阶段，都成为是阶段性事件
 * @param phase 该事件所应的实际阶段对象
 * @param eventName 事件名称，用于内部判断以确认该事件对这个阶段执行什么样的操作
 */
class PhaseEventImpl(private val phase: GamePhase, eventName: String, params: List<Any>? = null) : IGameEvent {

    override val obj: Any = phase
    override var source: Any? = null
    override var resultSet: Any? = null
    override val eventType: String = "phase_$eventName"
    lateinit var event: (GamePhase) -> Any

    init {
        when (eventName) {

        /* 执行事件 - 当注册在某个时间点时，在那个时间点会执行该事件 */
            "execute" -> {
                event = { p: GamePhase -> p.execute() }
            }

        /* 执行下一回合 - 在上一回合结束后的空档，注册生成并执行下一回合的事件 */
        /* 值得注意的是，这个下一回合也需要递归式的生成执行再下一回合的事件 */
        /* 十分重要的辅助空档时间段，在log记录中可以轻松获得上一回合角色和当前回合角色 */
            "nextTurn" -> {
                if (params == null || params.isEmpty()) throw Exception("创建事件错误-上一个是谁的回合来着？")
                nextTurn(params[0] as ITable)
            }

            "nextPhase" -> {
                if (params == null || params.isEmpty()) throw Exception("创建事件错误-下一个是什么阶段来着？")
                nextPhase(params[0] as ITable)
            }
        }
    }

    /**
     * 执行此事件
     * @return 返回自己以供log记录
     */
    override fun execute(): IGameEvent {
        resultSet = event(phase)
        return this
    }

    private fun nextTurn(table: ITable) {

        val players = table.players
        val ruleUtil = table.rule
        event = { p: GamePhase ->
            kotlin.run {
                val player = table.log.getLastPlayer() ?: throw Exception("创建事件错误-上一个是谁的回合来着？")
                var nextPlayer: Player
                var seatNum = player.seatNum
                do {
                    seatNum %= players.size
                    nextPlayer = players[seatNum]
                    seatNum++
                } while (nextPlayer.isDead())
                val nextTurn = ruleUtil.generateGameTurnPeriod(nextPlayer)
                p.registerOnProcess(PhaseEventImpl(nextTurn, "execute").modifySource(nextPlayer))
                p.registerOnProcess(PhaseEventImpl(p, "nextTurn", listOf(table)))
                return@run nextPlayer
            }
        }
    }

    private fun nextPhase(table: ITable) {

        val ruleUtil = table.rule
        event = { p: GamePhase ->
            kotlin.run {
                var nextPhaseName = table.log.getPhasesInThisTurn().firstOrNull()?.name ?: ""
                do {
                    nextPhaseName = BasicRule.phasesName.getOrNull(BasicRule.phasesName.indexOf(nextPhaseName) + 1) ?: ""
                } while (false)
                if (nextPhaseName.isNotEmpty()) {
                    val currentPlayer = table.log.getCurrentPlayer() ?: table.players.first()
                    val nextPhase = ruleUtil.generatePhase(currentPlayer, nextPhaseName)
                    p.registerOnProcess(PhaseEventImpl(nextPhase, "execute"))
                    p.registerOnProcess(PhaseEventImpl(p, "nextPhase", listOf(table)))
                }
            }
        }
    }

    override fun toString(): String {
        return ""
    }

}