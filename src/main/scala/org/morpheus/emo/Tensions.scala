package org.morpheus.emo

import org.morpheus.emo.Tensions.TensionCalculation
import org.morpheus._
import org.morpheus.Morpheus._
import org.morpheus.emo.v3.{FeatureWarp, Emotions, Muscle, EmotionsContext}

import scala.collection.immutable.Seq
import scala.xml.{NodeSeq, Node, XML}

/**
 * Created by zslajchrt on 15/11/15.
 */
object Tensions {

  type TensionCalculation = (String) => (Float => Float)

  def main(args: Array[String]): Unit = {
    val xml = XML.loadFile("/Users/zslajchrt/Sites/meta/emotions.xml")
    val x = (xml \\ "facedata" \\ "emotion").foreach(em => {
      println((em \ "@label").text)
      (em \ "addinfluence").foreach(inf => {
        println((inf \ "@muscle").text)
      })
    })
  }
}

@fragment
trait TensionCalculator {

  val emoInfMap = {
    val emoXml = XML.loadFile("/Users/zslajchrt/Sites/meta/emotions.xml")
    (for (em <- emoXml \\ "facedata" \\ "emotion";
         emLabel = (em \ "@label").text)
      yield (emLabel, createMuscleMappings(em))).toMap
  }

  val featInfMap: Map[String, Map[String, Map[Int, Float]]] = {
    val featXml = XML.loadFile("/Users/zslajchrt/Sites/meta/features.xml")
    (for (fn <- featXml \\ "facedata" \\ "feature" \\ "segment";
          fLabel = (fn \ "@label").text)
      yield (fLabel, createFeatureMappings(fn))).toMap
  }

  def createMuscleMappings(emotionNode: Node): Map[String, (Int, Float => Float)] = {
    (for (inf <- emotionNode \ "addinfluence";
         muscleLabel = (inf \ "@muscle").text)
      yield (muscleLabel, createMuscleMapping(inf))).toMap

  }

  def createFeatureMappings(featureNode: Node): Map[String, Map[Int, Float]] = {
    (for (inf <- featureNode \ "addmuscle";
          muscleLabel = (inf \ "@muscle").text;
          nodeNum = (inf \ "@nodenum").text.toInt;
          weight = (inf \ "@weight").text.toFloat)
      //yield (muscleLabel, nodeNum, weight)).groupBy(_._1).map(e1 => (e1._1, e1._2.map(e2 => (e2._2, e2._3))))
      yield (muscleLabel, nodeNum, weight)).groupBy(_._1).map(e1 => (e1._1, {
      val triplets: Seq[(String, Int, Float)] = e1._2
      triplets.map(e2 => (e2._2, e2._3)).toMap
    }))
  }

  def createMuscleMapping(influenceNode: Node): (Int, Float => Float) = {
    val mapNode = (influenceNode \ "mapping").head
    val mapType: String = (mapNode \ "@type").text
    val priorityAttr = influenceNode \ "@priority"
    val priority: Int = if (priorityAttr.isEmpty) 1 else priorityAttr.text.toInt
    val infFn = mapType match {
      case "polynomial" => PolyMapping(mapNode)
      case "sine" => SineMapping(mapNode)
      case "gauss" => GaussMapping(mapNode)
      case _ => sys.error(s"Unknown mapping type $mapType")
    }
    (priority, infFn)
  }

  def performCalc(mappingNode: Node, emotionLevel: Option[Float]): Float = {
    // todo:
    0
  }

  def createTensionsMap(emoPairs: List[(Int, Float)]): Map[String, Float] = {
    val emoStim: List[List[(String, Float, Float)]] = for (e <- emoPairs) yield {
      val (emoId, emoLevel) = e
      val tcalcMap: Map[String, (Int, (Float) => Float)] = tensionCalcMap(emoId)
      tcalcMap.map(e => {
        val weightedPriority: Float = emoLevel * e._2._1
        val rawTension: Float = e._2._2(emoLevel)
        val muscId: String = e._1
        (muscId, rawTension, weightedPriority)
      }).toList
    }
    val muscStims: Map[String, List[(String, Float, Float)]] = emoStim.flatMap(p => p).groupBy(_._1)
    val muscStimFractions: Map[String, (Float, Float)] = muscStims.map(e => (e._1, e._2.foldLeft((0f, 0f))((r, x) => {
      val (_, rawTension, weightedPriority) = x
      (r._1 + weightedPriority * rawTension, r._2 + weightedPriority)
    })))
    muscStimFractions.map(e => (e._1, {
      val rawTensionSum: Float = e._2._1
      val weightedPrioritySum: Float = e._2._2
      rawTensionSum / weightedPrioritySum
    }))
  }

