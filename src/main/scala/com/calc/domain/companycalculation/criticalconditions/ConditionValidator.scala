package com.calc.domain.companycalculation.criticalconditions

import com.calc.domain.companycalculation.criticalconditions.CriticalconditionsResults._
import com.calc.domain.companycalculation.criticalconditions.CriticalConditionsConstants._

object ConditionValidator {
//  P/E ratio <= 15 (Market Price - price of stock/Earnings - how much company earn per share) (the lower the best)
  def peRatioValidation(pe: Double): ConditionState = {
    validateUpLimitFactor(pe, PE_RATIO)
  }
  //Debt to equity ratio < 0.5
  def deRatioValidation(de: Double): ConditionState = {
    validateUpLimitFactor(de, DEBT_TO_EQUITY)
  }
  //P/BV <= 1.5 (Market Price - price of stock/ Book Value - how much equity company have per share) (the lower the best)
  def pbvRatioValidation(pbv: Double): ConditionState = {
    validateUpLimitFactor(pbv, PBV_RATIO)
  }
  //P/E * P/BV <= 22.5
  def pepbvConsolidatedRatioValidation(pepbv: Double): ConditionState = {
    validateUpLimitFactor(pepbv, PE_PBV_RATIO)
  }
  //Current ratio (how company handle debt in next 12 months) > 1.50 (balance sheet) (below 1.0 is crap)
  def currentRatioValidation(c: Double): ConditionState = {
    validateDownLimitFactor(c, CURRENT_RATIO)
  }


  private def validateUpLimitFactor(fv: Double, idealV: Double) = {
    fv match {
      case x if x > MAX_OVER * idealV => CRITICALLY_NOT_PASS
      case x if x > OVER._1 * idealV && x <= OVER._2 * idealV => NOT_PASS
      case x if x > BARE_OVER._1 * idealV && x <= BARE_OVER._2 * idealV => BARELY_NOT_PASS
      case x if x == EXACT * idealV => PASS
      case x if x < BARE_UNDER._1 * idealV && x >= BARE_UNDER._2 * idealV => PROPERLY_PASS
      case x if x < UNDER._1 * idealV && x >= UNDER._2 * idealV => VERY_WELL_PASS
      case x if x < MAX_UNDER * idealV => GREAT_PASS
    }
  }
  private def validateDownLimitFactor(f: Double, idealV: Double) = {
    f match {
      case x if x > MAX_OVER * idealV => GREAT_PASS
      case x if x > OVER._1 * idealV && x <= OVER._2 * idealV => VERY_WELL_PASS
      case x if x > BARE_OVER._1 * idealV && x <= BARE_OVER._2 * idealV => PROPERLY_PASS
      case x if x == EXACT * idealV => PASS
      case x if x < BARE_UNDER._1 * idealV && x >= BARE_UNDER._2 * idealV => BARELY_NOT_PASS
      case x if x < UNDER._1 * idealV && x >= UNDER._2 * idealV => NOT_PASS
      case x if x < MAX_UNDER * idealV => CRITICALLY_NOT_PASS
    }
  }
}