package test.seu.vo.table.area

import com.seu.vo.card.common.CardInfo
import com.seu.vo.card.common.CardSuit
import com.seu.vo.card.common.CardUtil
import com.seu.vo.table.common.Deck
import org.junit.Test

import org.junit.Ignore

class DeckTest {

    companion object {
        val slash = CardInfo(CardSuit.SPADE, 8, "杀", "basic")
        val dodge = CardInfo(CardSuit.DIAMOND, 2, "闪", "basic")
        val card1 = CardUtil.generateCard(slash)
        val card2 = CardUtil.generateCard(dodge)
        val cardList = arrayListOf(card1, card1, card1, card2, card2, card2)
    }

    @Ignore
    @Test
    fun shuffleTest() {
        val deck = Deck(cardList)
        print(deck.debugMessage())
    }
}