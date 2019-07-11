package com.calc.domain.companycalculation.criticalconditions

//bounds for passing company ratios criteria
object CriticalconditionsResults{
  sealed trait ConditionState
  case object CRITICALLY_NOT_PASS extends ConditionState
  case object NOT_PASS extends ConditionState
  case object BARELY_NOT_PASS extends ConditionState
  case object PASS extends ConditionState
  case object PROPERLY_PASS extends ConditionState
  case object VERY_WELL_PASS extends ConditionState
  case object GREAT_PASS extends ConditionState
}