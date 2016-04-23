package com.hacerkrank.algorithms.graph_theory.journey_to_the_moon

object Solution {

  case class Country(id: Int, members: Set[Int]) { }

  def main(args: Array[String]) {
    val lines = io.Source.stdin.getLines()
    val header = lines.next()
    val maxMember = header.split(" ")(0).toLong
    val total = header.split(" ")(1)

    var members:Set[Int] = Set()
    var countries = List[Country]()

    lines.take(total.toInt).zipWithIndex.foreach {
      line => {
        val current = line._1.split(" ")
        val index = line._2

        val leftMember = current(0).toInt
        val rightMember = current(1).toInt

        members = members + leftMember + rightMember

        val leftMaybeCountry = findCountry(countries, leftMember)
        val rightMaybeCountry = findCountry(countries, rightMember)

        countries = (leftMaybeCountry, rightMaybeCountry) match {
          case (None, None) => createCountry(index, Set(leftMember, rightMember)) :: countries
          case (Some(c), None) => includeMissingMember(countries, c, rightMember)
          case (None, Some(c)) => includeMissingMember(countries, c, leftMember)
          case (Some(x), Some(y)) => mergeMembers(countries, index, x, y)
        }
      }
    }

    val totalOfCombinations:Long = maxMember * (maxMember - 1) / 2

    val pairsFromSameCountry = countries.foldLeft(0)((acc, country: Country) => {
      acc + country.members.size * (country.members.size - 1) / 2
    })

    print(totalOfCombinations - pairsFromSameCountry)
  }

  def createCountry(id: Int, members: Set[Int]) = {
    Country(id, members)
  }

  def findCountry(countries:List[Country], member: Int) = {
    countries.find(c => c.members.contains(member))
  }

  def removeCountry(countries:List[Country], country: Country) = {
    countries.filterNot(c => c.id.equals(country.id))
  }

  def includeMissingMember(countries:List[Country], country: Country, memberToInclude: Int) = {
    createCountry(country.id, country.members + memberToInclude) :: removeCountry(countries, country)
  }

  def mergeMembers(countries:List[Country], newId: Int, leftCountry: Country, rightCountry: Country) = {
    createCountry(newId, leftCountry.members ++ rightCountry.members) :: removeCountry(removeCountry(countries, leftCountry), rightCountry)
  }
}