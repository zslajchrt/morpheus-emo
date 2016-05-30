//package org.morpheus.emo
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//
///**
// * Created by zslajchrt on 11/11/15.
// */
//object Emotions {
//
//  type BASIC_MODEL_TYPE = Unit or Joy or Surprise or Fear or Sadness or Disgust or Anger
//  //  type MULTI_DIM_MODEL_TYPE = \?[Joy] with \?[Surprise] with \?[Fear] with \?[Sadness] with \?[Disgust] with \?[Anger]
//  //  val multiDimModel = parse[MULTI_DIM_MODEL_TYPE](false)
//
//  type EMOTIONS_BINDING_TYPE = Unit or ^[JoyInf] or ^[SurpriseInf] or ^[FearInf] or ^[SadnessInf] or ^[DisgustInf] or ^[AngerInf]
//  type ALL_MUSCLES = MuscleBase with M0 with M1 with M2 with M3 with M041 with M042 with M5 with M6 with M7 with M8 with M9 with M10 with M11 with M12 with M14 with M15 with M16 with H_Meb6 with H_Meb7 with H_Mey8 with H_Mli9 with H_Mli10 with H_Mli11 with H_Mey12 with H_TeethLower with H_TeethUpper
//
////  type MUSCLE_COMMON = M5 with M6 with M7 with M11 with M15 with H_Meb6 with H_TeethUpper with H_TeethLower
////  type MUSCLE_GROUPS = Unit or
////    (^[JoyInf] with M042 with M8 with M9 with M10 with M12 with M16 with H_Mli9 with H_Mli10 with H_Mey8) or
////    (^[SurpriseInf] with M0 with M1 with M2 with M3 with M041 with M10 with M16 with M042 with H_Meb7 with H_Mey8 with H_Mey12) or
////    (^[FearInf] with M0 with M1 with M2 with M3 with M041 with M8 with M10 with M12 with M14 with M16 with H_Meb7 with M042 with H_Mey12 with H_Mey8) or
////    (^[SadnessInf] with M0 with M1 with M2 with M3 with M042 with M8 with M9 with M10 with M12 with M16 with H_Meb7 with H_Mey12 with H_Mey8) or
////    (^[DisgustInf] with M0 with M2 with M042 with M8 with M10 with M12 with M16 with H_Meb7 with H_Mli9 with H_Mli10) or
////    (^[AngerInf] with M0 with M1 with M2 with M3 with M041 with M042 with M12 with H_Meb7 with H_Mey8 with H_Mli9 with H_Mey12)
//
//  type MULTI_EMOTIONS_BINDING_TYPE = Emotion with EmotionInf with ALL_MUSCLES with ((EMOTIONS_BINDING_TYPE) and (EMOTIONS_BINDING_TYPE))
//  //type MULTI_EMOTIONS_BINDING_TYPE = Emotion with EmotionInf with MUSCLE_COMMON with ((MUSCLE_GROUPS) and (MUSCLE_GROUPS))
//
//  val multiDimModel = parse[MULTI_EMOTIONS_BINDING_TYPE](false)
//
//  val NoEmoId = 0
//  val JoyId = 1
//  val SurpriseId = 2
//  val FearId = 3
//  val SadnessId = 4
//  val DisgustId = 5
//  val AngerId = 6
//}
//
//import Emotions._
//
//@fragment
//trait Emotion {
//  private var emo1Lev: (Int, Float) = (0, 0)
//  def emo1Level() = {
//    val l = emo1Lev
//    l
//  }
//  def emo1Level_= (level: (Int, Float)) = {
//    emo1Lev = level
//  }
//
//  private var emo2Lev: (Int, Float) = (0, 0)
//  def emo2Level() = {
//    val l = emo2Lev
//    l
//  }
//  def emo2Level_= (level: (Int, Float)) = {
//    emo2Lev = level
//  }
//
//  def emoLevel(emoId: Int) =
//    if (emo1Level._1 == emoId)
//      Some(emo1Level._2)
//    else if (emo2Level._1 == emoId)
//      Some(emo2Level._2)
//    else None
//}
//
//@fragment
////@wrapper
//trait Joy {
//  this: Emotion =>
//  def joyLevel = emoLevel(JoyId)
//}
//
//@fragment
////@wrapper
//trait Surprise extends Emotion {
//  this: Emotion =>
//  def surpriseLevel = emoLevel(SurpriseId)
//}
//
//@fragment
////@wrapper
//trait Fear extends Emotion {
//  this: Emotion =>
//  def fearLevel = emoLevel(FearId)
//}
//
//@fragment
////@wrapper
//trait Sadness extends Emotion {
//  this: Emotion =>
//  def sadnessLevel = emoLevel(SadnessId)
//}
//
//@fragment
////@wrapper
//trait Disgust extends Emotion {
//  this: Emotion =>
//  def disgustLevel = emoLevel(DisgustId)
//}
//
//@fragment
////@wrapper
//trait Anger extends Emotion {
//  this: Emotion =>
//  def angerLevel = emoLevel(AngerId)
//}
//
//@fragment
//trait EmotionInf {
//
//  def influence(): List[String] = Nil
//}
//
//@fragment
//@wrapper
//trait JoyInf extends EmotionInf {
//  this: Joy with MuscleBase with TensionCalculator =>
//
//  private lazy val tcalc = tensionCalc("joy")(_)
//
//  override def influence() = {
//    val appliedMuscles = applyTension(tcalc, joyLevel)
//    "Joy" :: super.influence()
//  }
//}
//
//@fragment
//@wrapper
//trait SurpriseInf extends EmotionInf {
//  this: Surprise with MuscleBase with TensionCalculator =>
//
//  private lazy val tcalc = tensionCalc("surprise")(_)
//
//  override def influence() = {
//    val appliedMuscles = applyTension(tcalc, surpriseLevel)
//    "Surprise" :: super.influence()
//  }
//}
//
//@fragment
//@wrapper
//trait FearInf extends EmotionInf {
//  this: Fear with MuscleBase with TensionCalculator =>
//
//  private lazy val tcalc = tensionCalc("fear")(_)
//
//  override def influence() = {
//    val appliedMuscles = applyTension(tcalc, fearLevel)
//    "Fear" :: super.influence()
//  }
//}
//
//@fragment
//@wrapper
//trait SadnessInf extends EmotionInf {
//  this: Sadness with MuscleBase with TensionCalculator =>
//
//  private lazy val tcalc = tensionCalc("sadness")(_)
//
//  override def influence() = {
//    val appliedMuscles = applyTension(tcalc, sadnessLevel)
//    "Sadness" :: super.influence()
//  }
//}
//
//@fragment
//@wrapper
//trait DisgustInf extends EmotionInf {
//  this: Disgust with MuscleBase with TensionCalculator =>
//
//  private lazy val tcalc = tensionCalc("disgust")(_)
//
//  override def influence() = {
//    val appliedMuscles = applyTension(tcalc, disgustLevel)
//    "Disgust" :: super.influence()
//  }
//}
//
//@fragment
//@wrapper
//trait AngerInf extends EmotionInf {
//  this: Anger with MuscleBase with TensionCalculator =>
//
//  private lazy val tcalc = tensionCalc("anger")(_)
//
//  override def influence() = {
//    val appliedMuscles = applyTension(tcalc, angerLevel)
//    "Anger" :: super.influence()
//  }
//}