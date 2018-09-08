package com.seu.vo.table.common

import com.seu.vo.card.struct.Card

class Deck(allCards: ArrayList<Card>) {
    private lateinit var cardList: ArrayList<Card>

    init {
        loadCards(allCards)
    }

    public fun loadCards(newCards: ArrayList<Card>) {
        shuffle(newCards)
    }

    private fun shuffle(cards: ArrayList<Card>) {
        cardList = cards.shuffled() as ArrayList
    }

    public fun watchTop(amount: Int): ArrayList<Card> {
        if (amount > cardList.size)
            throw Exception("获取卡牌异常-牌堆没有足够卡牌")
        return cardList.subList(0, amount - 1) as ArrayList<Card>
    }

    public fun watchDown(amount: Int): ArrayList<Card> {
        if (amount > cardList.size)
            throw Exception("获取卡牌异常-牌堆没有足够卡牌")
        return cardList.reversed()?.subList(0, amount - 1) as ArrayList<Card>
    }

    public fun getTop(amount: Int): ArrayList<Card> {
        if (amount > cardList.size)
            throw Exception("获取卡牌异常-牌堆没有足够卡牌")

        val cards = cardList.take(amount)
        cardList = try {
            cardList.drop(amount) as ArrayList<Card>
        } catch (e: Exception) {
            ArrayList()
        }
        return cards as ArrayList<Card>
    }

    public fun getDown(amount: Int): ArrayList<Card> {
        if (amount > cardList.size)
            throw Exception("获取卡牌异常-牌堆没有足够卡牌")

        val cards = cardList.takeLast(amount)

        cardList = try {
            cardList.dropLast(amount) as ArrayList<Card>
        } catch (e: Exception) {
            ArrayList()
        }

        return cards as ArrayList<Card>
    }

    public fun addTop(cards: ArrayList<Card>) {
        cardList.addAll(0,cards)
    }

    public fun addDown(cards: ArrayList<Card>) {
        cardList.addAll(cards)
    }

    fun debugMessage(): String {
        val builder = StringBuilder()
        cardList.forEach { c -> builder.append(c.toString()).append("\n") }
        return builder.toString()
    }
}