package de.tobiasdoetzer.aoc2021

import java.lang.IllegalArgumentException

private fun part1(instructions: List<String>): Int {
    var horizontalPos = 0
    var depth = 0

    instructions.forEach {
        val (direction, _magnitude) = it.split(' ')
        val magnitude = _magnitude.toInt()

        when (direction) {
            "forward" -> horizontalPos += magnitude
            "down" -> depth += magnitude
            "up" -> depth -= magnitude
            else -> throw IllegalArgumentException("Could not interpret direction \"$direction\"")
        }
    }

    return depth * horizontalPos
}

private fun part2(instructions: List<String>): Int {
    var horizontalPos = 0
    var depth = 0
    var aim = 0

    instructions.forEach {
        val (direction, _magnitude) = it.split(' ')
        val magnitude = _magnitude.toInt()

        when(direction) {
            "forward" -> {
                horizontalPos += magnitude
                depth += aim * magnitude
            }
            "up" -> aim -= magnitude
            "down" -> aim += magnitude
            else -> throw IllegalArgumentException("Could not interpret direction \"$direction\"")
        }
    }

    return depth * horizontalPos
}

private fun main() {
    val input = readInput("day2_input.txt")
    println("The solution of part 1 is: ${part1(input)}")
    println("The solution of part 2 is: ${part2(input)}")
}