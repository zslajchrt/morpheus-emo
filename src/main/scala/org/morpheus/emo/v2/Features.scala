//package org.morpheus.emo.v2
//
//import java.io.{File, FileWriter}
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.{SplineStyle, CBezier, Spline}
//
///**
//  * Created by zslajchrt on 20/12/15.
//  */
//object Features {
//  //type Features = Feature or Eyebrow or UpperLidFragment or UpperLid or LowerLid or LowerLipJoiner or LowerLipFragment or LowerLip or UpperLip or UpperLipJoiner or UpperLipJoinerFragment or UpperLipFragment
//  //type Features_ = Feature with \?[Eyebrow] with \?[UpperLid with (Unit | LowerLid)] with \?[UpperLipJoiner with (Unit | UpperLip with (Unit | LowerLip with (Unit | LowerLipJoiner)))]
//  type Features = FeatureBase with \?[Eyebrow with EyebrowWrapper] with \?[UpperLid with UpperLidWrapper
//    with \?[LowerLid with LowerLidWrapper] with \?[UpperLipJoiner with UpperLipJoinerWrapper with \?[UpperLip with UpperLipWrapper
//    with \?[LowerLip with LowerLipWrapper with \?[LowerLipJoiner with LowerLipJoinerWrapper]]]]]
//
//  //  type Features = Feature with /?[Eyebrow] with
//  //    /?[UpperLidFragment with UpperLid] with
//  //    /?[LowerLid] with
//  //    /?[LowerLipJoiner] with
//  //    /?[LowerLipFragment with LowerLip] with
//  //    /?[UpperLipFragment with UpperLip] with
//  //    /?[UpperLipJoinerFragment with UpperLipJoiner]
//
//  val defaultFeatureSplines = List(
//    CBezier("Eyebrow", (150f, 160f), (119f, 130f), (80f, 125f), (32f, 153f)),
//    CBezier("UpperLid", (125f, 210f), (105f, 174f), (60f, 178f), (43f, 212f)),
//    CBezier("LowerLid", (43f, 212f), (56f, 212f), (80f, 235f), (125f, 210f)),
//    CBezier("UpperLipJoiner", (-10f, 399f), (0f, 399f), (15f, 399f), (30f, 400f)),
//    CBezier("UpperLip", (30f, 400f), (43f, 410f), (45f, 400f), (60f, 399f)),
//    CBezier("LowerLip", (60f, 399f), (50f, 399f), (40f, 400f), (25f, 401f)),
//    CBezier("LowerLipJoiner", (25f, 401f), (12f, 400f), (0f, 400f), (-10f, 401f)))
//  val defaultFeatureSplinesMirror = defaultFeatureSplines.map(_.mirror())
//
//  val FeatureNames = Map("eyebrow" -> "Eyebrow", "upperlid" -> "UpperLid", "lowerlid" -> "LowerLid",
//    "upperlipjoiner" -> "UpperLipJoiner", "upperlip" -> "UpperLip", "lowerlip" -> "LowerLip",
//    "lowerlipjoiner" -> "LowerLipJoiner")
//
//  def flushSvg(splinesRight: List[Spline], outFile: File) = {
//    val splinesLeft = splinesRight.map(_.mirror())
//    val splinesLeftRight = (splinesLeft ::: splinesRight).map(_.changeStyle(SplineStyle(color = "blue", strokeWidth = 3)))
//    val filteredDefSplines = defaultFeatureSplines.filterNot(defSpline => splinesRight.exists(_.name == defSpline.name))
//    val filteredDefSplinesMirror = defaultFeatureSplinesMirror.filterNot(defSpline => splinesRight.exists(_.name == defSpline.name))
//    val splines = (filteredDefSplines ::: filteredDefSplinesMirror ::: splinesLeftRight).map(_.moveRel((300f, 50f)))
//
//    val svg: String =
//      s"""
//        <svg xmlns="http://www.w3.org/2000/svg"
//             xmlns:xlink="http://www.w3.org/1999/xlink"
//             width="100%" height="100%">
//             <image x="100" y="0" width="400" height="400" xlink:href="/Users/zslajchrt/Sites/meta/hairdo.svg" />
//             ${splines.map(_.render()).mkString}
//        </svg>
//      """
//    val fwr = new FileWriter(outFile)
//    try fwr.write(svg) finally fwr.close()
//  }
//
//}
//
//import Features._
//
//class Deltas[S <: Spline](val spline: S) {
//  var dlt = Map.empty[Int, (Float, Float)]
//
//  def update(newDeltas: Map[Int, (Float, Float)]): Unit = {
//    for (nd <- newDeltas) {
//      val x: (Int, (Float, Float)) = dlt.get(nd._1) match {
//        case None =>
//          (nd._1, nd._2)
//        case Some((cur1, cur2)) =>
//          (nd._1, (nd._2._1 + cur1, nd._2._2 + cur2))
//      }
//      dlt += x
//    }
//  }
//
//  def freshSpline(): S = spline.transform(dlt).asInstanceOf[S]
//}
//
//@fragment
//trait FeatureBase {
//  def collectSplines: List[Spline] = Nil
//}
//
//@fragment
//trait Eyebrow {
//  val eyebrowDeltas = new Deltas(CBezier("Eyebrow", (150f, 160f), (119f, 130f), (80f, 125f), (32f, 153f)))
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait EyebrowWrapper extends FeatureBase {
//  this: Eyebrow =>
//  override def collectSplines: List[Spline] = eyebrowDeltas.freshSpline() :: super.collectSplines
//}
//
//@fragment
//trait UpperLid {
//  val upperlidDeltas = new Deltas(CBezier("UpperLid", (125f, 210f), (105f, 174f), (60f, 178f), (43f, 212f)))
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait UpperLidWrapper extends FeatureBase {
//  this: UpperLid =>
//  override def collectSplines: List[Spline] = upperlidDeltas.freshSpline() :: super.collectSplines
//}
//
//@fragment
//trait LowerLid {
//  val lowerlidDeltas = new Deltas(CBezier("LowerLid", (43f, 212f), (56f, 212f), (80f, 235f), (125f, 210f)))
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait LowerLidWrapper extends FeatureBase {
//  this: LowerLid with UpperLid =>
//
//  override def collectSplines: List[Spline] = {
//    val uls = upperlidDeltas.freshSpline()
//    lowerlidDeltas.freshSpline().copy(p1 = uls.p4, p4 = uls.p1) :: super.collectSplines
//  }
//}
//
//@fragment
//trait UpperLipJoiner {
//  val upperlipjoinerDeltas = new Deltas(CBezier("UpperLipJoiner", (-10f, 399f), (0f, 399f), (15f, 399f), (30f, 400f)))
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait UpperLipJoinerWrapper extends FeatureBase {
//  this: UpperLipJoiner =>
//  override def collectSplines: List[Spline] = upperlipjoinerDeltas.freshSpline() :: super.collectSplines
//}
//
//
//@fragment
//trait UpperLip {
//  val upperlipDeltas = new Deltas(CBezier("UpperLip", (30f, 400f), (43f, 410f), (45f, 400f), (60f, 399f))) {
//    override def update(newDeltas: Map[Int, (Float, Float)]): Unit = {
//      println(s"Updating Upper Lip: ${newDeltas}")
//      super.update(newDeltas)
//    }
//  }
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait UpperLipWrapper extends FeatureBase {
//  this: UpperLip with UpperLipJoiner =>
//  override def collectSplines: List[Spline] = {
//    val newP1 = upperlipjoinerDeltas.freshSpline().p4
//    upperlipDeltas.freshSpline().copy(p1 = newP1) :: super.collectSplines
//  }
//}
//
//@fragment
//trait LowerLip {
//  val lowerlipDeltas = new Deltas(CBezier("LowerLip", (60f, 399f), (50f, 399f), (40f, 400f), (25f, 401f)))
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait LowerLipWrapper extends FeatureBase {
//  this: LowerLip with UpperLip =>
//  override def collectSplines: List[Spline] = {
//    val newP1 = upperlipDeltas.freshSpline().p4
//    lowerlipDeltas.freshSpline().copy(p1 = newP1) :: super.collectSplines
//  }
//}
//
//@fragment
//trait LowerLipJoiner {
//  val lowerlipjoinerDeltas = new Deltas(CBezier("LowerLipJoiner", (25f, 401f), (12f, 400f), (0f, 400f), (-10f, 401f)))
//}
//
//@fragment
//@wrapper
//@nodepscheck
//trait LowerLipJoinerWrapper extends FeatureBase {
//  this: LowerLipJoiner with LowerLip =>
//  override def collectSplines: List[Spline] = {
//    val newP1 = lowerlipDeltas.freshSpline().p4
//    lowerlipjoinerDeltas.freshSpline().copy(p1 = newP1) :: super.collectSplines
//  }
//}
