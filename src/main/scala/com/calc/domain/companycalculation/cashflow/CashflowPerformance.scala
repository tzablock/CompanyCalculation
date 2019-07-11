package com.calc.domain.companycalculation.cashflow

import com.calc.domain.companycalculation.cashflow.CashflowConstants._
import com.calc.domain.companycalculation.cashflow.CashflowResults._
import com.calc.domain.companycalculation.entities.CashflowParameterAnalitics

import scala.math.BigDecimal.RoundingMode

object CashflowPerformance {
  def checkParameter(paramValToYear: List[(Int, Double)], paramType: Parameter): CashflowParameterAnalitics = {
    val paramVals = paramValToYear.map(_._2)
    val currentValues = paramVals.reverse.head
    val averageValue = paramVals.sum / paramVals.size
    val changeByYears = calculatePercentChangeYearToYear(paramValToYear)
    val trend = calculateTrendOfValuesChange(changeByYears)
    /*
    Operating Activities (Look for a high positive number) grow or stay
    Investing Activities (Look for a negative number) smaller or stay
    Financing Activities (Look for a negative number) smaller or stay
     */
    val state = calculateState(paramType, currentValues, trend)

    CashflowParameterAnalitics(currentValues,
                               averageValue,
                               changeByYears,
                               trend,
                               state)
  }

  private def calculatePercentChangeYearToYear(paramValToYear: List[(Int, Double)]): List[(String, Double)] = {
    paramValToYear.sliding(2).map(
      yv => (s"${yv.map(_._1).head}-${yv.map(_._1).tail.head}",
        yv.map(_._2).reduce((v1, v2) => BigDecimal((v2 - v1) / Math.abs(v1)).setScale(3, RoundingMode.HALF_DOWN).toDouble))
    ).toList
  }

  private def calculateTrendOfValuesChange(changeByYears: List[(String, Double)]): Trend = {
    if (changeByYears.map(_._2).forall(ch => ch > -STABLE_CHANGE_PERCENT && ch < STABLE_CHANGE_PERCENT))
      StableTrend
    else if (changeByYears.map(_._2).forall(_ > ZERO_PERCENT))
      GrowingTrend
    else if (changeByYears.map(_._2).forall(_ < ZERO_PERCENT))
      FallingTrend
    else
      NoTrend
  }

  private def calculateState(paramType: Parameter, currentValues: Double, trend: Trend) = {
    paramType match {
      case OperatingActivities
        if ifHighPositiveNumber(currentValues, trend) => PROPER_VALUE
      case OperatingActivities => NOT_PROPER_VALUE
      case InvestingActivities
        if ifNegativeNumber(currentValues, trend, INVESTING_ACTIVITIES_MAX) => PROPER_VALUE
      case InvestingActivities => NOT_PROPER_VALUE
      case FinancingActivities
        if ifNegativeNumber(currentValues, trend, FINANCING_ACTIVITIES_MAX) => PROPER_VALUE
      case FinancingActivities => NOT_PROPER_VALUE
    }
  }

  private def ifHighPositiveNumber(currentValues: Double, trend: Trend): Boolean = {
    (trend == StableTrend || trend == GrowingTrend) && currentValues >= OPERATIONG_ACTIVITIES_MIN
  }

  private def ifNegativeNumber(currentValues: Double, trend: Trend, minVal: Double): Boolean = {
    (trend == StableTrend || trend == FallingTrend) && currentValues <= minVal
  }
}
// Operating Activities (Look for a high positive number) grow or stay
// Investing Activities (Look for a negative number) smaller or stay
// Financing Activities (Look for a negative number) smaller or stay
sealed trait Parameter
case object OperatingActivities extends Parameter
case object InvestingActivities extends Parameter
case object FinancingActivities extends Parameter

