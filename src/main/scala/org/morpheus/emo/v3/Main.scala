package org.morpheus.emo.v3

import java.io.File

import org.morpheus._
import org.morpheus.Morpheus._

/**
  * Created by zslajchrt on 02/01/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val emoCtx = new EmotionsContext
    val musCtx = new MusclesContext(emoCtx.recognizer)
    val featCtx = new FeaturesContext(musCtx.recognizer)
    val featMorph = featCtx.recognizer.~

    def printInfo(): Unit = {
      println("Morph Alt: " + featMorph.myAlternative)
      println("Actual Emo Levels: " + featMorph.actualEmoLevels)
      println("Actual Contractions: " + featMorph.contractions)
      println("Actual Splines: " + featMorph.collectSplines.map(_.name))
    }

    println("Beginning")
    printInfo()

    emoCtx.emo1Lev = Some((Emotions.joy, 0.7f))
    emoCtx.emo2Lev = Some((Emotions.surprise, 0.2f))

    featMorph.remorph

    println("\n1st wave")
    printInfo()

    featMorph.remorph

    println("\n2nd wave:")
    printInfo()

    featMorph.remorph

    println("\n3rd wave:")
    printInfo()
  }

  def main2(args: Array[String]) {
    import Assembler._

//    println(emoCtx.morph.myAlternative)
//    println(musCtx.morph.myAlternative)
//    println(featCtx.morph.myAlternative)

//    emoCtx.emo1Lev = Some(2, 0.7f)
//    emoCtx.emo2Lev = Some(3, 0.7f)

//    musCtx.morph.remorph
//    println(musCtx.morph.emo1Lev)
//    println(musCtx.morph.emo2Lev)
//    println(musCtx.morph.myAlternative)

//    featCtx.morph.remorph
//    println(featCtx.morph.emo1Lev)
//    println(featCtx.morph.emo2Lev)
//    println(featCtx.morph.myAlternative)

//    val splines = featCtx.morph.collectSplines
//    val outFile = new File(s"/Users/zslajchrt/tmp/face.svg")
//    Features.flushSvg(splines, outFile)

    println("Features resolution: " + featCtx.model.alternatives.toList.size)
    println("Features alts: " + featCtx.model.alternatives.toList)

    for (emoId1 <- 0 to 6;
         emoId2 <- emoId1 to 6) {
//    {
//      emoCtx.emo1Lev = Some((emoId1, 1f))
//      emoCtx.emo2Lev = Some((emoId2, 1f))
//
      println(emoCtx.morph.myAlternative)

//      val emoId1 = 1
//      val emoId2 = 1

      emoCtx.emo1Lev = Some((emoId1, 1f))
      emoCtx.emo2Lev = Some((emoId2, 1f))

      println(featCtx.morph.myAlternative)
      flush(emoId1, emoId2, "0")
      // 1st morph wave - propagating the emo levels to the emotions tier
      featCtx.morph.remorph
      println(featCtx.morph.myAlternative)
      flush(emoId1, emoId2, "1")
      // 2nd morph wave - propagating muscle contractions to the muscles tier
      featCtx.morph.remorph
      println(featCtx.morph.myAlternative)
      flush(emoId1, emoId2, "2")
      // 3rd morph wave - propagating the feature warps to the features tier
      featCtx.morph.remorph
      println(featCtx.morph.myAlternative)
      flush(emoId1, emoId2, "3")

      // Despite the featCtx.morph is the highest tier, yet it is possible to invoke
      // the fragments from the underlying tiers.

      // The functionality of those underlying fragments may be overridden by wrappers from
      // the upper layers.

      featCtx.morph.delegate match {
        case joyFrag: JoyFragment =>
          //println("Joy: " + joyFrag.joyLevel)
        case _ =>
      }

      featCtx.morph.delegate match {
        case m2frag: M2Fragment =>
          //println("M2: " + m2frag.m2.spline)
        case _ =>
      }

      //    val emoId1 = 5
      //    val emoId2 = 6
      //em.setEmoLevels(Some((emoId1, 1f)), Some((emoId2, 1f)))
      //println(featCtx.morph.myAlternative)
    }

    def flush(emoId1: Int, emoId2: Int, postfix: String): Unit = {
      val splines = featCtx.morph.collectSplines
      val fileName: String = s"/Users/zslajchrt/tmp/face$emoId1$emoId2$postfix.svg"
      println(fileName)
      val outFile = new File(fileName)
      Features.flushSvg(splines, outFile)
    }
  }
}
