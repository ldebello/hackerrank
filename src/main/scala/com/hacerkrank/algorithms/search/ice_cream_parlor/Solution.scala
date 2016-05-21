package com.hacerkrank.algorithms.search.ice_cream_parlor

object Solution {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      val dollars = lines.next().toInt
      val numberOfElements = lines.next().toInt

      val line = lines.next()
      val values = line.split(" ")

      var index = 0
      var solution = false
      var result = (-1, -1)
      val data:scala.collection.mutable.Map[Int,Int] = scala.collection.mutable.Map()

      while(index < numberOfElements && !solution) {
        val currentValue = values(index).toInt

        val remainder = dollars - currentValue

        if (data.contains(currentValue)) {
          solution = true
          result = (data(currentValue), index + 1)
        } else {
          data += (remainder -> (index + 1))
        }

        index = index + 1
      }

      println(result._1 + " " + result._2)
    }
  }
}