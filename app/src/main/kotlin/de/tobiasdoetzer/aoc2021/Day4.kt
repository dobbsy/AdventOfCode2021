package de.tobiasdoetzer.aoc2021

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

private fun main() {
    val input = readInput("day4_input.txt")
    val drawnNumbers = input[0].split(',').map(String::toInt)

    val boardsPart1 = mutableListOf<BingoBoard>()
    val boardsPart2 = mutableListOf<BingoBoard>()

    for (i in (2..(input.lastIndex - 4)) step 6) {
        val board = BingoBoard.createBoard(input.subList(i, i+ 5))
        boardsPart1 += board
        boardsPart2 += board.copy()
    }

    println("The solution of part 1 is: ${part1(boardsPart1, drawnNumbers)}")
    println("The solution of part 2 is: ${part2(boardsPart2, drawnNumbers)}")
}

private fun part1(boards: List<BingoBoard>, drawnNumbers: List<Int>): Int {
    for (number in drawnNumbers) {
        boards.forEach { it.markNumber(number) }
        boards.forEach {
            if (it.isWinningBoard()) return it.calculateScore()
        }
    }

    throw IllegalArgumentException("There was no winning board")
}

private fun part2(boards: MutableList<BingoBoard>, drawnNumbers: List<Int>): Int {
    for (number in drawnNumbers) {
        boards.forEach { it.markNumber(number) }
        if (boards.size > 1) boards.removeAll { it.isWinningBoard() }
        else if (boards[0].isWinningBoard()) return boards[0].calculateScore()
    }

    throw IllegalArgumentException()
}

class BingoBoard private constructor(private val rowsAndColumns: Array<MutableSet<Int>>) {
    private var lastNumber: Int? = null

    fun markNumber(number: Int) {
        rowsAndColumns.forEach { it.remove(number) }
        lastNumber = number
    }

    fun copy() = BingoBoard(rowsAndColumns)

    fun isWinningBoard() = rowsAndColumns.any { it.isEmpty() }

    fun calculateScore(): Int {
        if (!isWinningBoard()) throw IllegalStateException("Cannot calculate score if the board has not won")

        val remainingNumbers = mutableSetOf<Int>()
        rowsAndColumns.forEach { remainingNumbers += it }

        return remainingNumbers.sum() * lastNumber!!
    }

    companion object {
        fun createBoard(numberStrings: List<String>): BingoBoard {
            val numbers = numberStrings.map { it.trim().split("\\s+".toRegex()) }.map { list -> list.map(String::toInt) }
            val rowsAndColumns = Array<MutableSet<Int>>(10) { mutableSetOf() }

            for (i in 0 until 5) {
                for (j in 0 until 5) {
                    rowsAndColumns[i] += numbers[i][j]
                    rowsAndColumns[j + 5] += numbers[i][j]
                }
            }

            return BingoBoard(rowsAndColumns)
        }
    }
}