  def createWarpMap(contractions: Map[String, (Float, Float)]): Map[String, FeatureWarp] = {
    for ((featId: String, m2f: Map[String, Map[Int, Float]]) <- featInfMap) yield {

      var featureWarp = new FeatureWarp(Map.empty)

      for ((m: String, inf: Map[Int, Float]) <- m2f;
           contraction <- contractions.get(m)) {
        val delta: Map[Int, (Float, Float)] = Splines.transform(contraction, inf)
        featureWarp = featureWarp.update(delta)
      }

      (featId, featureWarp)
    }
  }

  //  def tensionCalc(emotionId: Int): TensionCalculation = {
//    tensionCalc(Emotions.EmotionNames(emotionId))
//  }
//
//  def tensionCalc(emotionName: String): TensionCalculation = {
//    (muscleName: String) => {
//      val calcFn = for (infMap <- emoInfMap.get(emotionName);
//                        musInf <- infMap.get(muscleName))
//        yield musInf
//      calcFn.getOrElse(_ => 0f)
//    }
//  }

  def tensionCalcMap(emotionId: Int): Map[String, (Int, Float => Float)] = {
    emoInfMap(Emotions.EmotionNames(emotionId))
  }

//  lazy val muscleToFeatures: Map[String, Set[String]] = {
//    val f2m: Set[(String, Set[String])] = featInfMap.toList.map(e0 => (e0._1, e0._2.keySet)).toSet
//    val s: Set[(String, String)] = f2m.flatMap[(String, String), Set[(String, String)]](e1 => e1._2.map(e2 => (e1._1, e2)))
//    s.groupBy(_._2).map(e => (e._1, e._2.map(_._1)))
//  }
//
//  lazy val muscleToFeatureWeights: Map[String, Map[String, Seq[(Int, Float)]]] = {
//    val m2f = muscleToFeatures
//
//    for ((m, ff) <- m2f) yield (m, {
//      (for (f <- ff;
//           infMapForMuscle: Seq[(Int, Float)] = featInfMap(f)(m)
//      ) yield (f, infMapForMuscle)).toMap
//    })
//  }

}

import math._

class SineMapping(x0: Float, x1: Float, y0: Float, y1: Float) extends (Float => Float) {

  override def apply(x: Float): Float = {
    if (x < x0) {
      y0
    } else if (x >= x1) {
      y1
    } else {
      val subExpr = Pi * ((x - x0) / (x1 - x0) + 1.5d)
      val res = (0.5d * sin(subExpr) + 0.5d) * (y1 - y0) + y0
      res.toFloat
    }
  }

}

object SineMapping {

  def apply(mapNode: Node): SineMapping = {
    new SineMapping(
      (mapNode \ "values" \ "@x0").text.toFloat,
      (mapNode \ "values" \ "@x1").text.toFloat,
      (mapNode \ "values" \ "@y0").text.toFloat,
      (mapNode \ "values" \ "@y1").text.toFloat)
  }

}

class GaussMapping(mean: Float, variance: Float, value: Float) extends (Float => Float) {

  override def apply(x: Float): Float = {
    val subExpr = -(x - mean) * (x - mean) / (2 * variance)
    val res = value * (1 / sqrt(2 * Pi * variance)) * exp(subExpr)
    res.toFloat
  }

}

object GaussMapping {

  def apply(mapNode: Node): GaussMapping = {
    new GaussMapping(
      (mapNode \ "values" \ "@mean").text.toFloat,
      (mapNode \ "values" \ "@variance").text.toFloat,
      (mapNode \ "values" \ "@value").text.toFloat)
  }

}

class PolyMapping(coefs: Map[Int, Float]) extends (Float => Float) {

  override def apply(x: Float): Float = {
    val res = coefs.foldLeft(0d)((res, e) => {
      val (exp, coef) = e
      res + coef * pow(x, exp)
    }).toFloat
    res
  }

}

object PolyMapping {

  def apply(mapNode: Node): PolyMapping = {
    val coefs: Map[Int, Float] =
      (for (paramNode <- mapNode \ "param") yield ((paramNode \ "@exponent").text.toInt, (paramNode \ "@value").text.toFloat)).toMap
    new PolyMapping(coefs)
  }

}
