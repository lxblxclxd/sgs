package com.seu.biz.table

import com.seu.biz.event.Log
import com.seu.biz.player.Player
import com.seu.biz.rule.GamePhase
import com.seu.biz.rule.RuleUtil
import com.seu.vo.card.struct.Card
import com.seu.vo.table.common.Deck
import java.util.*
import kotlin.collections.ArrayList

interface ITable {
    public fun start()

    /* 玩家 */
    public val players: ArrayList<Player>
    /* 所有游戏卡牌 */
    val cards: ArrayList<Card>
    /* 摸牌堆 */
    public var deckUnused: Deck
    /* 弃牌堆 */
    public var deckUsed: Deck
    /* 游戏阶段 */
    public val phaseStack: Stack<GamePhase>

    /* 游戏记录 */
    public var log: Log

    /* 游戏规则 */
    public var rule:RuleUtil
}