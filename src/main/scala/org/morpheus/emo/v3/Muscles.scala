package org.morpheus.emo.v3

import org.morpheus._
import org.morpheus.Morpheus._

import org.morpheus.emo.{TensionCalculator, Spline, QBezier, Line}

/**
  * Created by zslajchrt on 26/12/15.
  */
object Muscles {
  //  type Muscles1D = Unit | (M7 with M2 with M8 with \?[H_Mli9 with M12 with H_Mli10] with H_TeethUpper with \?[M0 with M15 with M11 with H_Meb7] with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
  //    (\?[M7] with \?[M8 with H_Mey8] with \?[H_Mli9 with M12] with H_Mli10 with \?[M9 with H_TeethUpper] with M15 with M11 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
  //    (M7 with \?[M2 with H_Mey8] with H_Mli9 with M1 with M12 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
  //    (M7 with \?[M2 with H_Mey8 with M1] with \?[H_Mey12 with H_TeethUpper] with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
  //    (M7 with M2 with \?[M8 with H_Mey8 with \?[M1 with M12]] with H_Mey12 with M14 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
  //    (M7 with \?[M2 with M8 with \?[H_Mey8]] with \?[M1 with M12] with H_Mey12 with M9 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M5)

  //  type Muscles1D = Unit | dlg[M7 * M2 * M8 * H_Mli9 * M12 * H_Mli10 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * H_TeethLower * H_Meb6 * M5] |
  //    dlg[M7 * M8 * H_Mey8 * H_Mli9 * M12 * H_Mli10 * M9 * H_TeethUpper * M15 * M11 * M6 * M10 * M16 * M042 * H_TeethLower * H_Meb6 * M5] |
  //    dlg[M7 * M2 * H_Mey8 * H_Mli9 * M1 * M12 * H_Mey12 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M042 * M3 * H_TeethLower * H_Meb6 * M041 * M5] |
  //    dlg[M7 * M2 * H_Mey8 * M1 * H_Mey12 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * M3 * H_TeethLower * H_Meb6 * M041 * M5] |
  //    dlg[M7 * M2 * M8 * H_Mey8 * M1 * M12 * H_Mey12 * M14 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * M3 * H_TeethLower * H_Meb6 * M041 * M5] |
  //    dlg[M7 * M2 * M8 * H_Mey8 * M1 * M12 * H_Mey12 * M9 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * M3 * H_TeethLower * H_Meb6 * M5]

