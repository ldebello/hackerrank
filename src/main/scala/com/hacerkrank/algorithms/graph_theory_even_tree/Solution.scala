package com.hacerkrank.algorithms.graph_theory_even_tree

import scala.collection.mutable

object Solution {

  class Graph[T] {
    val adjacencyList: mutable.Map[T, List[T]] = mutable.Map()

    def addEdge(source: T, target: T) {
      val sourceNeighbors: List[T] = adjacencyList.getOrElse(source, List())
      val targetNeighbors: List[T] = adjacencyList.getOrElse(target, List())


      adjacencyList(source) = target :: sourceNeighbors
      adjacencyList(target) = source :: targetNeighbors
    }

    def getAdjacencies(source: T) = {
      adjacencyList.getOrElse(source, List())
    }

    override def toString = {
      val sb: StringBuilder = new StringBuilder()

      adjacencyList.foreach {
        entry => {
          sb.append(entry._1)
          sb.append(" ")
          sb.append("->")
          sb.append(" ")
          sb.append(entry._2)
          sb.append("\n")
        }
      }
      sb.toString()
    }
  }

  def main(args: Array[String]) {
    val lines = io.Source.stdin.getLines()
    val header = lines.next().split(" ")

    val vertices = header(0).toInt
    val graph = new Graph[Int]

    var startVertex:Int = 1
    for (index <- 0 until vertices - 1) {
      val line = lines.next().split(" ")

      graph.addEdge(line(1).toInt, line(0).toInt)
      startVertex = line(1).toInt
    }

    val result = traverse(graph, startVertex)

    println(result._2)
  }

  def traverse[T](graph: Graph[T], startVertex: T): (Int, Int) = {
    val visited = mutable.Set[T]()
    traverse(graph, startVertex, visited)
  }

  def traverse[T](graph: Graph[T], startVertex: T, visited: mutable.Set[T]): (Int, Int) = {
    visited.add(startVertex)

    var edgeToCut = 0
    var totalCount = 1
    for (adjacency <- graph.getAdjacencies(startVertex)) {
      if (!visited.contains(adjacency)) {
        var count = 0
        val pair = traverse(graph, adjacency, visited)
        count = pair._1
        totalCount = totalCount + count

        edgeToCut = edgeToCut + pair._2

        if (count % 2 == 0) {
          edgeToCut = edgeToCut + 1
        }
      }
    }
    (totalCount, edgeToCut)
  }
}