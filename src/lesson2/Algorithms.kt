@file:Suppress("UNUSED_PARAMETER")

package lesson2

import javafx.scene.control.Cell
import java.io.File

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    TODO()
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
// Трудоемкость: T = O(mn)
// Ресурсоемоксть: R = O(mn)
fun longestCommonSubstring(first: String, second: String): String {
    var length: Int
    var startIndex = 0
    var maxLength = 0
    for (i in 0 until first.length) {
        length = findSubstring(i, first, second)
        if (length > maxLength) {
            startIndex = i
            maxLength = length
        }
    }
    if (maxLength == 0) return ""
    return first.substring(startIndex, startIndex + maxLength)
}

fun findSubstring(index: Int, fWord: String, sWord: String): Int {
    var count = 0
    var maxCount = 0
    for (i in 0 until sWord.length) {
        for (k in i until sWord.length) {
            if (count >= fWord.length || index + count >= fWord.length) break
            if (fWord[index + count] != sWord[k]) {
                if (count > maxCount) maxCount = count
                break
            } else count++
        }
        if (count > maxCount) maxCount = count
        count = 0
    }
    return maxCount
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    TODO()
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
data class Cell(val letter: Char, var flag: Boolean)

fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    val board = mutableListOf<lesson2.Cell>()
    var x = 0
    var lines = 0
    for (line in File(inputName).readLines()) {
        for (char in line) {
            if (char == ' ') continue
            board.add(Cell(char, false))
            x++
        }
        lines++
    }
    x /= lines
    val result = mutableSetOf<String>()
    for (word in words) {
        val flag = findWord(word, board, x)
        if (flag) result.add(word)
    }
    return result
}

fun findWord(word: String, board: MutableList<lesson2.Cell>, x: Int): Boolean {
    for (i in 0 until board.size) {
        if (board[i].letter == word[0]) {
            board[i].flag = true
            if (findLetters(x, i, word, board, 1)) {
                return true
            }
            for (k in 0 until board.size) {
                board[k].flag = false
            }
        }
    }
    return false
}

fun findLetters(x: Int, position: Int, word: String, board: MutableList<lesson2.Cell>, index: Int): Boolean {
    if (index == word.length) return true
    if (position + x < board.size) {
        if (board[position + x].letter == word[index]) {
            board[position].flag = true
            if (!board[position + x].flag && findLetters(x, position + x, word, board, index + 1))
                return true
        }
    }
    if (position - x > 0) {
        if (board[position - x].letter == word[index]) {
            board[position].flag = true
            if (!board[position - x].flag && findLetters(x, position - x, word, board, index + 1))
                return true
        }
    }
    if ((position + 1) % x != 0) {
        if (board[position + 1].letter == word[index]) {
            board[position].flag = true
            if (!board[position + 1].flag && findLetters(x, position + 1, word, board, index + 1))
                return true
        }
    }
    if (position - 1 >= 0 && (position % x) != 0) {
        if (board[position - 1].letter == word[index]) {
            board[position].flag = true
            if (!board[position - 1].flag && findLetters(x, position - 1, word, board, index + 1))
                return true
        }
    }
    return false
}