  type MusclesType[Base, M0, M1, M2, M3, M041, M042, M5, M6, M7, M8, M9, M10, M11, M12, M14, M15, M16, H_Meb6, H_Meb7, H_Mey8, H_Mli9, H_Mli10, H_Mli11, H_Mey12, H_TeethLower, H_TeethUpper] = {
    //  type Muscles1D = Unit | dlg[M7 * M2 * M8 * H_Mli9 * M12 * H_Mli10 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * H_TeethLower * H_Meb6 * M5] |
    //    dlg[M7 * M8 * H_Mey8 * H_Mli9 * M12 * H_Mli10 * M9 * H_TeethUpper * M15 * M11 * M6 * M10 * M16 * M042 * H_TeethLower * H_Meb6 * M5] |
    //    dlg[M7 * M2 * H_Mey8 * H_Mli9 * M1 * M12 * H_Mey12 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M042 * M3 * H_TeethLower * H_Meb6 * M041 * M5] |
    //    dlg[M7 * M2 * H_Mey8 * M1 * H_Mey12 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * M3 * H_TeethLower * H_Meb6 * M041 * M5] |
    //    dlg[M7 * M2 * M8 * H_Mey8 * M1 * M12 * H_Mey12 * M14 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * M3 * H_TeethLower * H_Meb6 * M041 * M5] |
    //    dlg[M7 * M2 * M8 * H_Mey8 * M1 * M12 * H_Mey12 * M9 * H_TeethUpper * M0 * M15 * M11 * H_Meb7 * M6 * M10 * M16 * M042 * M3 * H_TeethLower * H_Meb6 * M5]
    type Muscles1D = Unit | (M7 with M2 with M8 with H_Mli9 with M12 with H_Mli10 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
      (M7 with M8 with H_Mey8 with H_Mli9 with M12 with H_Mli10 with M9 with H_TeethUpper with M15 with M11 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
      (M7 with M2 with H_Mey8 with H_Mli9 with M1 with M12 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
      (M7 with M2 with H_Mey8 with M1 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
      (M7 with M2 with M8 with H_Mey8 with M1 with M12 with H_Mey12 with M14 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
      (M7 with M2 with M8 with H_Mey8 with M1 with M12 with H_Mey12 with M9 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M5)

//    type Muscles1D = Unit | (M7 with M2 with M8 with \?[H_Mli9 with M12 with H_Mli10] with H_TeethUpper with \?[M0 with M15 with M11 with H_Meb7] with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
//      (\?[M7] with \?[M8 with H_Mey8] with \?[H_Mli9 with M12] with H_Mli10 with \?[M9 with H_TeethUpper] with M15 with M11 with M6 with M10 with M16 with M042 with H_TeethLower with H_Meb6 with M5) |
//      (M7 with \?[M2 with H_Mey8] with H_Mli9 with M1 with M12 with H_Mey12 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
//      (M7 with \?[M2 with H_Mey8 with M1] with \?[H_Mey12 with H_TeethUpper] with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
//      (M7 with M2 with \?[M8 with H_Mey8 with \?[M1 with M12]] with H_Mey12 with M14 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M041 with M5) |
//      (M7 with \?[M2 with M8 with \?[H_Mey8]] with \?[M1 with M12] with H_Mey12 with M9 with H_TeethUpper with M0 with M15 with M11 with H_Meb7 with M6 with M10 with M16 with M042 with M3 with H_TeethLower with H_Meb6 with M5)

    type Muscles2D = Muscles1D * Muscles1D

    type Muscles = Base * Muscles2D
  }

  val MuscleNames = Map("m0" -> "M0", "m1" -> "M1", "m2" -> "M2", "m3" -> "M3", "m041" -> "M041", "m042" -> "M042",
    "m5" -> "M5", "m6" -> "M6", "m7" -> "M7", "m8" -> "M8", "m9" -> "M9", "m10" -> "M10", "m11" -> "M11", "m12" -> "M12",
    "m14" -> "M14", "m15" -> "M15", "m16" -> "M16", "h_meb6" -> "H_Meb6", "h_meb7" -> "H_Meb7", "h_mey8" -> "H_Mey8",
    "h_mli9" -> "H_Mli9", "h_mli10" -> "H_Mli10", "h_mli11" -> "H_Mli11", "h_mey12" -> "H_Mey12",
    "h_teethlower" -> "H_TeethLower", "h_teethupper" -> "H_TeethUpper")

  val MuscleTypeNames = List("M0", "M1", "M2", "M3", "M041", "M042", "M5", "M6", "M7", "M8", "M9", "M10", "M11", "M12", "M14", "M15", "M16", "H_Meb6", "H_Meb7", "H_Mey8", "H_Mli9", "H_Mli10", "H_Mli11", "H_Mey12", "H_TeethLower", "H_TeethUpper")

  val m0Spline = Line("m0", (136f, 144f), (140f, 140f))
  val m1Spline = Line("m1", (109f, 125f), (119f, 25f))
  val m2Spline = Line("m2", (73f, 127f), (79f, 19f))
  val m3Spline = Line("m3", (26f, 152f), (27f, 30f))
  val m5Spline = Line("m5", (87f, 244f), (87f, 170f))
  val m6Spline = Line("m6", (36f, 345f), (36f, 262f))
  val m7Spline = Line("m7", (58f, 349f), (92f, 269f))
  val m8Spline = QBezier("m8", (79f, 358f), (119f, 335f), (147f, 303f))
  val m9Spline = QBezier("m9", (89f, 382f), (133f, 378f), (155f, 331f))
  val m10Spline = Line("m10", (89f, 411f), (131f, 424f))
  val m11Spline = QBezier("m11", (75f, 425f), (80f, 457f), (59f, 488f))
  val m12Spline = Line("m12", (25f, 447f), (44f, 493f))
  val m14Spline = QBezier("m14", (99f, 114f), (70f, 137f), (55f, 159f))
  val m15Spline = Line("m15", (8f, 447f), (8f, 502f))
  val m041Spline = Line("m041", (116f, 171f), (116f, 152f))
  val m042Spline = Line("m042", (98f, 171f), (98f, 224f))
  val m16Spline = Line("m16", (64f, 399f), (-10f, 399f))
  val h_meb6Spline = Line("h_meb6", (14f, 122f), (14f, 211f))
  val h_meb7Spline = Line("h_meb7", (42f, 122f), (131f, 235f))
  val h_mey8Spline = Line("h_mey8", (49f, 165f), (123f, 269f))
  val h_mli9Spline = Line("h_mli9", (0f, 339f), (0f, 354f))
  val h_mli10Spline = Line("h_mli10", (43f, 446f), (58f, 465f))
  val h_mli11Spline = Line("h_mli11", (-21f, 449f), (-21f, 462f))
  val h_mey12Spline = Line("h_mey12", (32f, 215f), (32f, 175f))
  val h_teethupperSpline = Line("h_teethupper", (196f, 399f), (196f, 337f))
  val h_teethlowerSpline = Line("h_teethlower", (196f, 417f), (196f, 462f))

}

class Muscle(val spline: Spline, muscleBase: MuscleBase) {
  //class Muscle(val spline: Spline) extends (() => (Float, Float)) {
  val name = spline.name
  val tension: Float = muscleBase.tensions.getOrElse(spline.name, 0f)

  def contraction(): (Float, Float) = {
    val pos0 = spline(0)
    val pos1 = spline(tension)
    (pos1._1 - pos0._1, pos1._2 - pos0._2)
  }

}


trait MuscleBaseInit {
  val tensions: Map[String, Float]
}

case class MuscleBaseInitData(tensions: Map[String, Float]) extends MuscleBaseInit {
}

@fragment
trait MuscleBase extends dlg[MuscleBaseInit] {
  def contractions: Map[String, (Float, Float)] = Map.empty
}

@fragment @nodepscheck
trait H_Meb6Fragment {
  this: MuscleBase =>

  lazy val h_meb6 = new Muscle(Muscles.h_meb6Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Meb6Wrapper extends MuscleBase {
  this: H_Meb6Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_meb6" -> h_meb6.contraction())

}


@fragment @nodepscheck
trait H_Meb7Fragment {
  this: MuscleBase =>

  lazy val h_meb7 = new Muscle(Muscles.h_meb7Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Meb7Wrapper extends MuscleBase {
  this: H_Meb7Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_meb7" -> h_meb7.contraction())

}


@fragment @nodepscheck
trait H_Mey12Fragment {
  this: MuscleBase =>

  lazy val h_mey12 = new Muscle(Muscles.h_mey12Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Mey12Wrapper extends MuscleBase {
  this: H_Mey12Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_mey12" -> h_mey12.contraction())

}


@fragment @nodepscheck
trait H_Mey8Fragment {
  this: MuscleBase =>

  lazy val h_mey8 = new Muscle(Muscles.h_mey8Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Mey8Wrapper extends MuscleBase {
  this: H_Mey8Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_mey8" -> h_mey8.contraction())

}


@fragment @nodepscheck
trait H_Mli10Fragment {
  this: MuscleBase =>

  lazy val h_mli10 = new Muscle(Muscles.h_mli10Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Mli10Wrapper extends MuscleBase {
  this: H_Mli10Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_mli10" -> h_mli10.contraction())

}


@fragment @nodepscheck
trait H_Mli11Fragment {
  this: MuscleBase =>

  lazy val h_mli11 = new Muscle(Muscles.h_mli11Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Mli11Wrapper extends MuscleBase {
  this: H_Mli11Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_mli11" -> h_mli11.contraction())

}


@fragment @nodepscheck
trait H_Mli9Fragment {
  this: MuscleBase =>

  lazy val h_mli9 = new Muscle(Muscles.h_mli9Spline, this)
}

@fragment @wrapper @nodepscheck
trait H_Mli9Wrapper extends MuscleBase {
  this: H_Mli9Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_mli9" -> h_mli9.contraction())

}


@fragment @nodepscheck
trait H_TeethLowerFragment {
  this: MuscleBase =>

  lazy val h_teethlower = new Muscle(Muscles.h_teethlowerSpline, this)
}

@fragment @wrapper @nodepscheck
trait H_TeethLowerWrapper extends MuscleBase {
  this: H_TeethLowerFragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_teethlower" -> h_teethlower.contraction())

}


@fragment @nodepscheck
trait H_TeethUpperFragment {
  this: MuscleBase =>

  lazy val h_teethupper = new Muscle(Muscles.h_teethupperSpline, this)
}

@fragment @wrapper @nodepscheck
trait H_TeethUpperWrapper extends MuscleBase {
  this: H_TeethUpperFragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("h_teethupper" -> h_teethupper.contraction())

}


@fragment @nodepscheck
trait M0Fragment {
  this: MuscleBase =>

  lazy val m0 = new Muscle(Muscles.m0Spline, this)
}

@fragment @wrapper @nodepscheck
trait M0Wrapper extends MuscleBase {
  this: M0Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m0" -> m0.contraction())

}


@fragment @nodepscheck
trait M041Fragment {
  this: MuscleBase =>

  lazy val m041 = new Muscle(Muscles.m041Spline, this)
}

@fragment @wrapper @nodepscheck
trait M041Wrapper extends MuscleBase {
  this: M041Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m041" -> m041.contraction())

}


@fragment @nodepscheck
trait M042Fragment {
  this: MuscleBase =>

  lazy val m042 = new Muscle(Muscles.m042Spline, this)
}

@fragment @wrapper @nodepscheck
trait M042Wrapper extends MuscleBase {
  this: M042Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m042" -> m042.contraction())

}


@fragment @nodepscheck
trait M1Fragment {
  this: MuscleBase =>

  lazy val m1 = new Muscle(Muscles.m1Spline, this)
}

@fragment @wrapper @nodepscheck
trait M1Wrapper extends MuscleBase {
  this: M1Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m1" -> m1.contraction())

}


@fragment @nodepscheck
trait M10Fragment {
  this: MuscleBase =>

  lazy val m10 = new Muscle(Muscles.m10Spline, this)
}

@fragment @wrapper @nodepscheck
trait M10Wrapper extends MuscleBase {
  this: M10Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m10" -> m10.contraction())

}


@fragment @nodepscheck
trait M11Fragment {
  this: MuscleBase =>

  lazy val m11 = new Muscle(Muscles.m11Spline, this)
}

@fragment @wrapper @nodepscheck
trait M11Wrapper extends MuscleBase {
  this: M11Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m11" -> m11.contraction())

}


@fragment @nodepscheck
trait M12Fragment {
  this: MuscleBase =>

  lazy val m12 = new Muscle(Muscles.m12Spline, this)
}

@fragment @wrapper @nodepscheck
trait M12Wrapper extends MuscleBase {
  this: M12Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m12" -> m12.contraction())

}


@fragment @nodepscheck
trait M14Fragment {
  this: MuscleBase =>

  lazy val m14 = new Muscle(Muscles.m14Spline, this)
}

@fragment @wrapper @nodepscheck
trait M14Wrapper extends MuscleBase {
  this: M14Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m14" -> m14.contraction())

}


@fragment @nodepscheck
trait M15Fragment {
  this: MuscleBase =>

  lazy val m15 = new Muscle(Muscles.m15Spline, this)
}

@fragment @wrapper @nodepscheck
trait M15Wrapper extends MuscleBase {
  this: M15Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m15" -> m15.contraction())

}


@fragment @nodepscheck
trait M16Fragment {
  this: MuscleBase =>

  lazy val m16 = new Muscle(Muscles.m16Spline, this)
}

@fragment @wrapper @nodepscheck
trait M16Wrapper extends MuscleBase {
  this: M16Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m16" -> m16.contraction())

}


@fragment @nodepscheck
trait M2Fragment {
  this: MuscleBase =>

  lazy val m2 = new Muscle(Muscles.m2Spline, this)

}

@fragment @wrapper @nodepscheck
trait M2Wrapper extends MuscleBase {
  this: M2Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m2" -> m2.contraction())

}


@fragment @nodepscheck
trait M3Fragment {
  this: MuscleBase =>

  lazy val m3 = new Muscle(Muscles.m3Spline, this)
}

@fragment @wrapper @nodepscheck
trait M3Wrapper extends MuscleBase {
  this: M3Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m3" -> m3.contraction())

}


@fragment @nodepscheck
trait M5Fragment {
  this: MuscleBase =>

  lazy val m5 = new Muscle(Muscles.m5Spline, this)
}

@fragment @wrapper @nodepscheck
trait M5Wrapper extends MuscleBase {
  this: M5Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m5" -> m5.contraction())

}


@fragment @nodepscheck
trait M6Fragment {
  this: MuscleBase =>

  lazy val m6 = new Muscle(Muscles.m6Spline, this)
}

@fragment @wrapper @nodepscheck
trait M6Wrapper extends MuscleBase {
  this: M6Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m6" -> m6.contraction())

}


@fragment @nodepscheck
trait M7Fragment {
  this: MuscleBase =>

  lazy val m7 = new Muscle(Muscles.m7Spline, this)

  def xxx(): Int = {
    1
  }
}

@fragment @wrapper @nodepscheck
trait M7Wrapper extends MuscleBase {
  this: M7Fragment =>

  override def contractions: Map[String, (Float, Float)] = {
    xxx()
    super.contractions + ("m7" -> m7.contraction())
  }

}


@fragment @nodepscheck
trait M8Fragment {
  this: MuscleBase =>

  lazy val m8 = new Muscle(Muscles.m8Spline, this)
}

@fragment @wrapper @nodepscheck
trait M8Wrapper extends MuscleBase {
  this: M8Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m8" -> m8.contraction())

}


@fragment @nodepscheck
trait M9Fragment {
  this: MuscleBase =>

  lazy val m9 = new Muscle(Muscles.m9Spline, this)
}

@fragment @wrapper @nodepscheck
trait M9Wrapper extends MuscleBase {
  this: M9Fragment =>

  override def contractions: Map[String, (Float, Float)] = super.contractions + ("m9" -> m9.contraction())

}
