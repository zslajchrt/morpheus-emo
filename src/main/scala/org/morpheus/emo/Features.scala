//package org.morpheus.emo
//
//import java.io._
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.Emotions.BASIC_MODEL_TYPE
//import org.morpheus.emo.Muscles._
//import Main._
//
///**
// * Created by zslajchrt on 11/11/15.
// */
//object Features {
//  //type MUSCLES_BINDING_TYPE = Feature with Eyebrow with UpperLidFragment with UpperLid with LowerLid with UpperLipJoinerFragment with UpperLipJoiner with UpperLipFragment with UpperLip with LowerLipFragment with LowerLip with LowerLipJoiner
//  type MUSCLES_BINDING_TYPE = Feature with /?[Eyebrow] with
//    /?[UpperLidFragment with UpperLid] with
//    /?[LowerLid] with
//    /?[LowerLipJoiner] with
//    /?[LowerLipFragment with LowerLip] with
//    /?[UpperLipFragment with UpperLip] with
//    /?[UpperLipJoinerFragment with UpperLipJoiner]
//
//
//  val defaultFeatureSplines = List(
//    CBezier("Eyebrow", (150f, 160f), (119f, 130f), (80f, 125f), (32f, 153f)),
//    CBezier("UpperLid", (125f, 210f), (105f, 174f), (60f, 178f), (43f, 212f)),
//    CBezier("LowerLid", (43f, 212f), (56f, 212f), (80f, 235f), (125f, 210f)),
//    CBezier("UpperLipJoiner", (-10f, 399f), (0f, 399f), (15f, 399f), (30f, 400f)),
//    CBezier("UpperLip", (30f, 400f), (43f, 410f), (45f, 400f), (60f, 399f)),
//    CBezier("LowerLip", (60f, 399f), (50f, 399f), (40f, 400f), (25f, 401f)),
//    CBezier("LowerLipJoiner", (25f, 401f), (12f, 400f), (0f, 400f), (-10f, 401f)))
//
//
//  val ratingOperators: (List[FragmentHolder[_]], Double) => Double = (alt, r) => {
//    val r = alt.count(fh => {
//      fh.toString.endsWith("Eyebrow") || fh.toString.endsWith("UpperLid") ||
//        fh.toString.endsWith("LowerLid") || fh.toString.endsWith("UpperLipJoiner") ||
//        fh.toString.endsWith("UpperLip") || fh.toString.endsWith("LowerLip") ||
//        fh.toString.endsWith("LowerLipJoiner")
//    })
//    r
//  }
//
//  val defaultFeatureSplinesMirror = defaultFeatureSplines.map(_.mirror())
//  val eyebrowSpline = defaultFeatureSplines(0)
//  val upperLidSpline = defaultFeatureSplines(1)
//  val lowerLidSpline = defaultFeatureSplines(2)
//  val upperLidJoinerSpline = defaultFeatureSplines(3)
//  val upperLipSpline = defaultFeatureSplines(4)
//  val lowerLipSpline = defaultFeatureSplines(5)
//  val lowerLipJoinerSpline = defaultFeatureSplines(6)
//
//  def bindFeatures(): Unit = {
//
//    val reader = new BufferedReader(new InputStreamReader(System.in))
//    val writer = new PrintWriter(System.out)
//    while (true) {
//      writer.print("Emotion 1:")
//      writer.flush()
//      var pair = reader.readLine().split(",")
//      val emo1 = (pair(0).toInt, pair(1).toFloat)
//
//      writer.print("Emotion 2:")
//      writer.flush()
//      pair = reader.readLine().split(",")
//      val emo2 = (pair(0).toInt, pair(1).toFloat)
//
//      features.emo1Level_=((emo1._1, emo1._2))
//      features.emo2Level_=((emo2._1, emo2._2))
////      features.emo1Level_=((0, 0f))
////      features.emo2Level_=((0, 0f))
//      features.remorph
//      println(features.myAlternative)
//      println(s"Influences: ${features.influence()}")
//      val splines: List[Spline] = features.splines()
//      //println(splines.map(_.render()).mkString)
//      flushSvg(splines)
//    }
//  }
//
//  val outFile = new File("/Users/zslajchrt/tmp/face.svg")
//
//  def flushSvg(splinesRight: List[Spline]) = {
//    val splinesLeft = splinesRight.map(_.mirror())
//    val splinesLeftRight = (splinesLeft ::: splinesRight).map(_.changeStyle(SplineStyle(color = "blue", strokeWidth = 3)))
//    val filteredDefSplines = defaultFeatureSplines.filterNot(defSpline => splinesRight.exists(_.name == defSpline.name))
//    val filteredDefSplinesMirror = defaultFeatureSplinesMirror.filterNot(defSpline => splinesRight.exists(_.name == defSpline.name))
//    val splines = (filteredDefSplines ::: filteredDefSplinesMirror ::: splinesLeftRight).map(_.moveRel((300f, 50f)))
//
//    val svg: String = s"""
//      <svg xmlns="http://www.w3.org/2000/svg"
//           xmlns:xlink="http://www.w3.org/1999/xlink"
//           width="100%" height="100%">
//           <image x="100" y="0" width="400" height="400" xlink:href="/Users/zslajchrt/Sites/meta/hairdo.svg" />
//           ${splines.map(_.render()).mkString}
//      </svg>
//    """
//    val fwr = new FileWriter(outFile)
//    try fwr.write(svg) finally fwr.close()
//  }
//
//}
//
//@fragment
//trait Feature {
//  def splines(): List[Spline] = Nil
//}
//
//import Features._
//
//@fragment
//@wrapper
//trait Eyebrow extends Feature {
//  this: M0 with M1 with M2 with M3 with M14 with H_Meb6 with H_Meb7 =>
//
//  private val spline = eyebrowSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
//    (0, m0, 0.59f),
//    (1, m1, 0.3f),
//    (1, m2, 0.15f),
//    (2, m2, 0.29f),
//    (3, m3, 0.59f),
//    (1, m14, 0.24f),
//    (2, m14, 0.18f),
//    (3, h_Meb6, 1f),
//    (2, h_Meb6, 1f),
//    (1, h_Meb6, 1f),
//    (0, h_Meb6, 1f),
//    (2, h_Meb7, 0.29f)
//  ).groupBy(_._1)
//
//  override def splines(): List[Spline] = {
//    spline.transform(muscleBindings) :: super.splines()
//  }
//}
//
//@fragment
//trait UpperLidFragment {
//  this: M041 with M042 with H_Mey8 with H_Mey12 =>
//
//  private val spline = upperLidSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
//    (1, m041, 1f),
//    (2, m041, 0.67f),
//    (1, m042, 1f),
//    (2, m042, 0.67f),
//    (2, h_Mey8, 0.33f),
//    (3, h_Mey12, 1f)
//  ).groupBy(_._1)
//
//  def transformUpperLid(): CBezier = {
//    spline.transform(muscleBindings)
//  }
//
//}
//
//@fragment
//@wrapper
//trait UpperLid extends Feature {
//  this: UpperLidFragment =>
//
//  override def splines(): List[Spline] = {
//    transformUpperLid() :: super.splines()
//  }
//}
//
//@fragment
//@wrapper
//trait LowerLid extends Feature {
//  this: UpperLidFragment with M5 =>
//
//  private val spline = lowerLidSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
//    (2, m5, 1f)
//  ).groupBy(_._1)
//
//  override def splines(): List[Spline] = {
//    val newUpperLidSpline: CBezier = transformUpperLid()
//    val lowerLidSplineJoined = spline.copy(p1 = newUpperLidSpline.p4, p4 = newUpperLidSpline.p1)
//    lowerLidSplineJoined.transform(muscleBindings) :: super.splines()
//  }
//}
//
//@fragment
//trait UpperLipJoinerFragment {
//  this: M6 with H_Mli9 with M7 with M8 with M10 with M16 with H_Mli9 =>
//
//  private def spline = upperLidJoinerSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
//    (0, m6, 1f),
//    (1, m6, 1f),
//    (0, h_Mli9, 0.4f),
//    (1, h_Mli9, 0.4f),
//    (2, m6, 1f),
//    (3, m6, 0.95f),
//    (3, m7, 0.1f),
//    (3, m8, 0.1f),
//    (3, m10, 0.2f),
//    (3, m16, 0.1f),
//    (2, h_Mli9, 0.25f)
//  ).groupBy(_._1)
//
//  def transformUpperLipJoiner: CBezier = {
//    spline.transform(muscleBindings)
//  }
//
//}
//
//@fragment
//@wrapper
//trait UpperLipJoiner extends Feature {
//  this: UpperLipJoinerFragment =>
//
//  override def splines(): List[Spline] = {
//    transformUpperLipJoiner :: super.splines()
//  }
//
//}
//
//@fragment
//trait UpperLipFragment {
//  this: UpperLipJoinerFragment with M6 with M7 with M8 with M9 with M10 with M11 with M16 =>
//
//  private val spline = upperLipSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
//    (1, m6, 0.95f),
//    (1, m7, 0.5f),
//    (1, m8, 0.29f),
//    (2, m6, 0.70f),
//    (2, m7, 0.5f),
//    (2, m8, 0.29f),
//    (3, m8, 0.23f),
//    (3, m9, 0.5f),
//    (2, m10, 0.29f),
//    (3, m10, 0.09f),
//
////    (3, m11, 0.23f),
//
//    (3, m16, 1f),
//    (2, m16, 0.3f)
//  ).groupBy(_._1)
//
//  def transformUpperLip: CBezier = {
//    val newLipJoinerSpline: CBezier = transformUpperLipJoiner
//    val upperLipSplineJoined = spline.copy(p1 = newLipJoinerSpline.p4)
//    upperLipSplineJoined.transform(muscleBindings)
//  }
//}
//
//@fragment
//@wrapper
//trait UpperLip extends Feature {
//  this: UpperLipFragment =>
//
//  override def splines(): List[Spline] = {
//    transformUpperLip :: super.splines()
//  }
//
//}
//
//@fragment
//trait LowerLipFragment {
//  this: UpperLipFragment with M8 with M9 with M10 with M11 with M12 with M15 with M16 with H_Mli10 =>
//
//  private val spline = lowerLipSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
////    (0, m8, 0.23f),
////    (0, m9, 0.5f),
//    (1, m9, 0.13f),
//    (2, m9, 0.05f),
////    (0, m10, 0.09f),
//    (1, m10, 0.30f),
//    (2, m10, 0.31f),
//    (3, m10, 0.13f),
////    (0, m11, 0.22f),
//    (1, m12, 0.5f),
//    (2, m12, 0.31f),
//    (3, m12, 0.13f),
//    (3, m15, 0.85f),
//    (2, m15, 0.7f),
//    (1, m15, 0.4f),
////    (0, m16, 1f),
//    (1, m16, 0.3f),
//    (2, m16, 0.1f),
//    (1, h_Mli10, 1f),
//    (2, h_Mli10, 0.2f),
//    (3, h_Mli10, 0.1f)
//  ).groupBy(_._1)
//
//  def transformLowerLip(): CBezier = {
//    val newUpperLipSpline: CBezier = transformUpperLip
//    val lowerLipSplineJoined = spline.copy(p1 = newUpperLipSpline.p4)
//    lowerLipSplineJoined.transform(muscleBindings)
//  }
//}
//
//@fragment
//@wrapper
//trait LowerLip extends Feature {
//  this: LowerLipFragment =>
//
//  override def splines(): List[Spline] = {
//    transformLowerLip() :: super.splines()
//  }
//}
//
//@fragment
//@wrapper
//trait LowerLipJoiner extends Feature {
//  this: LowerLipFragment with M15 with H_Mli11 =>
//
//  private val spline = lowerLipJoinerSpline
//  private lazy val muscleBindings: Map[Int, List[(Int, () => (Float, Float), Float)]] = List(
//    (3, m15, 1f),
//    (2, m15, 1f),
//    (1, m15, 1f),
//    (3, h_Mli11, 1f),
//    (2, h_Mli11, 1f),
//    (1, h_Mli11, 1f)
//  ).groupBy(_._1)
//
//  override def splines(): List[Spline] = {
//    val newLowerLipSpline: CBezier = transformLowerLip()
//    val lowerLipJoinerSplineJoined = spline.copy(p1 = newLowerLipSpline.p4)
//    lowerLipJoinerSplineJoined.transform(muscleBindings) :: super.splines()
//  }
//}
