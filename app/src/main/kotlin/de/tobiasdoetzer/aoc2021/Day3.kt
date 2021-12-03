package de.tobiasdoetzer.aoc2021

private fun part1(report: List<String>): Int {
    var gammaText = ""
    var epsilonText = ""
    val limit = report.size / 2

    for (i in report[0].indices) {
        val numberOf0s = report.count { it[i] == '0' }
        if (numberOf0s > limit) { // more than half of the numbers in report have a 0 at position i
            gammaText += 0
            epsilonText += 1
        } else { // less than half of the numbers in report have a 1 ato position i
            gammaText += 1
            epsilonText += 0
        }
    }

    return gammaText.toInt(2) * epsilonText.toInt(2)
}

private fun part2(report: List<String>): Int {
    var oxygenGenList = report
    var scrubberRatingList = report

    for (i in report[0].indices) {
        if (oxygenGenList.size == 1) break
        val limit = oxygenGenList.size / 2.0

        val numberOf0s = oxygenGenList.count { it[i] == '0' }
        oxygenGenList = when {
            numberOf0s > limit -> oxygenGenList.filter { it[i] == '0' } // more than half of the numbers have a 0 at position i
            numberOf0s < limit -> oxygenGenList.filter { it[i] == '1' } // less than half of the numbers have a 1 ato position i
            else -> oxygenGenList.filter { it[i] == '1' } // Exactly half the numbers have a 0/1 at position i
        }
    }

    for (i in report[0].indices) {
        if (scrubberRatingList.size == 1) break
        val limit = scrubberRatingList.size / 2.0

        val numberOf0s = scrubberRatingList.count { it[i] == '0' }
        scrubberRatingList = when {
            numberOf0s > limit -> scrubberRatingList.filter { it[i] == '1' } // more than half of the numbers have a 0 at position i
            numberOf0s < limit -> scrubberRatingList.filter { it[i] == '0' } // less than half of the numbers have a 1 ato position i
            else -> scrubberRatingList.filter { it[i] == '0' } // Exactly half the numbers have a 0/1 at position i
        }
    }

    return oxygenGenList[0].toInt(2) * scrubberRatingList[0].toInt(2)
}

private fun main() {
    val input = readInput("day3_input.txt")
    println("The solution of part 1 is: ${part1(input)}")
    println("The solution of part 2 is: ${part2(input)}")
}