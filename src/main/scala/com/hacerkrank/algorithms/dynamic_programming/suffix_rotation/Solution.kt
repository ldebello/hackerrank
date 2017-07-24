package com.hacerkrank.algorithms.dynamic_programming.suffix_rotation

import java.io.Serializable
import java.util.*

fun main(args : Array<String>) {
    val numberOfGames = readLine()!!.toInt()

    for(game in 0..numberOfGames - 1) {
        val word = readLine()!!
        val lettersCount = word.fold(TreeMap<Char, Int>()) { acc, key -> acc.put(key, acc.getOrDefault(key, 0) + 1); acc }
        val letters = word.toCharArray()

        var index = 0
        var numberOfMoves = 0

        lettersCount.forEach {
          for (ocurrence in 0..it.value - 1) {
            if (letters[index] == it.key) {
                index++
            } else {
                numberOfMoves++
                val data = analyzeLetters(index, letters, it.key)
                val moreSuitableIndex = findMoreSuitableIndex(data, it.key)
                val numberOfShifts = letters.size - moreSuitableIndex

                for(iteration in 0..numberOfShifts - 1) {
                    val temp = letters[letters.size - 1]
                    for (pos in letters.size - 2 downTo  index) {
                        letters[pos + 1] = letters[pos]
                    }
                    letters[index] = temp
                }
                index++
            }
          }
        }
        println(numberOfMoves)
    }
}

fun analyzeLetters(initialIndex: Int, letters: CharArray, requiredLetter: Char): List<Info<Char, Int, Int, Int, String>> {
    val data:MutableList<Info<Char, Int, Int, Int, String>> = mutableListOf()

    for (pos in initialIndex..letters.size - 1) {
        var repeatedLetters = 0
        var contiguousLetters = 0
        if (letters[pos] == requiredLetter) {
            var prevLetter = requiredLetter
            var nextRequiredLetter = requiredLetter + 1
            var condition = false
            var nextPosition = computeNextPosition(initialIndex, pos, letters.size)
            var message = "" + requiredLetter

            do {
                if (letters[nextPosition] == requiredLetter && prevLetter == requiredLetter) {
                    repeatedLetters++
                }
                if (letters[nextPosition] == nextRequiredLetter || letters[nextPosition] == prevLetter) {
                    contiguousLetters++
                    prevLetter = letters[nextPosition]
                    nextRequiredLetter = prevLetter + 1
                    condition = true
                    message += letters[nextPosition]
                } else {
                    condition = false
                }
                nextPosition = computeNextPosition(initialIndex, nextPosition, letters.size)
            } while(condition)
            data.add(Info(requiredLetter, pos, repeatedLetters, contiguousLetters, message))
        }
    }

    return data
}

fun computeNextPosition(initialPosition:Int, currentPosition: Int, size: Int): Int {
    if (currentPosition < size - 1) {
        return currentPosition + 1
    } else {
        return initialPosition
    }
}

/**
 * If there is multiples options we need to use the more repeated letters with less contiguous order
 */
fun  findMoreSuitableIndex(data: List<Info<Char, Int, Int, Int, String>>, requiredLetter: Char): Int {
    var index = -1
    var maxRepeatedLetterCount = 0
    var minContiguousLetterCount = Int.MAX_VALUE
    var lastInfo:Info<Char, Int, Int, Int, String>? = null
    data.filter { it.letter == requiredLetter }.forEach {
        if (it.repeatedCounter >= maxRepeatedLetterCount) {
            if (it.repeatedCounter == maxRepeatedLetterCount) {
                if (it.contiguousCounter <= minContiguousLetterCount) {
                    lastInfo = it
                    index = it.index
                    maxRepeatedLetterCount = it.repeatedCounter
                    minContiguousLetterCount = it.contiguousCounter

                }
            } else {
                lastInfo = it
                index = it.index
                maxRepeatedLetterCount = it.repeatedCounter
                minContiguousLetterCount = it.contiguousCounter
            }
        }
    }

    return index
}

public data class Info<out A, out B, out C, out D, out E>(
        public val letter: A,
        public val index: B,
        public val repeatedCounter: C,
        public val contiguousCounter : D,
        public val message : E
) : Serializable {

    public override fun toString(): String = "($letter, $index, $repeatedCounter, $contiguousCounter, $message)"
}