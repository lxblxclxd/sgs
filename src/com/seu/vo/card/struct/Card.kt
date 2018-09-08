package com.seu.vo.card.struct

import com.seu.vo.card.common.CardSuit

abstract class Card(cardSuit: CardSuit, cardRank: Int, cardName: String) {

    /* 花色 */
    private val suit: CardSuit = cardSuit
    /* 点数 */
    private val rank: Int = cardRank
    /* 牌名 */
    private val name: String = cardName

    override fun toString(): String {
        val rankS =
                when (rank) {
                    11 -> "J"
                    12 -> "Q"
                    13 -> "K"
                    else -> rank.toString()
                }
        return "$suit${rankS}的$name"
    }

}