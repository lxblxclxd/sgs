package com.seu.biz.player

import com.seu.vo.card.struct.Card
import com.seu.vo.table.common.Deck

open class Player(val seatNum: Int) {

    public var alive = true
    public var handCards: ArrayList<Card> = ArrayList()
    public var equipCards: ArrayList<Card?>? = arrayListOf(null, null, null, null, null)
    public var judgeCards: ArrayList<Card>? = ArrayList()

    fun isAlive(): Boolean {
        return alive
    }

    fun isDead(): Boolean {
        return !alive
    }

    fun die() {
        alive = false
    }

    /**
     * Player can get card from deck of the specific area.
     *
     * @param deck list of cards
     * @param num refers to how much cards will you get
     * @param method "top" -> get cards from top of the deck
     *             "bottom" -> get cards from bottom of the deck
     *             "random" -> get cards randomly
     */
    fun getCardsFromDeck(deck: Deck, num: Int, method: String? = null): List<Card> {
        val cards: List<Card> = when (method) {
            null, "top" ->
                deck.getTop(num)
            else -> deck.getDown(num)
        //TODO: 随机摸牌的添加
        }
        handCards.addAll(cards)
        return cards
    }

    /**
     * Player can give card to deck of the specific area.
     *
     * @param deck list of cards
     * @param cards refers to which cards you are giving
     * @param method "top" -> give cards to top of the deck
     *             "bottom" -> give cards to bottom of the deck
     *             "random" -> give cards to randomly anywhere
     */
    fun giveHandCardsToDeck(deck: Deck, cards: ArrayList<Card>, method: String? = null): List<Card> {
        handCards.removeAll(cards)
        when (method) {
            null, "top" -> deck.addTop(cards)
            else -> deck.addDown(cards)
        }
        return cards
    }

    override fun toString(): String {
        return "玩家$seatNum"
    }
}