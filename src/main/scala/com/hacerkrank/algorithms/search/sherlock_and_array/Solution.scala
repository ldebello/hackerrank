package com.hacerkrank.algorithms.search.sherlock_and_array

object Solution {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      val numberOfElements = lines.next().toInt

      if (numberOfElements == 1) {
        lines.next()
        println("YES")
      } else {
        val partialSums = new Array[Long](numberOfElements)

        val line = lines.next()
        val values = line.split(" ")

        partialSums(0) = values(0).toLong

        for (valueIndex <- 1 until values.length) {
          partialSums(valueIndex) = partialSums(valueIndex - 1) + values(valueIndex).toLong
        }

        val lastIndex = values.length - 1

        var previousCurrentSum = 0L
        var currentSum = values(lastIndex).toLong
        var isSumPossible = false

        for (valueIndex <- lastIndex - 1 to 0 by -1) {
          previousCurrentSum = currentSum
          currentSum = previousCurrentSum + values(valueIndex).toLong
          if (currentSum == partialSums(valueIndex)) {
            if (previousCurrentSum == partialSums(valueIndex - 1)) {
              isSumPossible = true
            }
          }
        }

        if (isSumPossible) {
          println("YES")
        } else {
          println("NO")
        }
      }
    }
  }
}