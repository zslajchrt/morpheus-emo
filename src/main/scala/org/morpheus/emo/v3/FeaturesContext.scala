package org.morpheus.emo.v3

import org.morpheus._
import org.morpheus.Morpheus._
import org.morpheus.emo.{Splines, TensionCalculator}

/**
  * Created by zslajchrt on 03/01/16.
  */
object FeaturesContext {
  // Actual type
  //  type FeaturesActual = Features.Features[FeatureBase with EmotionBaseProfiler with $[MuscleBase with EmotionBase] with
  //    /?[M7Profiler with $[M7Fragment]], EyebrowFragment with EyebrowWrapper, UpperLidFragment with UpperLidWrapper,
  //    LowerLidFragment with LowerLidWrapper, UpperLipJoinerFragment with UpperLipJoinerWrapper, UpperLipFragment with UpperLipWrapper,
  //    LowerLipFragment with LowerLipWrapper, LowerLipJoinerFragment with LowerLipJoinerWrapper]

  type FeaturesTypeActual = Features.FeaturesType[FeatureBase * $[MuscleBase with EmotionBase],
    EyebrowFragment * EyebrowWrapper, UpperLidFragment * UpperLidWrapper,
    LowerLidFragment * LowerLidWrapper, UpperLipJoinerFragment * UpperLipJoinerWrapper, UpperLipFragment * UpperLipWrapper,
    LowerLipFragment * LowerLipWrapper, LowerLipJoinerFragment * LowerLipJoinerWrapper]

}

//class FeaturesContext(musRef: &[MuscleBase with /?[M7Fragment] with EmotionBase]) extends TensionCalculator {
class FeaturesContext(musRef: &[MuscleBase with EmotionBase]) extends TensionCalculator {

  import FeaturesContext._

  // The outer object
  private var warpsMap: Map[String, FeatureWarp] = Map.empty

  // Raw reference to the source morph: TODO: document the *! macro
  //V1: val musMorph = *!(musRef)


  // Morph reference to the source morph
  val featuresRef: &[$[FeaturesTypeActual#Features]] = *(musRef)
  // Morph model and the default strategy TODO: document the unveil macro
  val (model, defaultStrategy) = unveil(featuresRef)

  def featureSel(featureName: String): (Option[model.ImmutableLUB]) => Double = (_) =>
    if (warpsMap.contains(featureName)) {
      1d
    } else {
      0d
    }

  // Strategy chain
  val strategy: MorphingStrategy[model.Model] = {

    var strat: MorphingStrategy[model.Model] = defaultStrategy

    strat = rateFragment[EyebrowFragment](model)(strat, featureSel("eyebrow"))
    strat = rateFragment[UpperLidFragment](model)(strat, featureSel("upperlid"))
    strat = rateFragment[LowerLidFragment](model)(strat, featureSel("lowerlid"))
    strat = rateFragment[UpperLipJoinerFragment](model)(strat, featureSel("upperlipjoiner"))
    strat = rateFragment[UpperLipFragment](model)(strat, featureSel("upperlip"))
    strat = rateFragment[LowerLipFragment](model)(strat, featureSel("lowerlip"))
    strat = rateFragment[LowerLipJoinerFragment](model)(strat, featureSel("lowerlipjoiner"))

    new FeaturesStrategy(strat)

  }

  class FeaturesStrategy(prevStrat: MorphingStrategy[model.Model]) extends model.Strategy {
    override def chooseAlts(instance: model.Recognizer)(morphProxy: Option[instance.ImmutableLUB]): Alternatives[instance.Model] = {
      warpsMap = {
        //V1: val mm = musMorph.remorph
        morphProxy match {
          case None => Map.empty
          case Some(mm) =>
            val contractions: Map[String, (Float, Float)] = mm.contractions
            createWarpMap(contractions)
        }
      }
      prevStrat.chooseAlternatives(instance)(morphProxy)
    }
  }

  // Recognizer using one custom fragment factory
  val recognizer = *(featuresRef, strategy, frag[FeatureBase, FeatureBaseInit](FeatureBaseInitData(warpsMap)))

  // Mutable morph
  val morph = recognizer.~
}

@fragment @wrapper
trait EmotionBaseProfiler extends EmotionBase {
  override def actualEmoLevels: List[(Int, Float)] = {
    val res = super.actualEmoLevels
    println("Profiling: " + res)
    res
  }
}

@fragment @wrapper
trait M7Profiler extends M7Fragment {
  this: MuscleBase =>

  override def xxx(): Int = {
    println("Calling XXX")
    super.xxx()
  }
}