package org.morpheus.emo.v3

import org.morpheus._
import org.morpheus.Morpheus._
import org.morpheus.emo.TensionCalculator

/**
  * Created by zslajchrt on 11/11/15.
  */


object EmotionsContext {
  // Actual morph type
  type EmoTypesActual = Emotions.EmotionsType[EmotionBase, JoyFragment * JoyWrapper, SurpriseFragment * SurpriseWrapper,
    FearFragment * FearWrapper, SadnessFragment * SadnessWrapper, DisgustFragment * DisgustWrapper,
    AngerFragment * AngerWrapper]

  // Morph model
  val model = parse[EmoTypesActual#Emotions](true)
}

class EmotionsContext {
  // The "object" to be recognized, i.e. the states of the fragments are initialized from it
  var emo1Lev: Option[(Int, Float)] = None
  var emo2Lev: Option[(Int, Float)] = None

  import EmotionsContext._

  // Strategy chain

  val strategy: MorphingStrategy[model.Model] = {
    var strat: MorphingStrategy[model.Model] = unmaskAll(rootStrategy(model))

    strat = maskFull_+[EmoTypesActual#Emotions1D](model)(strat, _ => {
      for (el <- emo1Lev) yield el._1
    })

    strat = maskFull_+[EmoTypesActual#Emotions1D](model)(strat, _ => {
      for (el <- emo2Lev) yield el._1
    })

    strat = strict(strat)

    strat
  }

  // Overriding fragment factory
  implicit val emoBaseFact = frag[EmotionBase, EmotionBaseInit](TwoEmotionsInitData(emo1Lev, emo2Lev))
  // Recognizer
  val recognizer = compose(model, strategy)

  // Mutable morph
  val morph = recognizer.~

}

