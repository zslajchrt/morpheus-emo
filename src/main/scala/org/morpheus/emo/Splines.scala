package org.morpheus.emo

import org.morpheus.emo.v3.FeatureWarp

/**
  * Created by zslajchrt on 15/11/15.
  */
object Splines {

  def transform(delta: (Float, Float), bindings: Map[Int, Float]): Map[Int, (Float, Float)] = {
    for ((pointId, weight) <- bindings) yield {
      (pointId, (weight * delta._1, weight * delta._2))
    }
  }

}

case class SplineStyle(color: String = "black", strokeWidth: Int = 1)

abstract class Spline(val name: String, style: SplineStyle) extends ((Float) => (Float, Float)) {
  def render(): String = {
    s"""
       <path id="${name}" d="${points()}" stroke=\"${style.color}\" stroke-width=\"${style.strokeWidth}\" fill="none"/>
     """
  }

  protected def points(): String

  def transform(deltas: Map[Int, (Float, Float)]): Spline

  def moveRel(delta: (Float, Float)): Spline

  def mirror(): Spline

  def changeStyle(newStyle: SplineStyle): Spline

  protected def movePointRel(point: (Float, Float), delta: (Float, Float)): (Float, Float) = (point._1 + delta._1, point._2 + delta._2)

  protected def mirrorPoint(point: (Float, Float)): (Float, Float) = (-point._1, point._2)


}

case class Line(nm: String, p1: (Float, Float), p2: (Float, Float), style: SplineStyle = SplineStyle()) extends Spline(nm, style) {
  override protected def points(): String = s"M${p1._1},${p1._2} L${p2._1},${p2._2}"

  override def apply(t: Float): (Float, Float) = {
    (p1._1 * (1 - t) + p2._1 * t,
      p1._2 * (1 - t) + p2._2 * t)
  }

  override def transform(deltas: Map[Int, (Float, Float)]): Line = {
    val c1 = deltas.getOrElse(0, (0f, 0f))
    val c2 = deltas.getOrElse(1, (0f, 0f))

    Line(name, (p1._1 + c1._1, p1._2 + c1._2), (p2._1 + c2._1, p2._2 + c2._2))
  }

  override def moveRel(delta: (Float, Float)): Spline = Line(nm, movePointRel(p1, delta), movePointRel(p2, delta), style)

  override def mirror(): Spline = Line(nm, mirrorPoint(p1), mirrorPoint(p2), style)

  override def changeStyle(newStyle: SplineStyle): Spline = copy(style = newStyle)
}

case class QBezier(nm: String, p1: (Float, Float), p2: (Float, Float), p3: (Float, Float), style: SplineStyle = SplineStyle()) extends Spline(nm, style) {
  override protected def points(): String = s"M${p1._1},${p1._2} Q${p2._1},${p2._2} ${p3._1},${p3._2}"

  override def apply(t: Float): (Float, Float) = {
    (p1._1 * (1 - t) * (1 - t) + p2._1 * 2 * t * (1 - t) + p3._1 * t * t,
      p1._2 * (1 - t) * (1 - t) + p2._2 * 2 * t * (1 - t) + p3._2 * t * t)
  }

  override def transform(deltas: Map[Int, (Float, Float)]): QBezier = {
    val c1 = deltas.getOrElse(0, (0f, 0f))
    val c2 = deltas.getOrElse(1, (0f, 0f))
    val c3 = deltas.getOrElse(2, (0f, 0f))

    QBezier(name, (p1._1 + c1._1, p1._2 + c1._2), (p2._1 + c2._1, p2._2 + c2._2), (p3._1 + c3._1, p3._2 + c3._2))
  }

  override def moveRel(delta: (Float, Float)): Spline = QBezier(nm, movePointRel(p1, delta), movePointRel(p2, delta), movePointRel(p3, delta), style)

  override def mirror(): Spline = QBezier(nm, mirrorPoint(p1), mirrorPoint(p2), mirrorPoint(p3), style)

  override def changeStyle(newStyle: SplineStyle): Spline = copy(style = newStyle)
}

case class CBezier(nm: String, p1: (Float, Float), p2: (Float, Float), p3: (Float, Float), p4: (Float, Float), style: SplineStyle = SplineStyle()) extends Spline(nm, style) {
  override protected def points(): String = s"M${p1._1},${p1._2} C${p2._1},${p2._2} ${p3._1},${p3._2} ${p4._1},${p4._2}"

  override def apply(t: Float): (Float, Float) = {
    (p1._1 * (1 - t) * (1 - t) * (1 - t) + p2._1 * 3 * t * (1 - t) * (1 - t) + p3._1 * 3 * t * t * (1 - t) + p4._1 * t * t * t,
      p1._2 * (1 - t) * (1 - t) * (1 - t) + p2._2 * 3 * t * (1 - t) * (1 - t) + p3._2 * 3 * t * t * (1 - t) + p4._2 * t * t * t)
  }

  override def transform(deltas: Map[Int, (Float, Float)]): CBezier = {
    val c1 = deltas.getOrElse(0, (0f, 0f))
    val c2 = deltas.getOrElse(1, (0f, 0f))
    val c3 = deltas.getOrElse(2, (0f, 0f))
    val c4 = deltas.getOrElse(3, (0f, 0f))

    CBezier(name, (p1._1 + c1._1, p1._2 + c1._2), (p2._1 + c2._1, p2._2 + c2._2), (p3._1 + c3._1, p3._2 + c3._2),
      (p4._1 + c4._1, p4._2 + c4._2))
  }

  override def moveRel(delta: (Float, Float)): Spline = CBezier(nm, movePointRel(p1, delta), movePointRel(p2, delta), movePointRel(p3, delta), movePointRel(p4, delta), style)

  override def mirror(): Spline = CBezier(nm, mirrorPoint(p1), mirrorPoint(p2), mirrorPoint(p3), mirrorPoint(p4), style)

  override def changeStyle(newStyle: SplineStyle): Spline = copy(style = newStyle)
}
