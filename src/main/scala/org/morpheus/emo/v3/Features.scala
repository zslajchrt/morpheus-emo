package org.morpheus.emo.v3

import java.io.{File, FileWriter}

import org.morpheus.Morpheus._
import org.morpheus._
import org.morpheus.emo.{CBezier, Spline, SplineStyle}

/**
  * Created by zslajchrt on 20/12/15.
  */
object Features {

  type FeaturesType[Base, Eyebrow, UpperLid, LowerLid, UpperLipJoiner, UpperLip, LowerLip, LowerLipJoiner] = {
    type Features = Base * \?[Eyebrow] * \?[UpperLid * \?[LowerLid]] * \?[UpperLipJoiner * \?[UpperLip * \?[LowerLip * \?[LowerLipJoiner]]]]
  }

  // 1 *      2         *  3 * 5


  val defaultFeatureSplines = List(
    CBezier("Eyebrow", (150f, 160f), (119f, 130f), (80f, 125f), (32f, 153f)),
    CBezier("UpperLid", (125f, 210f), (105f, 174f), (60f, 178f), (43f, 212f)),
    CBezier("LowerLid", (43f, 212f), (56f, 212f), (80f, 235f), (125f, 210f)),
    CBezier("UpperLipJoiner", (-10f, 399f), (0f, 399f), (15f, 399f), (30f, 400f)),
    CBezier("UpperLip", (30f, 400f), (43f, 410f), (45f, 400f), (60f, 399f)),
    CBezier("LowerLip", (60f, 399f), (50f, 399f), (40f, 400f), (25f, 401f)),
    CBezier("LowerLipJoiner", (25f, 401f), (12f, 400f), (0f, 400f), (-10f, 401f)))
  val defaultFeatureSplinesMirror = defaultFeatureSplines.map(_.mirror())

  val FeatureNames = Map("eyebrow" -> "Eyebrow", "upperlid" -> "UpperLid", "lowerlid" -> "LowerLid",
    "upperlipjoiner" -> "UpperLipJoiner", "upperlip" -> "UpperLip", "lowerlip" -> "LowerLip",
    "lowerlipjoiner" -> "LowerLipJoiner")

  def flushSvg(splinesRight: List[Spline], outFile: File) = {
    val splinesLeft = splinesRight.map(_.mirror())
    val splinesLeftRight = (splinesLeft ::: splinesRight).map(_.changeStyle(SplineStyle(color = "blue", strokeWidth = 3)))
    val filteredDefSplines = defaultFeatureSplines.filterNot(defSpline => splinesRight.exists(_.name == defSpline.name))
    val filteredDefSplinesMirror = defaultFeatureSplinesMirror.filterNot(defSpline => splinesRight.exists(_.name == defSpline.name))
    val splines = (filteredDefSplines ::: filteredDefSplinesMirror ::: splinesLeftRight).map(_.moveRel((300f, 50f)))

    val svg: String =
      s"""
        <svg xmlns="http://www.w3.org/2000/svg"
             xmlns:xlink="http://www.w3.org/1999/xlink"
             width="100%" height="100%">
             <image x="100" y="0" width="400" height="400" xlink:href="/Users/zslajchrt/Sites/meta/hairdo.svg" />
             ${splines.map(_.render()).mkString}
        </svg>
      """
    val fwr = new FileWriter(outFile)
    try fwr.write(svg) finally fwr.close()
  }

}

case class FeatureWarp(deltas: Map[Int, (Float, Float)]) {

  def update(newDeltas: Map[Int, (Float, Float)]): FeatureWarp = {
    var joined = deltas

    for (nd <- newDeltas) {
      val x: (Int, (Float, Float)) = deltas.get(nd._1) match {
        case None =>
          (nd._1, nd._2)
        case Some((cur1, cur2)) =>
          (nd._1, (nd._2._1 + cur1, nd._2._2 + cur2))
      }
      joined += x
    }

    new FeatureWarp(joined)
  }

}


trait FeatureBaseInit {
  val warps: Map[String, FeatureWarp]
}

case class FeatureBaseInitData(warps: Map[String, FeatureWarp]) extends FeatureBaseInit

