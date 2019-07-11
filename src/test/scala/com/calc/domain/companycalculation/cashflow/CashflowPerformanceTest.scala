package com.calc.domain.companycalculation.cashflow

import org.scalatest.FunSuite
import com.calc.domain.companycalculation.cashflow.CashflowPerformance.checkParameter
import com.calc.domain.companycalculation.cashflow.CashflowResults._

class CashflowPerformanceTest extends FunSuite {
  test("checkParameterShouldReturnAnalysedResultsForOperatingActivitiesWithGrowingTrendAndProperValueState"){
    val yearsToVal = List((2016, 600.0),(2017, 800.0),(2018, 1000.0),(2019, 1200.0))
    val res = checkParameter(yearsToVal,OperatingActivities)
    assert(res.av == 1200.0)
    assert(res.avg == 900.0)
    assert(res.chyby == List(("2016-2017",0.333),("2017-2018",0.25),("2018-2019",0.2)))
    assert(res.t == GrowingTrend)
    assert(res.valueState == PROPER_VALUE)
  }

  test("checkParameterShouldReturnAnalysedResultsForOperatingActivitiesWithStableTrendAndProperValueState"){
    val yearsToVal = List((2016, 1600.0),(2017, 1605.0),(2018, 1606.0),(2019, 1604.0))
    val res = checkParameter(yearsToVal,OperatingActivities)

    assert(res.chyby == List(("2016-2017",0.003), ("2017-2018",0.001), ("2018-2019",-0.001)))
    assert(res.t == StableTrend)
    assert(res.valueState == PROPER_VALUE)
  }

  test("checkParameterShouldReturnAnalysedResultsForOperatingActivitiesWithStableTrendAndNotProperValueState"){
    val yearsToVal = List((2016, -600.0),(2017, -605.0),(2018, -606.0),(2019, -604.0))
    val res = checkParameter(yearsToVal,OperatingActivities)

    assert(res.chyby == List(("2016-2017",-0.008),("2017-2018",-0.002),("2018-2019",0.003)))
    assert(res.t == StableTrend)
    assert(res.valueState == NOT_PROPER_VALUE)
  }

  test("checkParameterShouldReturnAnalysedResultsForOperatingActivitiesWithFallingTrendAndNotProperValueState"){
    val yearsToVal = List((2016, 600.0),(2017, 500.0),(2018, 400.0),(2019, 300.0))
    val res = checkParameter(yearsToVal,OperatingActivities)

    assert(res.chyby == List(("2016-2017",-0.167),("2017-2018",-0.2),("2018-2019",-0.25)))
    assert(res.t == FallingTrend)
    assert(res.valueState == NOT_PROPER_VALUE)
  }

  test("checkParameterShouldReturnAnalysedResultsForInvestingActivitiesWithGrowingTrendAndNotProperValueState"){
    val yearsToVal = List((2016, 600.0),(2017, 800.0),(2018, 1000.0),(2019, 1200.0))
    val res = checkParameter(yearsToVal,InvestingActivities)

    assert(res.chyby == List(("2016-2017",0.333),("2017-2018",0.25),("2018-2019",0.2)))
    assert(res.t == GrowingTrend)
    assert(res.valueState == NOT_PROPER_VALUE)
  }

  test("checkParameterShouldReturnAnalysedResultsForInvestingActivitiesWithStableTrendAndProperValueState"){
    val yearsToVal = List((2016, -600.0),(2017, -605.0),(2018, -606.0),(2019, -604.0))
    val res = checkParameter(yearsToVal,InvestingActivities)

    assert(res.chyby == List(("2016-2017",-0.008),("2017-2018",-0.002),("2018-2019",0.003)))
    assert(res.t == StableTrend)
    assert(res.valueState == PROPER_VALUE)
  }

  test("checkParameterShouldReturnAnalysedResultsForInvestingActivitiesWithFallingTrendAndProperValueState"){
    val yearsToVal = List((2016, 600.0),(2017, 500.0),(2018, -400.0),(2019, -500.0))
    val res = checkParameter(yearsToVal,InvestingActivities)

    assert(res.chyby == List(("2016-2017",-0.167),("2017-2018",-1.8),("2018-2019", -0.25)))
    assert(res.t == FallingTrend)
    assert(res.valueState == PROPER_VALUE)
  }
}
