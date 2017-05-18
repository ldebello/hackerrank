package net.euler.problem040

object Solution {

  def main(args: Array[String]): Unit = {
    val d1 = computeValue(1)
    val d10 = computeValue(10)
    val d100 = computeValue(100)
    val d1000 = computeValue(1000)
    val d10000 = computeValue(10000)
    val d100000 = computeValue(100000)
    val d1000000 = computeValue(1000000)

    println(d1 * d10 * d100 * d1000 * d10000 * d100000 * d1000000)
  }

  def computeValue(position: Int) = {
    var regionStart = 0
    var regionEnd = 9
    var regionMaxNumber = 10
    var regionIndex = 1
    var regionSize = 10
    var prevRegionSize = 0

    while (position >= regionSize) {
      regionStart = regionMaxNumber
      regionEnd = (regionMaxNumber * 10) - 1
      regionMaxNumber = regionMaxNumber * 10
      regionIndex += 1
      prevRegionSize = prevRegionSize + regionSize
      regionSize = (regionMaxNumber - regionStart) * regionIndex
    }

    val regionInternalOffset = position - prevRegionSize

    val numberInRegion = regionInternalOffset / regionIndex

    val totalNumber = numberInRegion + regionStart

    val numberPosition = regionInternalOffset % regionIndex

    val result = String.valueOf(totalNumber).charAt(numberPosition)

    Character.getNumericValue(result)
  }
}
