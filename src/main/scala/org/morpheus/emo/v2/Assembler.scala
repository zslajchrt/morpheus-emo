//package org.morpheus.emo.v2
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.TensionCalculator
//
///**
//  * Created by zslajchrt on 20/12/15.
//  */
//object Assembler extends TensionCalculator {
//
//  val emotionsTier = new Emotions[Unit, Joy with JoyWrapper, Surprise with SurpriseWrapper, Fear with FearWrapper,
//    Sadness with SadnessWrapper, Disgust with DisgustWrapper, Anger with AngerWrapper]
//  //  val emotionsModel = parse[emotionsTier.Emotions](true)
//
//  // Muscles Tier
//
//
////  val musclesTier = new Muscles[Unit, M0, M1, M2, M3, M041, M042, M5, M6, M7, M8, M9, M10, M11, M12, M14, M15, M16, H_Meb6, H_Meb7, H_Mey8, H_Mli9, H_Mli10, H_Mli11, H_Mey12, H_TeethLower, H_TeethUpper]
////  val musclesTier = new Muscles[Unit, M0 with M0Wrapper, M1 with M1Wrapper, M2 with M2Wrapper, M3 with M3Wrapper, M041 with M041Wrapper, M042 with M042Wrapper, M5 with M5Wrapper,
////    M6 with M6Wrapper, M7 with M7Wrapper, M8 with M8Wrapper, M9 with M9Wrapper, M10 with M10Wrapper, M11 with M11Wrapper,
////    M12 with M12Wrapper, M14 with M14Wrapper, M15 with M15Wrapper, M16 with M16Wrapper, H_Meb6 with H_Meb6Wrapper,
////    H_Meb7 with H_Meb7Wrapper, H_Mey8 with H_Mey8Wrapper, H_Mli9 with H_Mli9Wrapper, H_Mli10 with H_Mli10Wrapper, H_Mli11 with H_Mli11Wrapper, H_Mey12 with H_Mey12Wrapper,
////    H_TeethLower with H_TeethLowerWrapper, H_TeethUpper with H_TeethUpperWrapper]
//  //val musclesTier = new Muscles[MuscleBase, M0 with M0Wrapper,M1 with M1Wrapper,M2 with M2Wrapper,M3 with M3Wrapper,M041 with M041Wrapper,M042 with M042Wrapper,M5 with M5Wrapper,M6 with M6Wrapper,M7 with M7Wrapper,M8 with M8Wrapper,M9 with M9Wrapper,M10 with M10Wrapper,M11 with M11Wrapper,M12 with M12Wrapper,M14 with M14Wrapper,M15 with M15Wrapper,M16 with M16Wrapper,H_Meb6 with H_Meb6Wrapper,H_Meb7 with H_Meb7Wrapper,H_Mey8 with H_Mey8Wrapper,H_Mli9 with H_Mli9Wrapper,H_Mli10 with H_Mli10Wrapper,H_Mli11 with H_Mli11Wrapper,H_Mey12 with H_Mey12Wrapper,H_TeethLower with H_TeethLowerWrapper,H_TeethUpper with H_TeethUpperWrapper]
//  //val musclesTier = new Muscles[MuscleBase, M0F with M0W,M1F with M1W,M2F with M2W,M3F with M3W,M041F with M041W,M042F with M042W,M5F with M5W,M6F with M6W,M7F with M7W,M8F with M8W,M9F with M9W,M10F with M10W,M11F with M11W,M12F with M12W,M14F with M14W,M15F with M15W,M16F with M16W,H_Meb6F with H_Meb6W,H_Meb7F with H_Meb7W,H_Mey8F with H_Mey8W,H_Mli9F with H_Mli9W,H_Mli10F with H_Mli10W,H_Mli11F with H_Mli11W,H_Mey12F with H_Mey12W,H_TeethLowerF with H_TeethLowerW,H_TeethUpperF with H_TeethUpperW]
//  val musclesTier = new Muscles[MuscleBase, M0F,M1F,M2F,M3F,M041F,M042F,M5F,M6F,M7F,M8F,M9F,M10F,M11F,M12F,M14F,M15F,M16F,H_Meb6F,H_Meb7F,H_Mey8F,H_Mli9F,H_Mli10F,H_Mli11F,H_Mey12F,H_TeethLowerF,H_TeethUpperF]
//  //val musclesTier = new Muscles
//
//  val featuresRec = singleton[Features.Features]
//
//  val musclesRef: &[$[musclesTier.Muscles] with FeatureBase] = featuresRec
//  val musclesRec = *(musclesRef)
//
//  val emotionsRef: &[$[emotionsTier.Emotions] with MuscleBase with FeatureBase] = musclesRec
//  val emotionsRec = *(emotionsRef)
//  val emotionsModel = emotionsRec.model
//
//  def emoStrategy() = {
//    val emoStateStrategy0 = unmaskAll(emotionsRec.defaultStrategy)
//    val emoStateStrategy1 = maskFragment[EmotionBase](emotionsModel)(emoStateStrategy0, (p) => true)
//
//    val emoStateStrategy2a = maskFull_+[emotionsTier.Emotions1D](emotionsModel)(emoStateStrategy1, {
//      case None => Some(Emotions.nothing)
//      case Some(proxy) => for (el <- proxy.getEmo1Level) yield el._1
//    })
//    val emoStateStrategy2b = maskFull_+[emotionsTier.Emotions1D](emotionsModel)(emoStateStrategy2a, {
//      case None => Some(Emotions.nothing)
//      case Some(proxy) => for (el <- proxy.getEmo2Level) yield el._1
//    })
//    val emoStateStrategy3 = strict(emoStateStrategy2b)
//    emoStateStrategy3
//  }
//
//  import Main._
//
////  def apply(prevStrat: MorphingStrategy[musclesModel.Model], emotions: Emotion): MorphingStrategy[musclesModel.Model] = {
////
////    def muscleSel(muscleName: String): (Option[musclesModel.ImmutableLUB]) => Boolean = morph => {
////      val emo1 = EmotionNames(emotions.emo1Level()._1)
////      val emo2 = EmotionNames(emotions.emo2Level()._1)
////      val stimulated: Set[String] = stimulatedMuscles(emo1) ++ stimulatedMuscles(emo2)
////      stimulated.contains(muscleName)
////    }
////
////    var strat = prevStrat
////    strat = unmaskAll(strat)
////
////    strat = maskFragment[M0](musclesModel)(strat, muscleSel("m0"))
////    strat = maskFragment[M1](musclesModel)(strat, muscleSel("m1"))
////    strat = maskFragment[M2](musclesModel)(strat, muscleSel("m2"))
////    strat = maskFragment[M3](musclesModel)(strat, muscleSel("m3"))
////    strat = maskFragment[M041](musclesModel)(strat, muscleSel("m041"))
////    strat = maskFragment[M042](musclesModel)(strat, muscleSel("m042"))
////    strat = maskFragment[M5](musclesModel)(strat, muscleSel("m5"))
////    strat = maskFragment[M6](musclesModel)(strat, muscleSel("m6"))
////    strat = maskFragment[M7](musclesModel)(strat, muscleSel("m7"))
////    strat = maskFragment[M8](musclesModel)(strat, muscleSel("m8"))
////    strat = maskFragment[M9](musclesModel)(strat, muscleSel("m9"))
////    strat = maskFragment[M10](musclesModel)(strat, muscleSel("m10"))
////    strat = maskFragment[M11](musclesModel)(strat, muscleSel("m11"))
////    strat = maskFragment[M12](musclesModel)(strat, muscleSel("m12"))
////    strat = maskFragment[M14](musclesModel)(strat, muscleSel("m14"))
////    strat = maskFragment[M15](musclesModel)(strat, muscleSel("m15"))
////    strat = maskFragment[M16](musclesModel)(strat, muscleSel("m16"))
////    strat = maskFragment[H_Meb6](musclesModel)(strat, muscleSel("h_meb6"))
////    strat = maskFragment[H_Meb7](musclesModel)(strat, muscleSel("h_meb7"))
////    strat = maskFragment[H_Mey8](musclesModel)(strat, muscleSel("h_mey8"))
////    strat = maskFragment[H_Mey12](musclesModel)(strat, muscleSel("h_mey12"))
////    strat = maskFragment[H_Mli9](musclesModel)(strat, muscleSel("h_mli9"))
////    strat = maskFragment[H_Mli10](musclesModel)(strat, muscleSel("h_mli10"))
////    //strat = maskFragment[H_Mli11](musclesModel)(strat, muscleSel("h_mli11"))
////    strat = maskFragment[H_TeethLower](musclesModel)(strat, muscleSel("h_teethlower"))
////    strat = maskFragment[H_TeethUpper](musclesModel)(strat, muscleSel("h_teethupper"))
////    strat = strict(strat)
////    strat
////  }
//
//}
//
