package com.hacerkrank.algorithms.search.sherlock_and_array

object SolutionV2 {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      val numberOfElements = lines.next().toInt

      if (numberOfElements == 1) {
        lines.next()
        println("YES")
      } else {
        val line = lines.next()
        val values = line.split(" ")

        var sum = 0L

        for (valueIndex <- 0 until values.length) {
          sum = sum + values(valueIndex).toLong
        }

        var isSumPossible = false
        var partialSum = 0L

        for (valueIndex <- 0 until values.length) {
          val value = values(valueIndex).toLong
          if (sum - partialSum - value == partialSum) {
            isSumPossible = true
          }
          partialSum = partialSum + value
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