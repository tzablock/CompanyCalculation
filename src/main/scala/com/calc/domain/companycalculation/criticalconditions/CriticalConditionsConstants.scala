package com.calc.domain.companycalculation.criticalconditions

object CriticalConditionsConstants {
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
}
