package com.github.lucean.advent19.day2

import com.github.lucean.advent19.day2.Day2.updateState

import scala.io.Source

object Day2_2 {

  def main(args: Array[String]): Unit = {
    val source = Source.fromResource("day2.txt")

    val opCodes = source.getLines().mkString.split(",").map(_.toInt)

    // Horrible
    for (a <- 0 to 99) {
      for (b <- 0 to 99) {

        opCodes(1) = a
        opCodes(2) = b

        val newState = updateState(opCodes(0), opCodes(1), opCodes(2), opCodes(3), opCodes)

        if (newState(0) == 19690720) {
          println("Completed!")
          println("a = " + a)
          println("b = " + b)
          println("Solution: " + (100 * a.+(b)))
        }
      }
    }

    source.close
  }

}
