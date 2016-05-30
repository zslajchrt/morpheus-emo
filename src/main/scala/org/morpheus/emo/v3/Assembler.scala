package org.morpheus.emo.v3

import org.morpheus._
import org.morpheus.Morpheus._

/**
  * Created by zslajchrt on 26/12/15.
  */
object Assembler {

  // the morph layers are loosely coupled by morph references

//  val emoCtx = new EmotionsContext
//  val musCtx = new MusclesContext(emoCtx.morph)
//  val featCtx = new FeaturesContext(musCtx.morph)

    val emoCtx = new EmotionsContext
    val musCtx = new MusclesContext(emoCtx.recognizer)
    val featCtx = new FeaturesContext(musCtx.recognizer)
}
