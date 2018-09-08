package com.seu.vo.card.common

public enum class CardSuit {
    SPADE, HEART, DIAMOND, CLUB;

    override fun toString(): String {
        return when(this){
            SPADE -> "黑桃"
            HEART -> "红桃"
            DIAMOND -> "方片"
            CLUB -> "梅花"
        }
    }
}