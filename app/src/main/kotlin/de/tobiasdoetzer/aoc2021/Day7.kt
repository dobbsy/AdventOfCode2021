package de.tobiasdoetzer.aoc2021

import kotlin.math.abs
import kotlin.math.min

private fun part1(positions: List<Int>): Int {
    var result = Int.MAX_VALUE
    for (i in positions.minOrNull()!!..positions.maxOrNull()!!) {
        val fuelUsed = positions.sumOf { abs(i - it) }
        result = min(result, fuelUsed)
    }
    return result
}

private fun part2(positions: List<Int>): Int {
    var result = Int.MAX_VALUE
    for (i in positions.minOrNull()!!..positions.maxOrNull()!!) {
        val fuelUsed = positions.sumOf {
            val distance = abs(i - it)
            (distance * (distance + 1)) / 2
        }
        result = min(result, fuelUsed)
    }
    return result
}

private fun main() {
    val input = readInputSingleLineAsInts("day7_input.txt")

    println("The solution of part 1 is: ${part1(input)}")
    println("The solution of part 2 is: ${part2(input)}")
}