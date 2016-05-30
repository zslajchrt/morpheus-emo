//package org.morpheus.emo
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.Emotions._
//import org.morpheus.emo.Features.MUSCLES_BINDING_TYPE
//
//
///**
// * Created by zslajchrt on 11/11/15.
// */
//
//object Main {
//
//  val emoStateStrategy1 = unmaskAll(rootStrategy(Emotions.multiDimModel))
//  val emoStateStrategy2a = maskFull_+[BASIC_MODEL_TYPE](Emotions.multiDimModel)(emoStateStrategy1, {
//    case None => None
//    case Some(proxy) => Some(proxy.emo1Level._1)
//  })
//  val emoStateStrategy2b = maskFull_+[BASIC_MODEL_TYPE](Emotions.multiDimModel)(emoStateStrategy2a, {
//    case None => None
//    case Some(proxy) => Some(proxy.emo2Level._1)
//  })
//  val emoStateStrategy3 = strict(emoStateStrategy2b)
//  val emoState = singleton(Emotions.multiDimModel, emoStateStrategy3)
//
//  val featuresFactoriesKernel = singleton_?[Feature or Eyebrow or UpperLidFragment or UpperLid or LowerLid or LowerLipJoiner or LowerLipFragment or LowerLip or UpperLip or UpperLipJoiner or UpperLipJoinerFragment or UpperLipFragment]
//  val featuresFactories = tupled(featuresFactoriesKernel)
//
//  val featuresRef: ~&[$[MUSCLES_BINDING_TYPE] with Emotion with EmotionInf] = emoState
//  val featuresKernel = *(featuresRef, featuresFactories)
//  //val featuresStrategy = RatingStrategy(featuresKernel.defaultStrategy, Features.ratingOperators)
//  //val features = featuresKernel.morph_~(featuresStrategy)
//  val features = featuresKernel.~
//
//  def main(args: Array[String]) {
//
//    Features.bindFeatures()
//  }
//
//}
