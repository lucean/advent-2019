package com.github.lucean.advent19.day1

import scala.io.Source

object Day1 {

  def main(args: Array[String]): Unit = {
    val source = Source.fromResource("day1.txt")

    val total = source.getLines().map(line => fuelCalc(line.toInt)).sum

    println(total)

    source.close
  }

  def fuelCalc(mass: Int): Int = {
    Math.floorDiv(mass, 3) - 2
  }
}
