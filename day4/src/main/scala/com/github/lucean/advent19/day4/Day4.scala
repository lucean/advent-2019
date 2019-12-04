package com.github.lucean.advent19.day4

object Day4 {

  def main(args: Array[String]): Unit = {
    val source = 357253 to 892942

    val solution = source.map(_.toString).filter(adjacentDigits).count(increasingChars)

    println(solution)
  }

  def adjacentDigits(input: String): Boolean = {
    input.toSeq.sliding(2).exists(str => str.head == str.last)
  }

  def increasingChars(input: String): Boolean = {
    input == input.toSeq.sorted.unwrap
  }

}
