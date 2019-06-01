package com.calc.domain.companycalculation.criticalconditions

//bounds for passing company ratios criteria
object RatioConditionStates{
  // 150% to ...
  val MAX_OVER:Double = 1.5
  //110 to 150%
  val OVER:(Double,Double) = (1.1,1.5)
  //100 to 110%
  val BARE_OVER:(Double,Double) = (1,1.1)
  //100%
  val EXACT = 1
  //100 to 90%
  val BARE_UNDER:(Double,Double) = (1,0.9)
  //90 to 50%
  val UNDER:(Double,Double) = (0.9,0.5)
  // 50 % to ...
  val MAX_UNDER:Double = 0.5

  sealed trait ConditionState
  case object CRITICALLY_NOT_PASS extends ConditionState
  case object NOT_PASS extends ConditionState
  case object BARELY_NOT_PASS extends ConditionState
  case object PASS extends ConditionState
  case object PROPERLY_PASS extends ConditionState
  case object VERY_WELL_PASS extends ConditionState
  case object GREAT_PASS extends ConditionState
}