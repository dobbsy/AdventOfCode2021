package de.tobiasdoetzer.aoc2021

private fun part1(depths: List<Int>): Int {
    var retVal = 0
    for (i in 1..depths.lastIndex) {
        if (depths[i] > depths[i - 1]) retVal++
    }

    return retVal
}

private fun part2(depths: List<Int>): Int {

    val windowDepths = mutableListOf<Int>()

    for (i in 0..depths.lastIndex - 2) {
        windowDepths += depths[i] + depths[i + 1] + depths[i + 2]
    }

    return windowDepths.zipWithNext().count { (first, second) ->
        second > first
    }
}

private fun main() {
    val input = readInputAsInts("day1_input.txt")
    println("The solution for part 1 is: ${part1(input)}")
    println("The solution for part 2 is: ${part2(input)}")
}
