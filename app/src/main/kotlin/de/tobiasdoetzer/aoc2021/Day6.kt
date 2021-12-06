package de.tobiasdoetzer.aoc2021

private fun part1(initialState: List<Lanternfish>): Int {
    val fishes = initialState.toMutableList()
    repeat(80) {
        for (i in fishes.indices) {
            val newFish = fishes[i].newDay()
            if (newFish != null) fishes += newFish
        }
    }

    return fishes.size
}

private fun part2(initialState: List<Int>): Int {
    TODO()
}

fun main() {
    val input = readInputSingleLineAsInts("day6_input.txt")
        .map { Lanternfish(it) }

    println("The result of part 1 is: ${part1(input)}")
}

class Lanternfish(private val initialTimer: Int) {
    private var timer = initialTimer

    fun newDay(): Lanternfish? {
        if (timer == 0) {
            timer = 6
            return Lanternfish(8)
        } else {
            timer--
            return null
        }
    }
}