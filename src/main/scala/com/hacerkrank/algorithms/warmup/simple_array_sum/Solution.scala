package com.hacerkrank.algorithms.warmup.simple_array_sum

object Solution {

  def main(args: Array[String]) {
    val scanner = new java.util.Scanner (System.in)
    val length = scanner.nextInt()
    var total = 0
    for(index <- 0 until length) {
      total = total + scanner.nextInt()
    }
    println(total)
  }
}
