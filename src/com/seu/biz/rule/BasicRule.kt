package com.seu.biz.rule

import com.seu.biz.event.impl.*
import com.seu.biz.player.Player
import com.seu.biz.table.ITable

class BasicRule(val table: ITable) {

    val unitPrint = { a: Any? -> println(a) }

    companion object {
        val phasesName = listOf(
                "phase_begin",//准备阶段
                "phase_judge",//判定阶段
                "phase_draw",//摸牌阶段
                "phase_play",//出牌阶段
                "phase_discard",//弃牌阶段
                "phase_end"//结束阶段
        )
    }

    /**
     * 游戏
     */
    fun game(): GamePhase {
        val game = GamePhase(table, "game", "游戏")
        //游戏开始的发牌
        for (player in table.players) {
            val event = PlayerEventImpl(player, "getCardsFromDeck", listOf(table.deckUnused, 4))
            game.registerOnStart(event)
        }
        if (table.players.size < 2)
            throw Exception("游戏开始异常-游戏人数不足")
        val firstPlayer = table.players[0]
        val firstTurn = turn(firstPlayer)
        //第一个角色的回合
        game.registerOnProcess(PhaseEventImpl(firstTurn, "execute").modifySource(firstPlayer))
        //寻找下一个角色的回合
        game.registerOnProcess(PhaseEventImpl(game, "nextTurn", listOf(table)))
        return game
    }

    /**
     *游戏 -> 回合
     */
    fun turn(player: Player): GamePhase {
        val turn = GamePhase(table, "turn", "${player}的回合")
        val firstPhase = beginPhase(player)
        turn.registerOnProcess(PhaseEventImpl(firstPhase, "execute"))
        turn.registerOnProcess(PhaseEventImpl(turn, "nextPhase", listOf(table)))
        return turn
    }

    /**
     *游戏 -> 回合 -> 准备阶段
     */
    fun beginPhase(player: Player): GamePhase {
        val phase = GamePhase(table, "phase_begin", "准备阶段")
        return phase
    }

    /**
     *游戏 -> 回合 -> 判定阶段
     */
    fun judgePhase(player: Player): GamePhase {
        val phase = GamePhase(table, "phase_judge", "判定阶段")
        return phase
    }

    /**
     *游戏 -> 回合 -> 摸牌阶段
     */
    fun drawPhase(player: Player): GamePhase {
        val phase = GamePhase(table, "phase_draw", "摸牌阶段")
        val event = PlayerEventImpl(player, "getCardsFromDeck", listOf(table.deckUnused, 2))
        phase.registerOnStart(event)
        return phase
    }

    /**
     *游戏 -> 回合 -> 出牌阶段
     */
    fun playPhase(player: Player): GamePhase {
        val phase = GamePhase(table, "phase_play", "出牌阶段")
        return phase
    }

    /**
     *游戏 -> 回合 -> 弃牌阶段
     */
    fun discardPhase(player: Player): GamePhase {
        val phase = GamePhase(table, "phase_discard", "弃牌阶段")
        return phase
    }

    /**
     *游戏 -> 回合 -> 结束阶段
     */
    fun endPhase(player: Player): GamePhase {
        val phase = GamePhase(table, "phase_end", "结束阶段")
        return phase
    }
}