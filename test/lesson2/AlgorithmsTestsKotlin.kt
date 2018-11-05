package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class AlgorithmsTestsKotlin : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring2() {
        assertEquals("и", longestCommonSubstring("диметилалкилбензиламмонийхлорид", "и"))
        assertEquals("б", longestCommonSubstring("б", "бактериологический"))
    }


    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { calcPrimesNumber(it) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> baldaSearcher(inputName, words) }
    }

    @Test
    @Tag("Hard")
    fun testBalda() {
        assertEquals(setOf("КАРТА", "КОМПАС"), baldaSearcher("input/balda_in2.txt",
                setOf("КАРТА", "ПАРАМЕТР", "ПОРТФЕЛЬ", "КОМПАС")))
    }
}