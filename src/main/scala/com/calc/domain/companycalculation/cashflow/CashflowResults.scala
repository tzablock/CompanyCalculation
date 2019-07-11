package com.calc.domain.companycalculation.cashflow

object CashflowResults {
  sealed trait CashflowParameterState
  case object PROPER_VALUE extends CashflowParameterState
  case object NOT_PROPER_VALUE extends CashflowParameterState
  /*
  What is trend of cashflow values
  1. It is stable
  2. They are growing
  3. They are falling
  4. There is no trend (sometimes falling sometimes growing)
  from year to year
   */
  sealed trait Trend
  case object GrowingTrend extends Trend
  case object FallingTrend extends Trend
  case object NoTrend extends Trend
  case object StableTrend extends Trend
}
