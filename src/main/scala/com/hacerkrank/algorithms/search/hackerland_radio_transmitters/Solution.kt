package com.hacerkrank.algorithms.search.hackerland_radio_transmitters

import java.util.TreeSet

fun main(args : Array<String>) {
    val values = readLine()!!.split(" ")
    val houses = readLine()!!.split(" ")

    val numberOfHouses = values[0].toInt()
    val range = values[1].toInt()

    val data = TreeSet<Int>()

    for (index in 0..numberOfHouses - 1) {
        val currentHouse = houses[index].toInt()
        data.add(currentHouse)
    }

    var counter = 0
    var nextTarget = data.first()

    data.forEach {
        if (it >= nextTarget) {
            val optimalTarget = it + range
            val realTarget = data.floor(optimalTarget)
            nextTarget = realTarget + range + 1
            counter++
        }
    }

    print(counter)
}