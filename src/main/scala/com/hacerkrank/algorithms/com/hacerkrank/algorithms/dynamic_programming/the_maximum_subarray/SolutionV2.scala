package com.hacerkrank.algorithms.com.hacerkrank.algorithms.dynamic_programming.the_maximum_subarray

/*
 * Esta version usa Kadane's algorithm
 */
object SolutionV2 {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      lines.next().toInt // Number of elements
      val line = lines.next()
      val values = line.split(" ")

      var maxSoFar = Integer.MIN_VALUE
      var maxEndingHere = 0

      var maxNonContiguous = 0

      values.foreach {
        textValue => {
          val value = textValue.toInt

          maxEndingHere = Math.max(value, maxEndingHere + value)
          maxSoFar = Math.max(maxEndingHere, maxSoFar)

          if (value > 0) {
            maxNonContiguous += value
          }
        }
      }

      if (maxSoFar < 0) {
        maxNonContiguous = maxSoFar
      }

      println(maxSoFar + " " + maxNonContiguous)
    }
  }
}