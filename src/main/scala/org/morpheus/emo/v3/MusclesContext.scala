package org.morpheus.emo.v3

import org.morpheus._
import org.morpheus.Morpheus._
import org.morpheus.emo.TensionCalculator

/**
  * Created by zslajchrt on 02/01/16.
  */
object MusclesContext {
  // Actual morph type
  type MusclesTypeActual = Muscles.MusclesType[MuscleBase with $[EmotionBase], M0Fragment with M0Wrapper, M1Fragment with M1Wrapper, M2Fragment with M2Wrapper, M3Fragment with M3Wrapper, M041Fragment with M041Wrapper, M042Fragment with M042Wrapper, M5Fragment with M5Wrapper, M6Fragment with M6Wrapper, M7Fragment with M7Wrapper, M8Fragment with M8Wrapper, M9Fragment with M9Wrapper, M10Fragment with M10Wrapper, M11Fragment with M11Wrapper, M12Fragment with M12Wrapper, M14Fragment with M14Wrapper, M15Fragment with M15Wrapper, M16Fragment with M16Wrapper, H_Meb6Fragment with H_Meb6Wrapper, H_Meb7Fragment with H_Meb7Wrapper, H_Mey8Fragment with H_Mey8Wrapper, H_Mli9Fragment with H_Mli9Wrapper, H_Mli10Fragment with H_Mli10Wrapper, H_Mli11Fragment with H_Mli11Wrapper, H_Mey12Fragment with H_Mey12Wrapper, H_TeethLowerFragment with H_TeethLowerWrapper, H_TeethUpperFragment with H_TeethUpperWrapper]
}

import MusclesContext._

//class MusclesContext(emoMorph: EmotionsContext.model.MutableLUB) extends TensionCalculator {
class MusclesContext(emoRef: &[EmotionBase]) extends TensionCalculator {
  // The outer object
  private var tensionsMap: Map[String, Float] = Map.empty

  // Raw reference to the source morph: TODO: document the *! macro
  //val emoMorph = *!(emoRef)


  // Morph reference to the source morph with placeholders
  val musclesRef: &[$[MusclesTypeActual#Muscles]] = *(emoRef)
  // Morph model
  val (model, defaultStrategy) = unveil(musclesRef)

  // Strategy chain
  val strategy: MorphingStrategy[model.Model] = {

    def muscleSel(muscleName: String): (Option[model.ImmutableLUB]) => Double = (_) =>
      if (tensionsMap.contains(muscleName)) {
        1d
      } else {
        0d
      }

    var strat: MorphingStrategy[model.Model] = defaultStrategy

    strat = rateFragment[M0Fragment](model)(strat, muscleSel("m0"))
    strat = rateFragment[M1Fragment](model)(strat, muscleSel("m1"))
    strat = rateFragment[M2Fragment](model)(strat, muscleSel("m2"))
    strat = rateFragment[M3Fragment](model)(strat, muscleSel("m3"))
    strat = rateFragment[M041Fragment](model)(strat, muscleSel("m041"))
    strat = rateFragment[M042Fragment](model)(strat, muscleSel("m042"))
    strat = rateFragment[M5Fragment](model)(strat, muscleSel("m5"))
    strat = rateFragment[M6Fragment](model)(strat, muscleSel("m6"))
    strat = rateFragment[M7Fragment](model)(strat, muscleSel("m7"))
    strat = rateFragment[M8Fragment](model)(strat, muscleSel("m8"))
    strat = rateFragment[M9Fragment](model)(strat, muscleSel("m9"))
    strat = rateFragment[M10Fragment](model)(strat, muscleSel("m10"))
    strat = rateFragment[M11Fragment](model)(strat, muscleSel("m11"))
    strat = rateFragment[M12Fragment](model)(strat, muscleSel("m12"))
    strat = rateFragment[M14Fragment](model)(strat, muscleSel("m14"))
    strat = rateFragment[M15Fragment](model)(strat, muscleSel("m15"))
    strat = rateFragment[M16Fragment](model)(strat, muscleSel("m16"))
    strat = rateFragment[H_Meb6Fragment](model)(strat, muscleSel("h_meb6"))
    strat = rateFragment[H_Meb7Fragment](model)(strat, muscleSel("h_meb7"))
    strat = rateFragment[H_Mey8Fragment](model)(strat, muscleSel("h_mey8"))
    strat = rateFragment[H_Mey12Fragment](model)(strat, muscleSel("h_mey12"))
    strat = rateFragment[H_Mli9Fragment](model)(strat, muscleSel("h_mli9"))
    strat = rateFragment[H_Mli10Fragment](model)(strat, muscleSel("h_mli10"))
    //strat = rateFragment[H_Mli11Fragment](musclesModel)(strat, muscleSel("h_mli11"))
    strat = rateFragment[H_TeethLowerFragment](model)(strat, muscleSel("h_teethlower"))
    strat = rateFragment[H_TeethUpperFragment](model)(strat, muscleSel("h_teethupper"))

    new MusclesStrategy(strat)
  }

  class MusclesStrategy(prevStrat: MorphingStrategy[model.Model]) extends model.Strategy {
    override def chooseAlts(instance: model.Recognizer)(morphProxy: Option[instance.ImmutableLUB]): Alternatives[instance.Model] = {
      // update the tensions map in the context
      MusclesContext.this.tensionsMap = {
        //val em = emoMorph.remorph // refresh the source morph
        morphProxy match {
          case None => Map.empty
          case Some(em) => createTensionsMap(em.actualEmoLevels)
        }
      }
      prevStrat.chooseAlternatives(instance)(morphProxy)
    }

  }

  // Recognizer using with one custom fragment factory
  val recognizer = *(musclesRef, strategy, frag[MuscleBase, MuscleBaseInit](MuscleBaseInitData(tensionsMap)))

  // Mutable morph
  //val morph = recognizer.morph_~(strategy)
  val morph = recognizer.~

}
