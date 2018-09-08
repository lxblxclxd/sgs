package com.seu.biz.table.impl

import com.seu.biz.event.Log
import com.seu.biz.rule.GamePhase
import com.seu.biz.player.Player
import com.seu.biz.rule.RuleUtil
import com.seu.biz.table.ITable
import com.seu.vo.card.common.CardInfo
import com.seu.vo.card.common.CardSuit
import com.seu.vo.card.common.CardUtil
import com.seu.vo.card.struct.Card
import com.seu.vo.table.common.Deck
import java.util.*
import kotlin.collections.ArrayList

class StandardTable(numOfPlayers: Int) : ITable {

    /* 玩家 */
    public override val players = ArrayList<Player>()
    /* 所有游戏卡牌 */
    override val cards = ArrayList<Card>()
    /* 摸牌堆 */
    public override var deckUnused = Deck(cards)
    /* 弃牌堆 */
    public override var deckUsed = Deck(ArrayList())

    public override val phaseStack: Stack<GamePhase> = Stack()

    /* 游戏规则 */
    public override var rule:RuleUtil = RuleUtil(this)

    /* 游戏记录 */
    override var log = Log()

    init {
        for (i in 1..numOfPlayers) {
            players.add(Player(i))
        }
        for (i in 1..160) {
            cards.add(CardUtil.generateCard(CardInfo(CardSuit.SPADE, i % 13 + 1, "杀", "basic")))
        }
        deckUnused.loadCards(cards)
    }

    var game = rule.generateGamePeriod()

    public override fun start() {
        game.execute()
    }

    private fun getAliveCount(): Int {
        var count = 0
        players.forEach { p -> if (p.isAlive()) count++ }
        return count
    }

    private fun reachWinRequirement(): Boolean {
        return getAliveCount() <= 1
    }
}