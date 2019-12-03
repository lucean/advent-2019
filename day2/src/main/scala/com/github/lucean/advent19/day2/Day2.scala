package com.github.lucean.advent19.day2

import scala.annotation.tailrec
import scala.io.Source

object Day2 {

  def main(args: Array[String]): Unit = {
    val source = Source.fromResource("day2.txt")

    val opCodes = source.getLines().mkString.split(",").map(_.toInt)

    val newState = updateState(opCodes(0), opCodes(1), opCodes(2), opCodes(3), opCodes)

    println("Solution: " + newState(0))

    source.close
  }

  def updateState(opCode: Int, pos1: Int, pos2: Int, target: Int, state: Array[Int]): Array[Int] = {

    @tailrec
    def iter(index: Int, opCode: Int, pos1: Int, pos2: Int, target: Int, state: Array[Int]): Array[Int] ={
      opCode match {
        case 1 =>
          val updated = state.updated(target, state(pos1) + state(pos2))
          val newIndex = index + 4
          if (updated(newIndex) == 99) updated else iter(newIndex, updated(newIndex), updated(newIndex + 1), updated(newIndex + 2), updated(newIndex + 3), updated)
        case 2 =>
          val updated = state.updated(target, state(pos1) * state(pos2))
          val newIndex = index + 4
          if (updated(newIndex) == 99) updated else iter(newIndex, updated(newIndex), updated(newIndex + 1), updated(newIndex + 2), updated(newIndex + 3), updated)
        case 99 =>
          state
      }
    }

    iter(0, state(0), state(1), state(2), state(3), state)
  }
}
