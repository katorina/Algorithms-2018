@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import java.util.*

/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */
fun Graph.findEulerLoop(): List<Graph.Edge> {
    TODO()
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */
// Трудоемкость: T = O(V + E)
// Ресурсоемкость: R = O(V^2)
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    val root = this.vertices.first()
    val rootIs = countChildren(true, root)
    val rootIsNot = countChildren(false, root)

    return if (rootIs.size > rootIsNot.size) rootIs else rootIsNot
}

fun Graph.countChildren(rootIsFirst: Boolean, root: Graph.Vertex): Set<Graph.Vertex> {
    val outChildren = mutableSetOf<Graph.Vertex>()
    if (rootIsFirst) outChildren.add(root)
    var lastSize = 1
    var addOrNot = rootIsFirst
    val currentChildren = mutableSetOf<Graph.Vertex>()
    currentChildren.add(root)

    while (currentChildren.isNotEmpty()) {
        for (i in 0 until lastSize) {
            this.getNeighbors(currentChildren.first()).forEach { if (!outChildren.contains(it)) currentChildren.add(it) }
            currentChildren.remove(currentChildren.first())
        }
        lastSize = currentChildren.size
        addOrNot = !addOrNot
        if (addOrNot) currentChildren.forEach { outChildren.add(it) }
    }

    return outChildren
}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
// Трудоемкость: T = O(V!)
// Ресурсоемкость: R = O(V)
fun Graph.longestSimplePath(): Path {
    val paths = ArrayDeque<Path>()

    for (path in this.vertices) paths.addLast(Path(path))
    var maxPath = paths.last()

    while (paths.isNotEmpty()) {
        val currentPath = paths.last()
        paths.removeLast()
        val currPathNeighbors = this.getNeighbors(currentPath.vertices.last())
        while (currPathNeighbors.isNotEmpty()) {
            val currentNeighbor = currPathNeighbors.last()
            if (currentPath.contains(currentNeighbor)) {
                if (currentPath > maxPath) maxPath = currentPath
            } else paths.addLast(Path(currentPath, this, currentNeighbor))
            currPathNeighbors.remove(currentNeighbor)
        }
    }

    return maxPath
}

