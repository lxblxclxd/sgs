package com.seu.biz.rule

import com.seu.biz.player.Player
import com.seu.biz.table.ITable

/**
 * 规则工具类
 * 将三国杀的规则添加至该游戏
 * 该类包含基本规则，卡牌规则，武将规则
 * 由于每桌游戏的这三类规则也许会有不同，所以不作为静态类，并引入table对象
 * @param table table对象
 */
class RuleUtil(val table: ITable) {
    val basicRule: BasicRule = BasicRule(table)

    public fun generateGamePeriod(): GamePhase {
        return basicRule.game()
    }

    public fun generateGameTurnPeriod(player: Player): GamePhase {
        return basicRule.turn(player)
    }

    public fun generatePhase(player: Player, phaseName:String): GamePhase {
        return when(phaseName) {
            "phase_begin" -> basicRule.beginPhase(player)
            "phase_judge" -> basicRule.judgePhase(player)
            "phase_draw" -> basicRule.drawPhase(player)
            "phase_play" -> basicRule.playPhase(player)
            "phase_discard" -> basicRule.discardPhase(player)
            "phase_end" -> basicRule.endPhase(player)
            else -> throw Exception("生成阶段异常-无法识别的阶段名")
        }
    }
}