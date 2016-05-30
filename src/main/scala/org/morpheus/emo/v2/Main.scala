//package org.morpheus.emo.v2
//
//import java.io.{File, FileWriter}
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.{Spline, SplineStyle, TensionCalculator}
//
///**
//  * Created by zslajchrt on 20/12/15.
//  */
//object Main {
//
//  // Emotions Tier
//
//  //  val musclesModel = parse[musclesTier.Muscles](true) // the deps check takes a long time
//
//
//  import Assembler._
//
//
//  def main(args: Array[String]) {
//
//    //    val tc = new TensionCalculator {
//    //
//    //    }
//    //
//    //    for (mf <- tc.muscleToFeatures) {
//    //      print(mf._1 + ": ")
//    //      println(mf._2.mkString("this: ", " with ", " =>"))
//    //    }
//
//
//    //    for (fd <- musclesModel.fragmentDescriptorsList) {
//    //      println(s"${musclesModel.sec2prim(fd.index)}/${fd.index}:${fd.fragTag.tpe}")
//    //    }
//    //
//
//    val em = emotionsRec.morph_~(emoStrategy())
//    //em.setEmoLevels(Some(1, 0.4f), Some((4, 0.9f)))
//    for (emoId1 <- 0 to 6;
//         emoId2 <- emoId1 to 6) {
//      //    val emoId1 = 5
//      //    val emoId2 = 6
//      em.setEmoLevels(Some((emoId1, 1f)), Some((emoId2, 1f)))
//      println(em.myAlternative)
//      val splines = em.collectSplines
//      val outFile = new File(s"/Users/zslajchrt/tmp/face$emoId1$emoId2.svg")
//      Features.flushSvg(splines, outFile)
//    }
//    //    println(s"Emotion Tier Resolution: ${emotionsRef.instance.model.altIterator().toList.size}")
//    //    println(s"Muscle Tier Resolution: ${musclesModel.altIterator().toList.size}")
//    //
//    //    val emotionsRec = singleton(emotionsModel, emoStrategy())
//    //    val emotions = emotionsRec.~
//    //
//    //    emotions.emo1Level_=((1, 0.4f))
//    //    emotions.emo2Level_=((2, 0.9f))
//    //    emotions.remorph
//    //    println(emotions.myAlternative)
//    //
//    //    val musclesRec = singleton(musclesModel, Emotions2Muscles(rootStrategy(musclesModel), emotions))
//    //    val muscles = musclesRec.~
//    //
//    //    muscles.remorph
//    //    println(muscles.myAlternative)
//    //
//    //    val a: &[$[\?[M0Inf] with \?[M1Inf] with \?[M2Inf]]] = musclesRec
//
//  }
//
//}
