//package org.morpheus.emo
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.Tensions.TensionCalculation
//
///**
// * Created by zslajchrt on 11/11/15.
// */
//
//object Muscles {
//}
//
//class Muscle(val spline: Spline) extends (() => (Float, Float)) {
//  val name = spline.name
//  var tension: Float = 0f
//  val initialPos: (Float, Float) = spline(tension)
//
//  def updateTension(calcFn: TensionCalculation, newTension: Option[Float]): Unit = {
//    if (newTension.nonEmpty) {
//      calcFn(name) match {
//        case Some(tensionCalc) =>
//          val t = tensionCalc(newTension.get)
//          tension = t
//        case None =>
//      }
//    }
//  }
//
//  override def apply(): (Float, Float) = {
//    val newPos = spline(tension)
//    (newPos._1 - initialPos._1, newPos._2 - initialPos._2)
//  }
//}
//
//@fragment
//trait MuscleBase {
//  def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    Nil
//  }
//}
//
//@fragment
//@wrapper
//trait M0 extends MuscleBase {
//  //val m0 = new Muscle(Line("m0", (136f, 144f), (140f, 52f)))
//  val m0 = new Muscle(Line("m0", (136f, 144f), (140f, 140f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m0.updateTension(calcFn, tension)
//    m0 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait M1 extends MuscleBase {
//  val m1 = new Muscle(Line("m1", (109f, 125f), (119f, 25f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m1.updateTension(calcFn, tension)
//    m1 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait M2 extends MuscleBase {
//  val m2 = new Muscle(Line("m2", (73f, 127f), (79f, 19f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m2.updateTension(calcFn, tension)
//    m2 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait M3 extends MuscleBase {
//  val m3 = new Muscle(Line("m3", (26f, 152f), (27f, 30f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m3.updateTension(calcFn, tension)
//    m3 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait M5 extends MuscleBase {
//  val m5 = new Muscle(Line("m5", (87f, 244f), (87f, 170f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m5.updateTension(calcFn, tension)
//    m5 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M6 extends MuscleBase {
//  val m6 = new Muscle(Line("m6", (36f, 345f), (36f, 262f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m6.updateTension(calcFn, tension)
//    m6 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M7 extends MuscleBase {
//  val m7 = new Muscle(Line("m7", (58f, 349f), (92f, 269f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m7.updateTension(calcFn, tension)
//    m7 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M8 extends MuscleBase {
//  val m8 = new Muscle(QBezier("m8", (79f, 358f), (119f, 335f), (147f, 303f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m8.updateTension(calcFn, tension)
//    m8 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M9 extends MuscleBase {
//  val m9 = new Muscle(QBezier("m9", (89f, 382f), (133f, 378f), (155f, 331f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m9.updateTension(calcFn, tension)
//    m9 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M10 extends MuscleBase {
//  val m10 = new Muscle(Line("m10", (89f, 411f), (131f, 424f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m10.updateTension(calcFn, tension)
//    m10 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M11 extends MuscleBase {
//  val m11 = new Muscle(QBezier("m11", (75f, 425f), (80f, 457f), (59f, 488f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m11.updateTension(calcFn, tension)
//    m11 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M12 extends MuscleBase {
//  val m12 = new Muscle(Line("m12", (25f, 447f), (44f, 493f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m12.updateTension(calcFn, tension)
//    m12 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M14 extends MuscleBase {
//  val m14 = new Muscle(QBezier("m14", (99f, 114f), (70f, 137f), (55f, 159f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m14.updateTension(calcFn, tension)
//    m14 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M15 extends MuscleBase {
//  val m15 = new Muscle(Line("m15", (8f, 447f), (8f, 502f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m15.updateTension(calcFn, tension)
//    m15 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M041 extends MuscleBase {
//  val m041 = new Muscle(Line("m041", (116f, 171f), (116f, 152f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m041.updateTension(calcFn, tension)
//    m041 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M042 extends MuscleBase {
//  val m042 = new Muscle(Line("m042", (98f, 171f), (98f, 224f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m042.updateTension(calcFn, tension)
//    m042 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait M16 extends MuscleBase {
//  val m16 = new Muscle(Line("m16", (64f, 399f), (-10f, 399f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    m16.updateTension(calcFn, tension)
//    m16 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait H_Meb6 extends MuscleBase {
//  val h_Meb6 = new Muscle(Line("h_meb6", (14f, 122f), (14f, 211f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Meb6.updateTension(calcFn, tension)
//    h_Meb6 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait H_Meb7 extends MuscleBase {
//  val h_Meb7 = new Muscle(Line("h_meb7", (42f, 122f), (131f, 235f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Meb7.updateTension(calcFn, tension)
//    h_Meb7 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait H_Mey8 extends MuscleBase {
//  val h_Mey8 = new Muscle(Line("h_mey8", (49f, 165f), (123f, 269f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Mey8.updateTension(calcFn, tension)
//    h_Mey8 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait H_Mli9 extends MuscleBase {
//  val h_Mli9 = new Muscle(Line("h_mli9", (0f, 339f), (0f, 354f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Mli9.updateTension(calcFn, tension)
//    h_Mli9 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait H_Mli10 extends MuscleBase {
//  val h_Mli10 = new Muscle(Line("h_mli10", (43f, 446f), (58f, 465f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Mli10.updateTension(calcFn, tension)
//    h_Mli10 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait H_Mli11 extends MuscleBase {
//  val h_Mli11 = new Muscle(Line("h_mli11", (-21f, 449f), (-21f, 462f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Mli11.updateTension(calcFn, tension)
//    h_Mli11 :: super.applyTension(calcFn, tension)
//  }
//}
//
//@fragment
//@wrapper
//trait H_Mey12 extends MuscleBase {
//  val h_Mey12 = new Muscle(Line("h_mey12", (32f, 215f), (32f, 175f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_Mey12.updateTension(calcFn, tension)
//    h_Mey12 :: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait H_TeethUpper extends MuscleBase {
//  val h_TeethUpper = new Muscle(Line("h_teethupper", (196f, 399f), (196f, 337f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_TeethUpper.updateTension(calcFn, tension)
//    h_TeethUpper:: super.applyTension(calcFn, tension)
//  }
//
//}
//
//@fragment
//@wrapper
//trait H_TeethLower extends MuscleBase {
//  val h_TeethLower = new Muscle(Line("h_teethlower", (196f, 417f), (196f, 462f)))
//
//  override def applyTension(calcFn: TensionCalculation, tension: Option[Float]): List[Muscle] = {
//    h_TeethLower.updateTension(calcFn, tension)
//    h_TeethLower :: super.applyTension(calcFn, tension)
//  }
//
//}
