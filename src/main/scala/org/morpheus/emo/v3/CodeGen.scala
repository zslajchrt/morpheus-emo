package org.morpheus.emo.v3

import org.morpheus.emo.TensionCalculator

import scala.collection.immutable.Seq

/**
  * Created by zslajchrt on 21/12/15.
  */
object CodeGen extends TensionCalculator {

//  def stimulatedMuscles(emotionName: String) = {
//    emoInfMap.getOrElse(emotionName, Map.empty).keySet.intersect(Muscles.MuscleNames.keySet).toList
//  }
//
//  def generateStimulation(emotionId: Int): Unit = {
//    val emotionName = Emotions.EmotionNames(emotionId)
//
//    val muscles: List[String] = stimulatedMuscles(emotionName)
//
//    val self = muscles.map(Muscles.MuscleNames(_)).map(_ + "F").mkString("this: TensionCalculator with EmotionBase with ", " with ", " =>")
//
//    val lines: List[String] = for (muscle <- muscles) yield {
//      s"$muscle.updateTension(tCal($muscle)(${emotionName}Level))"
//    }
//
//    val emoTypeName: String = Emotions.EmotionBigNames(emotionId)
//
//    println(
//      s"""
//@fragment
//trait $emoTypeName {
//  $self
//
//  def stimulate$emoTypeName(): Unit = {
//     val ${emotionName}Level = getEmoLevel($emotionName).get
//     val tCal = tensionCalc($emotionName)
//     ${lines.mkString("\n   ")}
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait ${emoTypeName}Wrapper extends Stimulator {
//this: $emoTypeName =>
//
//   override def stimulateAll(): Unit = {
//      stimulate$emoTypeName()
//      super.stimulateAll()
//   }
//}
//        """)
//
//    println()
//  }

//  lazy val muscleToFeatures: Map[String, Set[String]] = {
//    val f2m: Set[(String, Set[String])] = featInfMap.toList.map(e0 => (e0._1, e0._2.keySet)).toSet
//    val s: Set[(String, String)] = f2m.flatMap[(String, String), Set[(String, String)]](e1 => e1._2.map(e2 => (e1._1, e2)))
//    s.groupBy(_._2).map(e => (e._1, e._2.map(_._1)))
//  }

//  val muscleToFeatureWeights: Map[String, Map[String, Seq[(Int, Float)]]] = {
//    val m2f = muscleToFeatures
//
//    for ((m, ff) <- m2f) yield (m, {
//      (for (f <- ff;
//           infMapForMuscle: Seq[(Int, Float)] = featInfMap(f)(m)
//      ) yield (f, infMapForMuscle)).toMap
//    })
//  }

  def generateContractions(muscleId: String): Unit = {

    val muscleIdent: String = Muscles.MuscleNames(muscleId)

    println(
      s"""
@fragment @nodepscheck
trait ${muscleIdent}Fragment {
   this: MuscleBase =>

   lazy val $muscleId = new Muscle(Muscles.${muscleId}Spline, this)
}

@fragment @wrapper @nodepscheck
trait ${muscleIdent}Wrapper extends MuscleBase {
   this: ${muscleIdent}Fragment =>

   override def contractions: Map[String, (Float, Float)] = super.contractions + ("$muscleId" -> $muscleId.contraction())

}
    """)

  }

  def generateAllContractions(): Unit = {
    for (m <- Muscles.MuscleNames.keys.toList.sorted) {
      generateContractions(m)
    }
  }

  def generateMuscleTypes(): Unit = {
    println(s"[MuscleBase, ${Muscles.MuscleTypeNames.map(m => s"${m}Fragment with ${m}Wrapper").mkString(",")}]")
  }

  def main(args: Array[String]) {
    generateMuscleTypes

    generateAllContractions

//      generateStimulation(Emotions.joy)
//      generateStimulation(Emotions.surprise)
//      generateStimulation(Emotions.fear)
//      generateStimulation(Emotions.sadness)
//      generateStimulation(Emotions.disgust)
//      generateStimulation(Emotions.anger)
  }

}
