package de.tobiasdoetzer.aoc2021

import java.io.File

fun readInput(fileName: String): List<String> =
    File("app/src/main/kotlin/de/tobiasdoetzer/aoc2021", fileName).readLines()

fun readInputAsInts(fileName: String): List<Int> =
    readInput(fileName).map(String::toInt)