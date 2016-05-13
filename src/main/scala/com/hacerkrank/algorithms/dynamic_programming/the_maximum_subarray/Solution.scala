package com.hacerkrank.algorithms.dynamic_programming.the_maximum_subarray

object Solution {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      lines.next().toInt // Number of elements
      val line = lines.next()
      val values = line.split(" ")

      var maxContiguous = 0
      var lastMaxContiguous = 0
      var maxNonContiguous = 0
      values.foreach {
        textValue => {
          val value = textValue.toInt

          if (maxContiguous == 0) {
            maxContiguous = value
            if (lastMaxContiguous < maxContiguous) {
              lastMaxContiguous = maxContiguous
            }
          } else if (maxContiguous < 0) {
            if (maxContiguous < value) {
              maxContiguous = value
            }
          } else if (maxContiguous + value > 0) {
            if (maxContiguous > maxContiguous + value) {
              if (lastMaxContiguous < maxContiguous) {
                lastMaxContiguous = maxContiguous
              }
            }
            maxContiguous += value
          } else {
            if (lastMaxContiguous < maxContiguous) {
              lastMaxContiguous = maxContiguous
            }
            maxContiguous = 0
          }

          if (value > 0) {
            maxNonContiguous += value
          }
        }
      }

      if (lastMaxContiguous == 0 || lastMaxContiguous < maxContiguous) {
        lastMaxContiguous = maxContiguous
      }

      if (lastMaxContiguous < 0) {
        maxNonContiguous = lastMaxContiguous
      }

      println(lastMaxContiguous + " " + maxNonContiguous)
    }
  }
}