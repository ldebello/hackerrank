package com.hacerkrank.algorithms.greedy.sherlock_and_minimax

import java.util.TreeMap;
import java.lang.Math;

object Solution {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfElements = lines.next().toInt
    val values = lines.next().split(" ")
    val range = lines.next().split(" ")

    val start = range(0).toInt
    val end = range(1).toInt

    val minForEachValue = scala.collection.mutable.Map[Int, Int]()
    for (value <- start to end) {
      minForEachValue(value) = Int.MaxValue
    }

    for (index <- 0 until numberOfElements) {
      for (entry <- minForEachValue) {
        minForEachValue(entry._1) = Math.min(Math.abs(values(index).toInt - entry._1), entry._2)
      }
    }

    val tree = new TreeMap[Int, Int]()
    for (entry <- minForEachValue) {
      val current = tree.get(entry._2)
      tree.put(entry._2, Math.min(entry._1, if (current != null) current.toInt else Integer.MAX_VALUE))
    }

    println(tree.lastEntry().getValue)
  }
}