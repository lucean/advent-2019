package com.github.lucean.advent19.day4

object Day4_2 {

  def main(args: Array[String]): Unit = {
    val source = 357253 to 892942

    val solution = source.map(_.toString).filter(adjacentDigits).count(increasingChars)

    println(solution)
  }

  def adjacentDigits(input: String): Boolean = {
    val adjPair = input.toSeq.sliding(2).exists(str => str.head == str.last)

    val onlyAdjPair = input.groupMapReduce(identity)(_ => 1)(_ + _).values.exists(_ == 2)

    adjPair && onlyAdjPair
  }

  def increasingChars(input: String): Boolean = {
    input == input.toSeq.sorted.unwrap
  }
}
