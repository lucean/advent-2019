package com.github.lucean.advent19.day3

import scala.io.Source

object Day3_2 {

  def main(args: Array[String]): Unit = {
    val source = Source.fromResource("day3.txt")

    val wires = source.getLines().map(wire => process(wire)).reduce((a, b) => combine(a, b))

    println(wires.mkString(","))

    val solution = wires.map {
      case (_, (_, d)) => d
      case _ => 0
    }.filter(res => res != 0).min

    println(solution)

    source.close
  }

  def process(wire: String): Map[(Int, Int), (Int, Int)] = {
    wire.split(",").map(parseInstruction).foldLeft((0, 0), Map[(Int, Int), (Int, Int)]()) {
      (acc, i) => {
        val sourceX = acc._1._1
        val sourceY = acc._1._2

        val destX = sourceX + (i.distance * i.dx)
        val destY = sourceY + (i.distance * i.dy)

        val map = scala.collection.mutable.Map[(Int, Int), (Int, Int)]() ++ acc._2

        if (i.dx != 0) {
          for (x <- sourceX to destX by i.dx) {
            map.put((x, sourceY), (1, map.size))
          }
        }

        if (i.dy != 0) {
          for (y <- sourceY to destY by i.dy) {
            map.put((sourceX, y), (1, map.size))
          }
        }

        ((destX, destY), Map.from(map))
      }
    }._2
  }

  def parseInstruction(instruction: String): Instruction = {
    val direction = instruction.charAt(0)
    val distance = instruction.substring(1).toInt

    direction match {
      case 'U' => Up(distance)
      case 'D' => Down(distance)
      case 'L' => Left(distance)
      case 'R' => Right(distance)
      case _ => throw new RuntimeException
    }
  }

  def combine(a: Map[(Int, Int), (Int, Int)], b: Map[(Int, Int), (Int, Int)]): Map[(Int, Int), (Int, Int)] = {
    a.collect { case (k, v) if b.contains(k) => (k, (2, v._2 + b(k)._2)) }
  }

  case class Up(override val distance: Int) extends Instruction(0, 1, distance)

  case class Down(override val distance: Int) extends Instruction(0, -1, distance)

  case class Left(override val distance: Int) extends Instruction(-1, 0, distance)

  case class Right(override val distance: Int) extends Instruction(1, 0, distance)

  abstract class Instruction(_dx: Int, _dy: Int, _distance: Int) {
    def dx: Int = _dx

    def dy: Int = _dy

    def distance: Int = _distance
  }

}
