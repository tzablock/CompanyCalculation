package com.calc.domain.companycalculation.entities

import com.calc.domain.companycalculation.cashflow.CashflowResults.{CashflowParameterState, Trend}
import com.calc.domain.companycalculation.criticalconditions.CriticalconditionsResults.ConditionState

/*
pe -  P/E ratio <= 15 (Market Price - price of stock/Earnings - how much company earn per share) (the lower the best)
de - Debt to equity ratio < 0.5
pbv - P/BV <= 1.5 (Market Price - price of stock/ Book Value - how much equity company have per share) (the lower the best)
pepbv - P/E * P/BV <= 22.5
c - Current ratio (how company handle debt in next 12 months) > 1.50 (balance sheet) (below 1.0 is crap)
 */
case class CompanyRatios(peRatio: Double, deRatio: Double, pbvRatio:Double, pepbvRatio: Double, cRatio: Double)
case class CompanyCriticalConditionsStates(peRatioState: ConditionState, deRatioState: ConditionState, pbvRatioState:ConditionState, pepbvConsolidatedRatioState:ConditionState, currentRatioState:ConditionState)
/*
av - actual value
avg - avarage value
chyby - what year changed - change year by year in %
t - trend for diagram (growing, falling or mixed)
valueState - if value meet requirement for factor
 */
case class CashflowParameterAnalitics(av: Double, avg: Double, chyby: List[(String, Double)], t: Trend, valueState: CashflowParameterState)
case class CashflowAnalitics(oaAnalitics: CashflowParameterAnalitics, iaAnalitics: CashflowParameterAnalitics, faAnalitics: CashflowParameterAnalitics)
/*
oa - operating activities
ia - investing activities
fa - financing activities
 */
case class CashflowParameters(oa: List[(Int, Double)], ia: List[(Int, Double)], fa: List[(Int, Double)])