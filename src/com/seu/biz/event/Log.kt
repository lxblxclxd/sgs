package com.seu.biz.event

import com.seu.biz.player.Player
import com.seu.biz.rule.GamePhase

/**
 * 游戏记录类
 * 记录游戏从头开始的所有已发生的事件记录
 * 一些技能或规则需要查阅游戏记录进行判断，该类提供了一些便于查询的函数
 */
class Log {
    var events = ArrayList<IGameEvent>()

    /**
     * 记录并打印出游戏记录
     * @param event 刚刚结束的事件
     */
    fun recordAndPrint(event: IGameEvent) {
        events.add(event)
        if (event.toString().isNotEmpty())
            println(event)
    }

    /**
     * 获取上一个角色的函数
     * @return 获取上一个回合的发起者，即使是小胖的放权回合，发起者也是小胖
     */
    fun getLastPlayer(): Player? {
        for (event in events.reversed())
            if (event.eventType == "phase_execute")//如果是阶段性事件
                if ((event.obj as GamePhase).name == "turn")//如果是回合
                    if (event.source is Player)//如果回合的发起者是玩家
                        return event.source as Player//返回这个玩家
        return null
    }

    /**
     * 根据回合间空档记录获取当前回合角色的函数
     * @return 当前回合角色，如果是第一个回合，返回null
     */
    fun getCurrentPlayer(): Player? {
        for (event in events.reversed())
            if (event.eventType == "phase_nextTurn")
                return event.resultSet as Player
        return null
    }

    /**
     * 获取此回合进行过的所有阶段的函数
     * @return 获取上一个执行了什么阶段，准备阶段~结束阶段六种中的一种
     */
    fun getPhasesInThisTurn(): ArrayList<GamePhase> {
        val phases = ArrayList<GamePhase>()
        for (event in events.reversed()) {
            if (event.eventType == "phase_nextTurn")//如果是回合空档
                return phases//返回
            if (event.eventType == "phase_execute")//如果是阶段性事件
                if ((event.obj as GamePhase).name.startsWith("phase"))//如果是六个阶段的一种
                    phases.add(event.obj as GamePhase)//添加这个阶段
        }
        return phases
    }
}