package com.calc.domain.companycalculation.criticalconditions

object ConditionConstants {
  //Price to earnings
  val PE_RATIO:Double = 15
  //Number of debt to equity
  val DEBT_TO_EQUITY:Double = 0.5
  //how company handle debt (pay back)
  val CURRENT_RATIO:Double = 1.5
  //price to book value
  val PBV_RATIO:Double = 1.5
  //Price to earnings times price to book value
  val PE_PBV_RATIO:Double = PE_RATIO*PBV_RATIO
}
