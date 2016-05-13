package com.hacerkrank.algorithms.warmup.diagonal_difference

object Solution {

  def main(args: Array[String]) {
    val scanner = new java.util.Scanner(System.in)
    val dimm = scanner.nextInt()
    val matrix = Array.ofDim[Int](dimm, dimm)
    for(i <- 0 until dimm) {
      for(j <- 0 until dimm) {
        matrix(i)(j) = scanner.nextInt()
      }
    }

    val primaryDiagonal = calculatePrimaryDiagonal(matrix)
    val secondaryDiagonal = calculateSecondaryDiagonal(matrix)
    val result = Math.abs(primaryDiagonal - secondaryDiagonal)

    println(result)
  }

  def calculatePrimaryDiagonal(matrix: Array[Array[Int]]) = {
    var sum = 0
    for(i <- matrix.indices) {
      sum += matrix(i)(i)
    }
    sum
  }

  def calculateSecondaryDiagonal(matrix: Array[Array[Int]]) = {
    var sum = 0
    for(i <- matrix.indices) {
      sum += matrix(i)(matrix.length - 1 - i)
    }
    sum
  }
}