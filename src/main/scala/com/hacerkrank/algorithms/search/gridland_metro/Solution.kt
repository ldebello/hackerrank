package com.hacerkrank.algorithms.search.gridland_metro

import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap

fun main(args : Array<String>) {
    val data = readLine()!!.split(" ")
    val rows = data[0].toLong()
    val columns = data[1].toLong()
    val numberOfTrains = data[2].toInt()
    val gridSize = rows * columns

    val busyRows = HashMap<Int, NavigableMap<Int, Int>>()
    for (index in 0..numberOfTrains - 1) {
        val line = readLine()!!.split(" ")
        val row = line[0].toInt()
        val startColumn = line[1].toInt()
        val endColumn = line[2].toInt()

        val intervalsByRow = busyRows.computeIfAbsent(row, Function { _ -> TreeMap() })

        mergeIntervals(intervalsByRow, startColumn, endColumn)
    }

    var busyColumns = 0L
    busyRows.forEach {
        it.value.forEach {
            busyColumns += (it.value - it.key) + 1
        }
    }

    println(gridSize - busyColumns)
}

fun mergeIntervals(intervalsByRow: NavigableMap<Int, Int>, start: Int, end:Int) {
    val startEntry = intervalsByRow.floorEntry(start)
    val endEntry = intervalsByRow.floorEntry(end)

    if (startEntry == endEntry) {
        val entry = startEntry
        if (entry != null) {
            if (entry.key <= end && start <= entry.value) {
                intervalsByRow.remove(entry.key)
                mergeIntervals(intervalsByRow, Math.min(entry.key, start), Math.max(entry.value, end))
            } else {
                intervalsByRow.put(start, end)
            }
        } else {
            intervalsByRow.put(start, end)
        }
    } else if (startEntry != endEntry) {
        if (startEntry != null) {
            var newStart = start
            var newEnd = end
            if (startEntry.key <= end && start <= startEntry.value) {
                intervalsByRow.remove(startEntry.key)
                newStart = Math.min(startEntry.key, newStart)
                newEnd = Math.max(startEntry.value, newEnd)
            }
            intervalsByRow.remove(endEntry.key)
            mergeIntervals(intervalsByRow, Math.min(newStart, endEntry.key), Math.max(newEnd, endEntry.value))
        } else {
            intervalsByRow.remove(endEntry.key)
            mergeIntervals(intervalsByRow, Math.min(endEntry.key, start), Math.max(endEntry.value, end))
        }
    }
}