@fragment
trait FeatureBase extends dlg[FeatureBaseInit] {
  def collectSplines: List[Spline] = Nil
}

@fragment
trait EyebrowFragment {
  this: FeatureBase =>

  lazy val eyebrowSpline =
    CBezier("eyebrow", (150f, 160f), (119f, 130f), (80f, 125f), (32f, 153f)).
      transform(warps("eyebrow").deltas)
}

@fragment
@wrapper
@nodepscheck
trait EyebrowWrapper extends FeatureBase {
  this: EyebrowFragment =>
  override def collectSplines: List[Spline] = eyebrowSpline :: super.collectSplines
}

@fragment
trait UpperLidFragment {
  this: FeatureBase =>

  lazy val upperlidSpline = CBezier("upperlid", (125f, 210f), (105f, 174f), (60f, 178f), (43f, 212f)).
    transform(warps("upperlid").deltas)

}

@fragment
@wrapper
@nodepscheck
trait UpperLidWrapper extends FeatureBase {
  this: UpperLidFragment =>
  override def collectSplines: List[Spline] = upperlidSpline :: super.collectSplines
}

@fragment
trait LowerLidFragment {
  this: FeatureBase =>

  lazy val lowerlidSpline = CBezier("lowerlid", (43f, 212f), (56f, 212f), (80f, 235f), (125f, 210f)).
    transform(warps("lowerlid").deltas)

}

@fragment
@wrapper
@nodepscheck
trait LowerLidWrapper extends FeatureBase {
  this: LowerLidFragment with UpperLidFragment =>

  override def collectSplines: List[Spline] = {
    val uls = upperlidSpline
    lowerlidSpline.copy(p1 = uls.p4, p4 = uls.p1) :: super.collectSplines
  }
}

@fragment
trait UpperLipJoinerFragment {
  this: FeatureBase =>

  lazy val upperlipjoinerSpline = CBezier("upperlipjoiner", (-10f, 399f), (0f, 399f), (15f, 399f), (30f, 400f)).
    transform(warps("upperlipjoiner").deltas)

}

@fragment
@wrapper
@nodepscheck
trait UpperLipJoinerWrapper extends FeatureBase {
  this: UpperLipJoinerFragment =>
  override def collectSplines: List[Spline] = upperlipjoinerSpline :: super.collectSplines
}


@fragment
trait UpperLipFragment {
  this: FeatureBase =>

  lazy val upperlipSpline = CBezier("upperlip", (30f, 400f), (43f, 410f), (45f, 400f), (60f, 399f)).
    transform(warps("upperlip").deltas)

}

@fragment
@wrapper
@nodepscheck
trait UpperLipWrapper extends FeatureBase {
  this: UpperLipFragment with UpperLipJoinerFragment =>
  override def collectSplines: List[Spline] = {
    val newP1 = upperlipjoinerSpline.p4
    upperlipSpline.copy(p1 = newP1) :: super.collectSplines
  }
}

@fragment
trait LowerLipFragment {
  this: FeatureBase =>

  lazy val lowerlipSpline = CBezier("lowerlip", (60f, 399f), (50f, 399f), (40f, 400f), (25f, 401f)).
    transform(warps("lowerlip").deltas)

}

@fragment
@wrapper
@nodepscheck
trait LowerLipWrapper extends FeatureBase {
  this: LowerLipFragment with UpperLipFragment =>

  override def collectSplines: List[Spline] = {
    val newP1 = upperlipSpline.p4
    lowerlipSpline.copy(p1 = newP1) :: super.collectSplines
  }
}

@fragment
trait LowerLipJoinerFragment {
  this: FeatureBase =>

  lazy val lowerlipjoinerSpline = CBezier("lowerlipjoiner", (25f, 401f), (12f, 400f), (0f, 400f), (-10f, 401f)).
    transform(warps("lowerlipjoiner").deltas)

}

@fragment
@wrapper
@nodepscheck
trait LowerLipJoinerWrapper extends FeatureBase {
  this: LowerLipJoinerFragment with LowerLipFragment =>
  override def collectSplines: List[Spline] = {
    val newP1 = lowerlipSpline.p4
    lowerlipjoinerSpline.copy(p1 = newP1) :: super.collectSplines
  }
}
