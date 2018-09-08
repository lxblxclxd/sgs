package com.seu.vo.card.common;

/**
 * 实体卡牌类
 */
public class CardInfo(cardSuit: CardSuit, cardRank: Int, cardName: String, cardType: String) {

    /* 花色 */
    val suit: CardSuit = cardSuit

    /* 点数 */
    val rank: Int = cardRank

    /* 牌名 */
    val name: String = cardName

    /* 种类 */
    /**
     * basic 基本
     * strategy/common, delay 锦囊/普通，延时
     * equipment/weapon,armor,mount,treasure 装备/武器，防具，坐骑，宝物
     */
    val type: String = cardType
}
