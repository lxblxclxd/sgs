package test.seu.vo.card

import com.seu.vo.card.common.CardSuit
import org.junit.Ignore
import org.junit.Test

class CardSuitTest{

    @Test
    @Ignore
    fun toStringTest(){
        val cs: CardSuit = CardSuit.SPADE
        println(cs)
    }
}