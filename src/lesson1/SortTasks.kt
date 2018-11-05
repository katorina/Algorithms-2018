@file:Suppress("UNUSED_PARAMETER")

package lesson1

import java.io.File
import java.util.*
import kotlin.collections.HashMap

/**
 * Сортировка времён
 *
 * Простая
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
 * каждый на отдельной строке. Пример:
 *
 * 13:15:19
 * 07:26:57
 * 10:00:03
 * 19:56:14
 * 13:15:19
 * 00:40:31
 *
 * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
 * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
 *
 * 00:40:31
 * 07:26:57
 * 10:00:03
 * 13:15:19
 * 13:15:19
 * 19:56:14
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortTimes(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сортировка адресов
 *
 * Средняя
 *
 * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
 * где они прописаны. Пример:
 *
 * Петров Иван - Железнодорожная 3
 * Сидоров Петр - Садовая 5
 * Иванов Алексей - Железнодорожная 7
 * Сидорова Мария - Садовая 5
 * Иванов Михаил - Железнодорожная 7
 *
 * Людей в городе может быть до миллиона.
 *
 * Вывести записи в выходной файл outputName,
 * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
 * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
 *
 * Железнодорожная 3 - Петров Иван
 * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
 * Садовая 5 - Сидоров Петр, Сидорова Мария
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortAddresses(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сортировка температур
 *
 * Средняя
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
 * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
 * Например:
 *
 * 24.7
 * -12.6
 * 121.3
 * -98.4
 * 99.5
 * -12.6
 * 11.0
 *
 * Количество строк в файле может достигать ста миллионов.
 * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
 * Повторяющиеся строки сохранить. Например:
 *
 * -98.4
 * -12.6
 * -12.6
 * 11.0
 * 24.7
 * 99.5
 * 121.3
 */
// Трудоемкость: T = O(n)
// Ресурсоемкость: R = O(n)
fun sortTemperatures(inputName: String, outputName: String) {
    val min = -2730
    val max = 5000
    val count = IntArray(max - min + 1)
    val input = Scanner(File(inputName))
    while (input.hasNextLine()) {
        val temp = ((input.nextLine()).toDouble() * 10).toInt()
        count[temp - min]++
    }
    File(outputName).printWriter().use {
        for (i in 0 until count.size) {
            var index = count[i]
            if (index == 0) continue
            while (index > 0) {
                it.println((i + min).toDouble() / 10)
                index--
            }
        }
    }
    return print(count)
}

/**
 * Сортировка последовательности
 *
 * Средняя
 * (Задача взята с сайта acmp.ru)
 *
 * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
 *
 * 1
 * 2
 * 3
 * 2
 * 3
 * 1
 * 2
 *
 * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
 * а если таких чисел несколько, то найти минимальное из них,
 * и после этого переместить все такие числа в конец заданной последовательности.
 * Порядок расположения остальных чисел должен остаться без изменения.
 *
 * 1
 * 3
 * 3
 * 1
 * 2
 * 2
 * 2
 */
// Трудоемкость: T = O(n)
// Ресурсоемкость: R = O(n)
fun sortSequence(inputName: String, outputName: String) {
    val map = HashMap<Int, Int>()
    val allValues = mutableListOf<Int>()
    val input = Scanner(File(inputName))
    var maxValue = 0
    var maxKey = 0
    var currentValue: Int
    while (input.hasNextLine()) {
        val tmp = input.nextLine().toInt()
        allValues.add(tmp)
        if (map.containsKey(tmp)) {
            currentValue = map.getValue(tmp) + 1
            map[tmp] = currentValue
            when {
                currentValue > maxValue -> {
                    maxValue = currentValue
                    maxKey = tmp
                }
                currentValue == maxValue -> maxKey = Math.min(tmp, maxKey)
            }
        }
        else map[tmp] = 1
    }
    File(outputName).printWriter().use {
        for (i in 0 until allValues.size) {
            val temp = allValues[i]
            if (temp == maxKey) continue
            it.println(temp)
        }
        for (i in 1..maxValue) it.println(maxKey)
    }
    return print(allValues)
}

/**
 * Соединить два отсортированных массива в один
 *
 * Простая
 *
 * Задан отсортированный массив first и второй массив second,
 * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
 * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
 *
 * first = [4 9 15 20 28]
 * second = [null null null null null 1 3 9 13 18 23]
 *
 * Результат: second = [1 3 4 9 9 13 15 20 23 28]
 */
fun <T : Comparable<T>> mergeArrays(first: Array<T>, second: Array<T?>) {
    TODO()
}

