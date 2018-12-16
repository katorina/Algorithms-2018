@file:Suppress("UNUSED_PARAMETER")

package lesson6

import com.sun.org.apache.xerces.internal.util.IntStack
import lesson1.sortTemperatures
import java.io.File
import java.util.*

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    TODO()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
// Трудоемкость: T = O(N^2)
// Ресурсоемкость: R = O(N^2)
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty() || list.size == 1) return list

    val lists = mutableListOf<MutableList<Int>>()
    var currNumber: Int
    var currIndex = 0

    lists.add(mutableListOf(list[0]))
    while (currIndex < list.size) {
        currIndex++
        currNumber = list[currIndex - 1]
        for (i in 0 until lists.size) {
            val list1 = lists[i]
            if (currNumber > lists[i][lists[i].size - 1]) {
                lists.add(list1.toMutableList())
                list1.add(currNumber)
                lists[i] = list1
            }
        }
    }

    var maxLength = lists[0].size
    var result = lists[0]

    for (currList in lists.toSet()) {
        if (currList.size > maxLength) {
            maxLength = currList.size
            result = currList
        }
    }

    return result
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
// Трудоемкость: T = O(N^2 + M), где N - высота поля, а M - ширина
// Ресурсоемкость: R = O((NM)^3)
data class Path(val y: Int, val x: Int, val count: Int)

fun shortestPathOnField(inputName: String): Int {
    val numbers = mutableListOf<List<Int>>()
    for (line in File(inputName).readLines()) {
        numbers.add((line.split(' ').map { it.toInt() }))
    }

    val paths = mutableListOf<Path>()
    var result = Int.MAX_VALUE

    paths.add(Path(0, 0, numbers[0][0]))
    var x: Int
    var y: Int
    var preSize = paths.size

    loop@ while (true) {
        for (i in 0 until paths.size) {
            x = paths[i].x
            y = paths[i].y
            if (paths[i].x + 1 <= numbers[0].size - 1)
                paths.add(Path(y, x + 1, paths[i].count + numbers[y][x + 1]))
            if (paths[i].y + 1 <= numbers.size - 1)
                paths.add(Path(y + 1, x, paths[i].count + numbers[y + 1][x]))
            if (paths[i].x + 1 <= numbers[0].size - 1 && paths[i].y + 1 <= numbers.size - 1)
                paths.add(Path(y + 1, x + 1, paths[i].count + numbers[y + 1][x + 1]))
        }
        for (k in 0 until preSize) {
            paths.removeAt(0)
        }
        preSize = paths.size
        if (preSize == 0) break@loop
        for (l in 0 until paths.size) {
            if (paths[l].x == numbers[0].size - 1 && paths[l].y == numbers.size - 1 && paths[l].count < result)
                result = paths[l].count
        }
        var delIndex = 0
        while (delIndex < paths.size) {
            if (paths[delIndex].count > result) {
                paths.removeAt(delIndex)
                delIndex--
                preSize--
            }
            delIndex++
        }
    }

    return result
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5