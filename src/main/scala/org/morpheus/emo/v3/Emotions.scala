package org.morpheus.emo.v3

import org.morpheus._
import org.morpheus.Morpheus._

object Emotions {

  type EmotionsType[Base, Joy, Surprise, Fear, Sadness, Disgust, Anger] = {
    type Emotions1D = (Unit | (Joy) | (Surprise) | (Fear) | (Sadness) | (Disgust) | (Anger))
    type Emotions2D = Emotions1D * Emotions1D
    type Emotions = Base * Emotions2D
  }

  val nothing = 0
  val joy = 1
  val surprise = 2
  val fear = 3
  val sadness = 4
  val disgust = 5
  val anger = 6

  val EmotionNames = Map(joy -> "joy", surprise -> "surprise", fear -> "fear", sadness -> "sadness",
    disgust -> "disgust", anger -> "anger")

  val EmotionBigNames = Map(joy -> "Joy", surprise -> "Surprise", fear -> "Fear", sadness -> "Sadness",
    disgust -> "Disgust", anger -> "Anger")
}

import Emotions._

trait EmotionBaseInit {
  val initEmoLevels: Map[Int, Float]
}

case class TwoEmotionsInitData(emo1Lev: Option[(Int, Float)], emo2Lev: Option[(Int, Float)]) extends EmotionBaseInit {
  override val initEmoLevels: Map[Int, Float] = (emo1Lev.toSet ++ emo2Lev.toSet).toMap
}

@fragment
trait EmotionBase extends dlg[EmotionBaseInit] {
  def actualEmoLevels: List[(Int, Float)] = Nil
}

@fragment
trait JoyFragment {
  this: EmotionBase =>

  def joyLevel = initEmoLevels(joy)
}

@fragment @wrapper
trait JoyWrapper extends EmotionBase {
  this: JoyFragment =>

  override def actualEmoLevels: List[(Int, Float)] = (joy, joyLevel) :: super.actualEmoLevels
}

@fragment
trait SurpriseFragment {
  this: EmotionBase =>

  def surpriseLevel = initEmoLevels(surprise)
}

@fragment @wrapper
trait SurpriseWrapper extends EmotionBase {
  this: SurpriseFragment =>

  override def actualEmoLevels: List[(Int, Float)] = (surprise, surpriseLevel) :: super.actualEmoLevels
}

@fragment
trait FearFragment {
  this: EmotionBase =>

  def fearLevel = initEmoLevels(fear)
}

@fragment @wrapper
trait FearWrapper extends EmotionBase {
  this: FearFragment =>

  override def actualEmoLevels: List[(Int, Float)] = (fear, fearLevel) :: super.actualEmoLevels
}

@fragment
trait SadnessFragment {
  this: EmotionBase =>

  def sadnessLevel = initEmoLevels(sadness)
}

@fragment @wrapper
trait SadnessWrapper extends EmotionBase {
  this: SadnessFragment =>

  override def actualEmoLevels: List[(Int, Float)] = (sadness, sadnessLevel) :: super.actualEmoLevels
}

@fragment
trait DisgustFragment {
  this: EmotionBase =>

  def disgustLevel = initEmoLevels(disgust)
}

@fragment @wrapper
trait DisgustWrapper extends EmotionBase {
  this: DisgustFragment =>

  override def actualEmoLevels: List[(Int, Float)] = (disgust, disgustLevel) :: super.actualEmoLevels
}

@fragment
trait AngerFragment {
  this: EmotionBase =>

  def angerLevel = initEmoLevels(anger)
}

@fragment @wrapper
trait AngerWrapper extends EmotionBase {
  this: AngerFragment =>

  override def actualEmoLevels: List[(Int, Float)] = (anger, angerLevel) :: super.actualEmoLevels
}
