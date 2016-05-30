//package org.morpheus.emo.v2
//
//import org.morpheus._
//import org.morpheus.Morpheus._
//import org.morpheus.emo.TensionCalculator
//
///**
//  * Created by zslajchrt on 11/11/15.
//  */
//class Emotions[U, Joy, Surprise, Fear, Sadness, Disgust, Anger] {
//  type Emotions1D = (U | Joy | Surprise | Fear | Sadness | Disgust | Anger)
//  type Emotions2D = Emotions1D with Emotions1D
//  type Emotions = TensionCalculator with EmotionBase with Stimulator with StimulatorWrapper with Emotions2D
//}
//
//object Emotions {
//
//  val nothing = 0
//  val joy = 1
//  val surprise = 2
//  val fear = 3
//  val sadness = 4
//  val disgust = 5
//  val anger = 6
//
//  val EmotionNames = Map(joy -> "joy", surprise -> "surprise", fear -> "fear", sadness -> "sadness",
//    disgust -> "disgust", anger -> "anger")
//
//  val EmotionBigNames = Map(joy -> "Joy", surprise -> "Surprise", fear -> "Fear", sadness -> "Sadness",
//    disgust -> "Disgust", anger -> "Anger")
//}
//
//import Emotions._
//
//import scala.reflect.runtime.{universe => ru}
//
//@fragment
//trait EmotionBase {
//
//  private var emo1Lev: Option[(Int, Float)] = None
//  private var emo2Lev: Option[(Int, Float)] = None
//
//  def getEmo1Level = emo1Lev
//  def getEmo2Level = emo2Lev
//
//  def setEmoLevels(emo1: Option[(Int, Float)], emo2: Option[(Int, Float)]) = {
//    emo1Lev = emo1
//    emo2Lev = emo2
//
//    for (m <- mirror(this);
//         mm <- m.owningMutableProxy) {
//      mm.remorph
//      mm.onEmotionChange()
//    }
//  }
//
//  def getEmoLevel(emoId: Int) = {
//    if (emo1Lev.isDefined && emo1Lev.get._1 == emoId)
//      Some(emo1Lev.get._2)
//    else if (emo2Lev.isDefined && emo2Lev.get._1 == emoId)
//      Some(emo2Lev.get._2)
//    else
//      None
//  }
//
//  protected def onEmotionChange() = {}
//
//}
//
//@fragment
//trait Stimulator extends {
//  def stimulateAll() = ()
//}
//
//@fragment @wrapper @nodepscheck
//trait StimulatorWrapper extends EmotionBase {
//  this: Stimulator with MuscleBase =>
//
//  override protected def onEmotionChange(): Unit = {
//    stimulateAll()
//    super.onEmotionChange()
//  }
//
//}
//
//@fragment
//trait Joy {
//  this: TensionCalculator with EmotionBase with M7F with M8F with H_Mey8F with H_Mli9F with M12F with H_Mli10F with M9F with H_TeethUpperF with M15F with M11F with M6F with M10F with M16F with M042F with H_TeethLowerF with H_Meb6F with M5F =>
//
//  def stimulateJoy(): Unit = {
//    val joyLevel = getEmoLevel(joy).get
//    val tCal = tensionCalc(joy)
//    m7.updateTension(tCal(m7)(joyLevel))
//    m8.updateTension(tCal(m8)(joyLevel))
//    h_mey8.updateTension(tCal(h_mey8)(joyLevel))
//    h_mli9.updateTension(tCal(h_mli9)(joyLevel))
//    m12.updateTension(tCal(m12)(joyLevel))
//    h_mli10.updateTension(tCal(h_mli10)(joyLevel))
//    m9.updateTension(tCal(m9)(joyLevel))
//    h_teethupper.updateTension(tCal(h_teethupper)(joyLevel))
//    m15.updateTension(tCal(m15)(joyLevel))
//    m11.updateTension(tCal(m11)(joyLevel))
//    m6.updateTension(tCal(m6)(joyLevel))
//    m10.updateTension(tCal(m10)(joyLevel))
//    m16.updateTension(tCal(m16)(joyLevel))
//    m042.updateTension(tCal(m042)(joyLevel))
//    h_teethlower.updateTension(tCal(h_teethlower)(joyLevel))
//    h_meb6.updateTension(tCal(h_meb6)(joyLevel))
//    m5.updateTension(tCal(m5)(joyLevel))
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait JoyWrapper extends Stimulator {
//  this: Joy =>
//
//  override def stimulateAll(): Unit = {
//    stimulateJoy()
//    super.stimulateAll()
//  }
//}
//
//
//
//@fragment
//trait Surprise {
//  this: TensionCalculator with EmotionBase with M7F with M2F with H_Mey8F with M1F with H_Mey12F with H_TeethUpperF with M0F with M15F with M11F with H_Meb7F with M6F with M10F with M16F with M042F with M3F with H_TeethLowerF with H_Meb6F with M041F with M5F =>
//
//  def stimulateSurprise(): Unit = {
//    val surpriseLevel = getEmoLevel(surprise).get
//    val tCal = tensionCalc(surprise)
//    m7.updateTension(tCal(m7)(surpriseLevel))
//    m2.updateTension(tCal(m2)(surpriseLevel))
//    h_mey8.updateTension(tCal(h_mey8)(surpriseLevel))
//    m1.updateTension(tCal(m1)(surpriseLevel))
//    h_mey12.updateTension(tCal(h_mey12)(surpriseLevel))
//    h_teethupper.updateTension(tCal(h_teethupper)(surpriseLevel))
//    m0.updateTension(tCal(m0)(surpriseLevel))
//    m15.updateTension(tCal(m15)(surpriseLevel))
//    m11.updateTension(tCal(m11)(surpriseLevel))
//    h_meb7.updateTension(tCal(h_meb7)(surpriseLevel))
//    m6.updateTension(tCal(m6)(surpriseLevel))
//    m10.updateTension(tCal(m10)(surpriseLevel))
//    m16.updateTension(tCal(m16)(surpriseLevel))
//    m042.updateTension(tCal(m042)(surpriseLevel))
//    m3.updateTension(tCal(m3)(surpriseLevel))
//    h_teethlower.updateTension(tCal(h_teethlower)(surpriseLevel))
//    h_meb6.updateTension(tCal(h_meb6)(surpriseLevel))
//    m041.updateTension(tCal(m041)(surpriseLevel))
//    m5.updateTension(tCal(m5)(surpriseLevel))
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait SurpriseWrapper extends Stimulator {
//  this: Surprise =>
//
//  override def stimulateAll(): Unit = {
//    stimulateSurprise()
//    super.stimulateAll()
//  }
//}
//
//
//
//@fragment
//trait Fear {
//  this: TensionCalculator with EmotionBase with M7F with M2F with M8F with H_Mey8F with M1F with M12F with H_Mey12F with M14F with H_TeethUpperF with M0F with M15F with M11F with H_Meb7F with M6F with M10F with M16F with M042F with M3F with H_TeethLowerF with H_Meb6F with M041F with M5F =>
//
//  def stimulateFear(): Unit = {
//    val fearLevel = getEmoLevel(fear).get
//    val tCal = tensionCalc(fear)
//    m7.updateTension(tCal(m7)(fearLevel))
//    m2.updateTension(tCal(m2)(fearLevel))
//    m8.updateTension(tCal(m8)(fearLevel))
//    h_mey8.updateTension(tCal(h_mey8)(fearLevel))
//    m1.updateTension(tCal(m1)(fearLevel))
//    m12.updateTension(tCal(m12)(fearLevel))
//    h_mey12.updateTension(tCal(h_mey12)(fearLevel))
//    m14.updateTension(tCal(m14)(fearLevel))
//    h_teethupper.updateTension(tCal(h_teethupper)(fearLevel))
//    m0.updateTension(tCal(m0)(fearLevel))
//    m15.updateTension(tCal(m15)(fearLevel))
//    m11.updateTension(tCal(m11)(fearLevel))
//    h_meb7.updateTension(tCal(h_meb7)(fearLevel))
//    m6.updateTension(tCal(m6)(fearLevel))
//    m10.updateTension(tCal(m10)(fearLevel))
//    m16.updateTension(tCal(m16)(fearLevel))
//    m042.updateTension(tCal(m042)(fearLevel))
//    m3.updateTension(tCal(m3)(fearLevel))
//    h_teethlower.updateTension(tCal(h_teethlower)(fearLevel))
//    h_meb6.updateTension(tCal(h_meb6)(fearLevel))
//    m041.updateTension(tCal(m041)(fearLevel))
//    m5.updateTension(tCal(m5)(fearLevel))
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait FearWrapper extends Stimulator {
//  this: Fear =>
//
//  override def stimulateAll(): Unit = {
//    stimulateFear()
//    super.stimulateAll()
//  }
//}
//
//
//
//@fragment
//trait Sadness {
//  this: TensionCalculator with EmotionBase with M7F with M2F with M8F with H_Mey8F with M1F with M12F with H_Mey12F with M9F with H_TeethUpperF with M0F with M15F with M11F with H_Meb7F with M6F with M10F with M16F with M042F with M3F with H_TeethLowerF with H_Meb6F with M5F =>
//
//  def stimulateSadness(): Unit = {
//    val sadnessLevel = getEmoLevel(sadness).get
//    val tCal = tensionCalc(sadness)
//    m7.updateTension(tCal(m7)(sadnessLevel))
//    m2.updateTension(tCal(m2)(sadnessLevel))
//    m8.updateTension(tCal(m8)(sadnessLevel))
//    h_mey8.updateTension(tCal(h_mey8)(sadnessLevel))
//    m1.updateTension(tCal(m1)(sadnessLevel))
//    m12.updateTension(tCal(m12)(sadnessLevel))
//    h_mey12.updateTension(tCal(h_mey12)(sadnessLevel))
//    m9.updateTension(tCal(m9)(sadnessLevel))
//    h_teethupper.updateTension(tCal(h_teethupper)(sadnessLevel))
//    m0.updateTension(tCal(m0)(sadnessLevel))
//    m15.updateTension(tCal(m15)(sadnessLevel))
//    m11.updateTension(tCal(m11)(sadnessLevel))
//    h_meb7.updateTension(tCal(h_meb7)(sadnessLevel))
//    m6.updateTension(tCal(m6)(sadnessLevel))
//    m10.updateTension(tCal(m10)(sadnessLevel))
//    m16.updateTension(tCal(m16)(sadnessLevel))
//    m042.updateTension(tCal(m042)(sadnessLevel))
//    m3.updateTension(tCal(m3)(sadnessLevel))
//    h_teethlower.updateTension(tCal(h_teethlower)(sadnessLevel))
//    h_meb6.updateTension(tCal(h_meb6)(sadnessLevel))
//    m5.updateTension(tCal(m5)(sadnessLevel))
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait SadnessWrapper extends Stimulator {
//  this: Sadness =>
//
//  override def stimulateAll(): Unit = {
//    stimulateSadness()
//    super.stimulateAll()
//  }
//}
//
//
//
//@fragment
//trait Disgust {
//  this: TensionCalculator with EmotionBase with M7F with M2F with M8F with H_Mli9F with M12F with H_Mli10F with H_TeethUpperF with M0F with M15F with M11F with H_Meb7F with M6F with M10F with M16F with M042F with H_TeethLowerF with H_Meb6F with M5F =>
//
//  def stimulateDisgust(): Unit = {
//    val disgustLevel = getEmoLevel(disgust).get
//    val tCal = tensionCalc(disgust)
//    m7.updateTension(tCal(m7)(disgustLevel))
//    m2.updateTension(tCal(m2)(disgustLevel))
//    m8.updateTension(tCal(m8)(disgustLevel))
//    h_mli9.updateTension(tCal(h_mli9)(disgustLevel))
//    m12.updateTension(tCal(m12)(disgustLevel))
//    h_mli10.updateTension(tCal(h_mli10)(disgustLevel))
//    h_teethupper.updateTension(tCal(h_teethupper)(disgustLevel))
//    m0.updateTension(tCal(m0)(disgustLevel))
//    m15.updateTension(tCal(m15)(disgustLevel))
//    m11.updateTension(tCal(m11)(disgustLevel))
//    h_meb7.updateTension(tCal(h_meb7)(disgustLevel))
//    m6.updateTension(tCal(m6)(disgustLevel))
//    m10.updateTension(tCal(m10)(disgustLevel))
//    m16.updateTension(tCal(m16)(disgustLevel))
//    m042.updateTension(tCal(m042)(disgustLevel))
//    h_teethlower.updateTension(tCal(h_teethlower)(disgustLevel))
//    h_meb6.updateTension(tCal(h_meb6)(disgustLevel))
//    m5.updateTension(tCal(m5)(disgustLevel))
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait DisgustWrapper extends Stimulator {
//  this: Disgust =>
//
//  override def stimulateAll(): Unit = {
//    stimulateDisgust()
//    super.stimulateAll()
//  }
//}
//
//
//
//@fragment
//trait Anger {
//  this: TensionCalculator with EmotionBase with M7F with M2F with H_Mey8F with H_Mli9F with M1F with M12F with H_Mey12F with H_TeethUpperF with M0F with M15F with M11F with H_Meb7F with M6F with M042F with M3F with H_TeethLowerF with H_Meb6F with M041F with M5F =>
//
//  def stimulateAnger(): Unit = {
//    val angerLevel = getEmoLevel(anger).get
//    val tCal = tensionCalc(anger)
//    m7.updateTension(tCal(m7)(angerLevel))
//    m2.updateTension(tCal(m2)(angerLevel))
//    h_mey8.updateTension(tCal(h_mey8)(angerLevel))
//    h_mli9.updateTension(tCal(h_mli9)(angerLevel))
//    m1.updateTension(tCal(m1)(angerLevel))
//    m12.updateTension(tCal(m12)(angerLevel))
//    h_mey12.updateTension(tCal(h_mey12)(angerLevel))
//    h_teethupper.updateTension(tCal(h_teethupper)(angerLevel))
//    m0.updateTension(tCal(m0)(angerLevel))
//    m15.updateTension(tCal(m15)(angerLevel))
//    m11.updateTension(tCal(m11)(angerLevel))
//    h_meb7.updateTension(tCal(h_meb7)(angerLevel))
//    m6.updateTension(tCal(m6)(angerLevel))
//    m042.updateTension(tCal(m042)(angerLevel))
//    m3.updateTension(tCal(m3)(angerLevel))
//    h_teethlower.updateTension(tCal(h_teethlower)(angerLevel))
//    h_meb6.updateTension(tCal(h_meb6)(angerLevel))
//    m041.updateTension(tCal(m041)(angerLevel))
//    m5.updateTension(tCal(m5)(angerLevel))
//  }
//}
//
//@fragment @wrapper @nodepscheck
//trait AngerWrapper extends Stimulator {
//  this: Anger =>
//
//  override def stimulateAll(): Unit = {
//    stimulateAnger()
//    super.stimulateAll()
//  }
//}
