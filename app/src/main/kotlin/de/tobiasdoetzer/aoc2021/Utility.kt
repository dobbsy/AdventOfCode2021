package de.tobiasdoetzer.aoc2021

import java.io.File

const val FILE_PREFIX = "app/src/main/kotlin/de/tobiasdoetzer/aoc2021"

fun readInput(fileName: String): List<String> =
    File(FILE_PREFIX, fileName).readLines()

fun readInputAsInts(fileName: String): List<Int> =
    readInput(fileName).map(String::toInt)

fun readInputSingleLineAsInts(fileName: String, delimiter: String = ","): List<Int> =
    readInput(fileName)[0].split(delimiter).map(String::toInt)

fun <E> MutableList<E>.addIfNotNull(item: E?) {
    if (item != null) add(item)
}