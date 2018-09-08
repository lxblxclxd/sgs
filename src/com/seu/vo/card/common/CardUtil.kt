package com.seu.vo.card.common

import com.seu.vo.card.struct.BasicCard
import com.seu.vo.card.struct.Card

class CardUtil {

    companion object {
        public fun generateCard(cardInfo: CardInfo): Card {
            val type: String = cardInfo.type
            val rank: Int = cardInfo.rank
            if(rank <= 0 || rank > 13) {
                throw Exception("卡牌创建异常-卡牌点数值异常")
            }
            when {
                type.startsWith("basic") -> return BasicCard(cardInfo.suit,cardInfo.rank,cardInfo.name)
                else -> throw Exception("卡牌创建异常-无法解析的卡牌类型")
            }
        }
    }
}