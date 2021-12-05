package de.tobiasdoetzer.aoc2021

private fun part1(input: List<String>): Int {
    val ventMap = VentMap()

    for (s in input) {
        val (p1String, p2String) = s.split(" -> ")
        val (p1X, p1Y) = p1String.split(',').map(String::toInt)
        val (p2X, p2Y) = p2String.split(',').map(String::toInt)
        if (p1X == p2X || p1Y == p2Y) {
            ventMap.addLine(Point(p1X, p1Y), Point(p2X, p2Y))
        }
    }

    return ventMap.map.filter { it.value > 1 }.values.size
}

private fun part2(input: List<String>): Int {
    val ventMap = VentMap()

    for (s in input) {
        val (p1String, p2String) = s.split(" -> ")
        ventMap.addLine(Point.createPointFromString(p1String), Point.createPointFromString(p2String))
    }

    return ventMap.map.filter { it.value > 1 }.values.size
}

private fun main() {
    val input = readInput("day5_input.txt")

    println("The solution of part 1 is: ${part1(input)}")
    println("The solution of part 2 is: ${part2(input)}")
}

data class Point(val x: Int, val y: Int) {
    companion object {
        fun createPointFromString(s: String): Point {
            val (xString, yString) = s.split(',')
            return Point(xString.toInt(), yString.toInt())
        }
    }
}

/**
 * Represents a map of hydrothermal vents.
 *
 * The [map] property contains the actual map,
 * the [addLine] function is used to add a new line of vents to the map.
 */
class VentMap {
    private val _map: MutableMap<Point, Int> = HashMap()

    /**
     * Returns a read-only map where the keys are [Point]s through which
     * at least one line of hydrothermal vents goes, and the keys is the
     * total number of lines of vents that go through the respective Point.
     */
    val map: Map<Point, Int>
        get() = _map

    /**
     * Adds a line of vents between [p1] and [p2] in the map.
     *
     * Only call this once for each line of vents detected,
     * otherwise the same line will be added multiple times
     */
    fun addLine(p1: Point, p2: Point) {
        for (point in getPointsOnLine(p1, p2)) {
            _map[point] = _map[point]?.inc() ?: 1
        }
    }

    // Returns a set of all points on the line between p1 and p2
    // Only horizontal, vertical and diagonal at an angle of 45° can be computed
    private fun getPointsOnLine(p1: Point, p2: Point): Set<Point> {
        val retVal = mutableSetOf<Point>()
        if (p1.x == p2.x) {
            if (p1.y > p2.y) {
                for (i in p2.y..p1.y) retVal += Point(p1.x, i)
            } else {
                for (i in p1.y..p2.y) retVal += Point(p1.x, i)
            }
        } else if (p1.y == p2.y) {
            if (p1.x > p2.x) {
                for (i in p2.x..p1.x) retVal += Point(i, p1.y)
            } else {
                for (i in p1.x..p2.x) retVal += Point(i, p1.y)
            }
        } else {
            when {
                p1.x > p2.x && p1.y > p2.y -> for (i in 0..(p1.x - p2.x)) {
                    retVal += Point(p1.x - i, p1.y - i)
                }
                p1.x > p2.x -> for (i in 0..(p1.x - p2.x)) {  // p1.y < p2.y must also be true
                    retVal += Point(p1.x - i, p1.y + i)
                }
                // from her on forward, p1.x is guaranteed to be smaller than p2.x
                p1.y > p2.y -> for (i in 0..(p2.x - p1.x)) {
                    retVal += Point(p1.x + i, p1.y - i)
                }
                else -> for (i in 0..(p2.x - p1.x)) {
                    retVal += Point(p1.x + i, p1.y + i)
                }
            }
        }

        return retVal
    }
}
