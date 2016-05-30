package org.morpheus.square

import org.morpheus._
import org.morpheus.Morpheus._

/**
  * Created by zslajchrt on 22/01/16.
  */
object SquareRectangleProblem {

  type ModelType = Rectangle with \?[Square]
  val model = parse[ModelType](true)
  val strategy = {
    val strat = maskFull[ModelType](model)(rootStrategy(model), {
      case None => Some(0)
      case Some(r) => if (r.width == r.height)
        Some(1)
      else
        Some(0)
    })
    strict(strat)
  }

  val recognizer = singleton(model, strategy)
  val rect = recognizer.~

  def main(args: Array[String]) {

    def printSquare(): Unit = select[Square](rect) match {
      case None => println("not a square")
      case Some(sq) => println(sq.side)
    }

    rect.width = 100f
    rect.height = 100f
    rect.remorph

    printSquare()

    select[Square](rect) match {
      case None =>
      case Some(sq) => sq.side = 400f
    }

    printSquare()

    rect.width = 200f
    rect.height = 300f
    rect.remorph

    printSquare()

  }

}


@fragment
trait Rectangle {
  var width: Float = 0f
  var height: Float = 0f
}

@fragment
trait Square {
  this: Rectangle =>

  def side: Float = width

  def side_=(s: Float) = {
    width = s
    height = s
  }
}

