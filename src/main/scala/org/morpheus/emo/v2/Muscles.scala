//package org.morpheus.emo.v2
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.{Splines, QBezier, Line, Spline}
//
///**
//  * Created by zslajchrt on 20/12/15.
//  */
//class Muscles[C, M0, M1, M2, M3, M041, M042, M5, M6, M7, M8, M9, M10, M11, M12, M14, M15, M16, H_Meb6, H_Meb7, H_Mey8, H_Mli9, H_Mli10, H_Mli11, H_Mey12, H_TeethLower, H_TeethUpper] {
////class Muscles {
//
//  type Muscles1D = Unit | (M7 with M2 with M8 with \?[H_Mli9 with M12 with H_Mli10] with H_TeethUpper with \?[M0 with M15 with M11 with H_Meb7] with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
//    (\?[M7] with \?[M8 with H_Mey8] with \?[H_Mli9 with M12] with H_Mli10 with \?[M9 with H_TeethUpper] with M15 with M11 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
//    (M7 with \?[M2 with H_Mey8] with H_Mli9 with M1 with M12 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
//    (M7 with \?[M2 with H_Mey8 with M1] with \?[H_Mey12 with H_TeethUpper] with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
//    (M7 with M2 with \?[M8 with H_Mey8 with \?[M1 with M12]] with H_Mey12 with M14 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
//    (M7 with \?[M2 with M8 with \?[H_Mey8]] with \?[M1 with M12] with H_Mey12 with M9 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M5)
//
////  type Muscles1D = Unit | (M7 with M2 with M8 with H_Mli9 with M12 with H_Mli10 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
////    (M7 with M8 with H_Mey8 with H_Mli9 with M12 with H_Mli10 with M9 with H_TeethUpper with M15 with M11 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
////    (M7 with M2 with H_Mey8 with H_Mli9 with M1 with M12 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
////    (M7 with M2 with H_Mey8 with M1 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
////    (M7 with M2 with M8 with H_Mey8 with M1 with M12 with H_Mey12 with M14 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
////    (M7 with M2 with M8 with H_Mey8 with M1 with M12 with H_Mey12 with M9 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M5)
//
//  type Muscles2D = Muscles1D * Muscles1D
//
//  type Muscles = C with Muscles2D
//
//  //val musclesModel = parse[Muscles](true)
//  //val musclesRec = singleton(musclesModel, rootStrategy(musclesModel))
//  //val musclesRec = singleton(musclesModel, rootStrategy(musclesModel))
//
//  //type MuscleFragments = M0 | M1 | M2 | M3 | M041 | M042 | M5 | M6 | M7 | M8 | M9 | M10 | M11 | M12 | M14 | M15 | M16 | H_Meb6 | H_Meb7 | H_Mey8 | H_Mli9 | H_Mli10 | H_Mli11 | H_Mey12 | H_TeethLower | H_TeethUpper
//
//  //val r: &?[MuscleFragments] = musclesRec
//
////  def main(args: Array[String]) {
////
////    for (fd <- musclesModel.fragmentDescriptorsList) {
////      //musclesRec.fragmentHolder[String]
////      val fbm = musclesModel.fragmentBitMask(fd.index)
////      println(s"${fd}\t$fbm")
////    }
////
////  }
//}
//
//object Muscles {
//  val MuscleNames = Map("m0" -> "M0", "m1" -> "M1", "m2" -> "M2", "m3" -> "M3", "m041" -> "M041", "m042" -> "M042",
//    "m5" -> "M5", "m6" -> "M6", "m7" -> "M7", "m8" -> "M8", "m9" -> "M9", "m10" -> "M10", "m11" -> "M11", "m12" -> "M12",
//    "m14" -> "M14", "m15" -> "M15", "m16" -> "M16", "h_meb6" -> "H_Meb6", "h_meb7" -> "H_Meb7", "h_mey8" -> "H_Mey8",
//    "h_mli9" -> "H_Mli9", "h_mli10" -> "H_Mli10", "h_mli11" -> "H_Mli11", "h_mey12" -> "H_Mey12",
//    "h_teethlower" -> "H_TeethLower", "h_teethupper" -> "H_TeethUpper")
//
//  val MuscleTypeNames = List("M0", "M1", "M2", "M3", "M041", "M042", "M5", "M6", "M7", "M8", "M9", "M10", "M11", "M12", "M14", "M15", "M16", "H_Meb6", "H_Meb7", "H_Mey8", "H_Mli9", "H_Mli10", "H_Mli11", "H_Mey12", "H_TeethLower", "H_TeethUpper")
//
//  val m0Spline = Line("m0", (136f, 144f), (140f, 140f))
//  val m1Spline = Line("m1", (109f, 125f), (119f, 25f))
//  val m2Spline = Line("m2", (73f, 127f), (79f, 19f))
//  val m3Spline = Line("m3", (26f, 152f), (27f, 30f))
//  val m5Spline = Line("m5", (87f, 244f), (87f, 170f))
//  val m6Spline = Line("m6", (36f, 345f), (36f, 262f))
//  val m7Spline = Line("m7", (58f, 349f), (92f, 269f))
//  val m8Spline = QBezier("m8", (79f, 358f), (119f, 335f), (147f, 303f))
//  val m9Spline = QBezier("m9", (89f, 382f), (133f, 378f), (155f, 331f))
//  val m10Spline = Line("m10", (89f, 411f), (131f, 424f))
//  val m11Spline = QBezier("m11", (75f, 425f), (80f, 457f), (59f, 488f))
//  val m12Spline = Line("m12", (25f, 447f), (44f, 493f))
//  val m14Spline = QBezier("m14", (99f, 114f), (70f, 137f), (55f, 159f))
//  val m15Spline = Line("m15", (8f, 447f), (8f, 502f))
//  val m041Spline = Line("m041", (116f, 171f), (116f, 152f))
//  val m042Spline = Line("m042", (98f, 171f), (98f, 224f))
//  val m16Spline = Line("m16", (64f, 399f), (-10f, 399f))
//  val h_meb6Spline = Line("h_meb6", (14f, 122f), (14f, 211f))
//  val h_meb7Spline = Line("h_meb7", (42f, 122f), (131f, 235f))
//  val h_mey8Spline = Line("h_mey8", (49f, 165f), (123f, 269f))
//  val h_mli9Spline = Line("h_mli9", (0f, 339f), (0f, 354f))
//  val h_mli10Spline = Line("h_mli10", (43f, 446f), (58f, 465f))
//  val h_mli11Spline = Line("h_mli11", (-21f, 449f), (-21f, 462f))
//  val h_mey12Spline = Line("h_mey12", (32f, 215f), (32f, 175f))
//  val h_teethupperSpline = Line("h_teethupper", (196f, 399f), (196f, 337f))
//  val h_teethlowerSpline = Line("h_teethlower", (196f, 417f), (196f, 462f))
//
//}
//
//import Muscles._
//
//abstract class Muscle(val spline: Spline) extends (() => (Float, Float)) {
////class Muscle(val spline: Spline) extends (() => (Float, Float)) {
//  val name = spline.name
//  var tension0: Float = 0f
//  var tension1: Float = 0f
//
//  def updateTension(newTension: Float): Unit = {
//    tension0 = tension1
//    tension1 = newTension
//    applyTensionToFeatures()
//  }
//
//  override def apply(): (Float, Float) = {
//    val pos0 = spline(tension0)
//    val pos1 = spline(tension1)
//    (pos1._1 - pos0._1, pos1._2 - pos0._2)
//  }
//
//  def applyTensionToFeatures(): Unit
//}
//
//@fragment
//trait MuscleBase {
//  def updateFeatures(): Unit = {
//
//  }
//}
//
///////
//
//@fragment
//trait H_Meb6F {
//  this: Eyebrow =>
//
//  lazy val h_meb6 = new Muscle(Muscles.h_meb6Spline) {
//
//    val eyebrowWeights = List((3, 1.0f),(2, 1.0f),(1, 1.0f),(0, 1.0f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_Meb7F {
//  this: Eyebrow =>
//
//  lazy val h_meb7 = new Muscle(Muscles.h_meb7Spline) {
//
//    val eyebrowWeights = List((2, 0.29f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_Mey12F {
//  this: UpperLid =>
//
//  lazy val h_mey12 = new Muscle(Muscles.h_mey12Spline) {
//
//    val upperlidWeights = List((3, 1.0f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlidDeltas.update(Splines.transform(this(), upperlidWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_Mey8F {
//  this: UpperLid =>
//
//  lazy val h_mey8 = new Muscle(Muscles.h_mey8Spline) {
//
//    val upperlidWeights = List((2, 0.33f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlidDeltas.update(Splines.transform(this(), upperlidWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_Mli10F {
//  this: LowerLip =>
//
//  lazy val h_mli10 = new Muscle(Muscles.h_mli10Spline) {
//
//    val lowerlipWeights = List((1, 1.0f),(2, 0.2f),(3, 0.1f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_Mli11F {
//  this: LowerLipJoiner =>
//
//  lazy val h_mli11 = new Muscle(Muscles.h_mli11Spline) {
//
//    val lowerlipjoinerWeights = List((1, 1.0f),(2, 1.0f),(3, 1.0f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipjoinerDeltas.update(Splines.transform(this(), lowerlipjoinerWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_Mli9F {
//  this: UpperLipJoiner =>
//
//  lazy val h_mli9 = new Muscle(Muscles.h_mli9Spline) {
//
//    val upperlipjoinerWeights = List((0, 0.4f),(1, 0.4f),(2, 0.25f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlipjoinerDeltas.update(Splines.transform(this(), upperlipjoinerWeights))
//    }
//  }
//}
//
//
//@fragment
//trait H_TeethLowerF {
//
//
//  lazy val h_teethlower = new Muscle(Muscles.h_teethlowerSpline) {
//
//
//
//    def applyTensionToFeatures() {
//
//    }
//  }
//}
//
//
//@fragment
//trait H_TeethUpperF {
//
//
//  lazy val h_teethupper = new Muscle(Muscles.h_teethupperSpline) {
//
//
//
//    def applyTensionToFeatures() {
//
//    }
//  }
//}
//
//
//@fragment
//trait M0F {
//  this: Eyebrow =>
//
//  lazy val m0 = new Muscle(Muscles.m0Spline) {
//
//    val eyebrowWeights = List((0, 0.59f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M041F {
//  this: UpperLid =>
//
//  lazy val m041 = new Muscle(Muscles.m041Spline) {
//
//    val upperlidWeights = List((1, 1.0f),(2, 0.67f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlidDeltas.update(Splines.transform(this(), upperlidWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M042F {
//  this: UpperLid =>
//
//  lazy val m042 = new Muscle(Muscles.m042Spline) {
//
//    val upperlidWeights = List((1, 1.0f),(2, 0.67f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlidDeltas.update(Splines.transform(this(), upperlidWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M1F {
//  this: Eyebrow =>
//
//  lazy val m1 = new Muscle(Muscles.m1Spline) {
//
//    val eyebrowWeights = List((1, 0.3f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M10F {
//  this: LowerLip with UpperLip with UpperLipJoiner =>
//
//  lazy val m10 = new Muscle(Muscles.m10Spline) {
//
//    val lowerlipWeights = List((0, 0.09f),(1, 0.3f),(2, 0.31f),(3, 0.13f)).groupBy(_._1)
//    val upperlipWeights = List((2, 0.29f),(3, 0.09f)).groupBy(_._1)
//    val upperlipjoinerWeights = List((3, 0.2f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//      upperlipjoinerDeltas.update(Splines.transform(this(), upperlipjoinerWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M11F {
//  this: UpperLip with LowerLip =>
//
//  lazy val m11 = new Muscle(Muscles.m11Spline) {
//
//    val upperlipWeights = List((3, 0.23f)).groupBy(_._1)
//    val lowerlipWeights = List((0, 0.22f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M12F {
//  this: LowerLip =>
//
//  lazy val m12 = new Muscle(Muscles.m12Spline) {
//
//    val lowerlipWeights = List((1, 0.5f),(2, 0.31f),(3, 0.13f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M14F {
//  this: Eyebrow =>
//
//  lazy val m14 = new Muscle(Muscles.m14Spline) {
//
//    val eyebrowWeights = List((1, 0.24f),(2, 0.18f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M15F {
//  this: LowerLip with LowerLipJoiner =>
//
//  lazy val m15 = new Muscle(Muscles.m15Spline) {
//
//    val lowerlipWeights = List((3, 0.85f),(2, 0.7f),(1, 0.4f)).groupBy(_._1)
//    val lowerlipjoinerWeights = List((3, 1.0f),(2, 1.0f),(1, 1.0f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//      lowerlipjoinerDeltas.update(Splines.transform(this(), lowerlipjoinerWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M16F {
//  this: UpperLip with UpperLipJoiner with LowerLip =>
//
//  lazy val m16 = new Muscle(Muscles.m16Spline) {
//
//    val upperlipWeights = List((3, 1.0f),(2, 0.3f)).groupBy(_._1)
//    val upperlipjoinerWeights = List((3, 0.1f)).groupBy(_._1)
//    val lowerlipWeights = List((0, 1.0f),(1, 0.3f),(2, 0.1f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//      upperlipjoinerDeltas.update(Splines.transform(this(), upperlipjoinerWeights))
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M2F {
//  this: Eyebrow =>
//
//  lazy val m2 = new Muscle(Muscles.m2Spline) {
//
//    val eyebrowWeights = List((1, 0.15f),(2, 0.29f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M3F {
//  this: Eyebrow =>
//
//  lazy val m3 = new Muscle(Muscles.m3Spline) {
//
//    val eyebrowWeights = List((3, 0.59f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      eyebrowDeltas.update(Splines.transform(this(), eyebrowWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M5F {
//  this: LowerLid =>
//
//  lazy val m5 = new Muscle(Muscles.m5Spline) {
//
//    val lowerlidWeights = List((2, 1.0f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlidDeltas.update(Splines.transform(this(), lowerlidWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M6F {
//  this: UpperLip with UpperLipJoiner =>
//
//  lazy val m6 = new Muscle(Muscles.m6Spline) {
//
//    val upperlipWeights = List((1, 0.95f),(2, 0.7f)).groupBy(_._1)
//    val upperlipjoinerWeights = List((0, 1.0f),(1, 1.0f),(2, 1.0f),(3, 0.95f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//      upperlipjoinerDeltas.update(Splines.transform(this(), upperlipjoinerWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M7F {
//  this: UpperLip with UpperLipJoiner =>
//
//  lazy val m7 = new Muscle(Muscles.m7Spline) {
//
//    val upperlipWeights = List((1, 0.5f),(2, 0.5f)).groupBy(_._1)
//    val upperlipjoinerWeights = List((3, 0.1f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//      upperlipjoinerDeltas.update(Splines.transform(this(), upperlipjoinerWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M8F {
//  this: LowerLip with UpperLipJoiner with UpperLip =>
//
//  lazy val m8 = new Muscle(Muscles.m8Spline) {
//
//    val lowerlipWeights = List((0, 0.23f)).groupBy(_._1)
//    val upperlipjoinerWeights = List((3, 0.1f)).groupBy(_._1)
//    val upperlipWeights = List((1, 0.29f),(2, 0.29f),(3, 0.23f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//      upperlipjoinerDeltas.update(Splines.transform(this(), upperlipjoinerWeights))
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//    }
//  }
//}
//
//
//@fragment
//trait M9F {
//  this: LowerLip with UpperLip =>
//
//  lazy val m9 = new Muscle(Muscles.m9Spline) {
//
//    val lowerlipWeights = List((0, 0.5f),(1, 0.13f),(2, 0.05f)).groupBy(_._1)
//    val upperlipWeights = List((3, 0.5f)).groupBy(_._1)
//
//    def applyTensionToFeatures() {
//      lowerlipDeltas.update(Splines.transform(this(), lowerlipWeights))
//      upperlipDeltas.update(Splines.transform(this(), upperlipWeights))
//    }
//  }
//}
