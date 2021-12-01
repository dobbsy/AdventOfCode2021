package de.tobiasdoetzer.aoc2021

import java.io.File

fun part1(fileName: String): Int {
    val depths = File("app/src/main/kotlin/de/tobiasdoetzer/aoc2021", fileName).readLines().map(String::toInt)
    var retVal = 0
    for (i in 1..depths.lastIndex) {
        if (depths[i] > depths[i - 1]) retVal++
    }

    return retVal
}

fun part2(fileName: String): Int {
    val depths = File("app/src/main/kotlin/de/tobiasdoetzer/aoc2021", fileName).readLines().map(String::toInt)

    val windowDepths = mutableListOf<Int>()

    for (i in 0..depths.lastIndex - 2) {
        windowDepths += depths[i] + depths[i + 1] + depths[i + 2]
    }

    return windowDepths.zipWithNext().count { (first, second) ->
        second > first
    }
}

fun main() {
    println("The solution for part 1 is: ${part1("day1_input.txt")}")
    println("The solution for part 2 is: ${part2("day1_input.txt")}")
}
