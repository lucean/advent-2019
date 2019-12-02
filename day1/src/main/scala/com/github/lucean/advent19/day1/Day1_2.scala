package com.github.lucean.advent19.day1

import scala.annotation.tailrec
import scala.io.Source

object Day1_2 {

  def main(args: Array[String]): Unit = {
    val source = Source.fromResource("day1.txt")

    val total = source.getLines().map(line => fuelCalc(line.toInt)).sum

    println(total)

    source.close
  }

  def fuelCalc(mass: Int): Int = {

    @tailrec
    def iter(x: Int, result: Int): Int = {
      val mass = Math.floorDiv(x, 3) - 2

      if (mass <= 0) {
        result
      } else {
        iter(mass, result + mass)
      }
    }

    iter(mass, 0)
  }
}
