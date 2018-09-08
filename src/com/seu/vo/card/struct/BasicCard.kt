package com.seu.vo.card.struct

import com.seu.vo.card.common.CardSuit

class BasicCard(cardSuit: CardSuit, cardRank: Int, cardName: String)
    : Card(cardSuit, cardRank, cardName) {

}