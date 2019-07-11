package com.calc.domain.companycalculation.criticalconditions

import org.scalatest.FunSuite
import com.calc.domain.companycalculation.criticalconditions.ConditionValidator._
import com.calc.domain.companycalculation.criticalconditions.CriticalConditionsConstants._
import com.calc.domain.companycalculation.criticalconditions.CriticalconditionsResults._

class ConditionValidatorTest extends FunSuite {
  test("Test if proper state is returned for pe ratio values"){
    val A_BIT = 0.05
    val cnp = PE_RATIO*MAX_OVER + A_BIT
    val np = PE_RATIO*OVER._1 + A_BIT
    val bnp = PE_RATIO*BARE_OVER._1 + A_BIT
    val p = PE_RATIO*EXACT
    val pp = PE_RATIO*BARE_UNDER._1 - A_BIT
    val vwp = PE_RATIO*UNDER._1 - A_BIT
    val gp = PE_RATIO*MAX_UNDER - A_BIT

    List((cnp, CRITICALLY_NOT_PASS),
         (np, NOT_PASS),
         (bnp, BARELY_NOT_PASS),
         (p, PASS),
         (pp, PROPERLY_PASS),
         (vwp, VERY_WELL_PASS),
         (gp, GREAT_PASS)).foreach{
                                    case (v,cs) => assertEqualPe(v,cs)
                                  }
  }
  test("Test if proper state is returned for debt to equity ratio values"){
    val A_BIT = 0.05
    val cnp = DEBT_TO_EQUITY*MAX_OVER + A_BIT
    val np = DEBT_TO_EQUITY*OVER._1 + A_BIT
    val bnp = DEBT_TO_EQUITY*BARE_OVER._1 + A_BIT
    val p = DEBT_TO_EQUITY*EXACT
    val pp = DEBT_TO_EQUITY*BARE_UNDER._1 - A_BIT
    val vwp = DEBT_TO_EQUITY*UNDER._1 - A_BIT
    val gp = DEBT_TO_EQUITY*MAX_UNDER - A_BIT

    List((cnp, CRITICALLY_NOT_PASS),
      (np, NOT_PASS),
      (bnp, BARELY_NOT_PASS),
      (p, PASS),
      (pp, PROPERLY_PASS),
      (vwp, VERY_WELL_PASS),
      (gp, GREAT_PASS)).foreach{
      case (v,cs) => assertEqualDe(v,cs)
    }
  }
  test("Test if proper state is returned for P/BV ratio values"){
    val A_BIT = 0.05
    val cnp = PBV_RATIO*MAX_OVER + A_BIT
    val np = PBV_RATIO*OVER._1 + A_BIT
    val bnp = PBV_RATIO*BARE_OVER._1 + A_BIT
    val p = PBV_RATIO*EXACT
    val pp = PBV_RATIO*BARE_UNDER._1 - A_BIT
    val vwp = PBV_RATIO*UNDER._1 - A_BIT
    val gp = PBV_RATIO*MAX_UNDER - A_BIT

    List((cnp, CRITICALLY_NOT_PASS),
      (np, NOT_PASS),
      (bnp, BARELY_NOT_PASS),
      (p, PASS),
      (pp, PROPERLY_PASS),
      (vwp, VERY_WELL_PASS),
      (gp, GREAT_PASS)).foreach{
      case (v,cs) => assertEqualPbv(v,cs)
    }
  }
  test("Test if proper state is returned for pe ratio times P/BV ratio factor values"){
    val A_BIT = 0.05
    val cnp = PE_PBV_RATIO*MAX_OVER + A_BIT
    val np = PE_PBV_RATIO*OVER._1 + A_BIT
    val bnp = PE_PBV_RATIO*BARE_OVER._1 + A_BIT
    val p = PE_PBV_RATIO*EXACT
    val pp = PE_PBV_RATIO*BARE_UNDER._1 - A_BIT
    val vwp = PE_PBV_RATIO*UNDER._1 - A_BIT
    val gp = PE_PBV_RATIO*MAX_UNDER - A_BIT

    List((cnp, CRITICALLY_NOT_PASS),
      (np, NOT_PASS),
      (bnp, BARELY_NOT_PASS),
      (p, PASS),
      (pp, PROPERLY_PASS),
      (vwp, VERY_WELL_PASS),
      (gp, GREAT_PASS)).foreach{
      case (v,cs) => assertEqualPepbv(v,cs)
    }
  }
  test("Test if proper state is returned for current ratio values"){
    val A_BIT = 0.05
    val gp = CURRENT_RATIO*MAX_OVER + A_BIT
    val vwp = CURRENT_RATIO*OVER._1 + A_BIT
    val pp = CURRENT_RATIO*BARE_OVER._1 + A_BIT
    val p = CURRENT_RATIO*EXACT
    val bnp = CURRENT_RATIO*BARE_UNDER._1 - A_BIT
    val np = CURRENT_RATIO*UNDER._1 - A_BIT
    val cnp = CURRENT_RATIO*MAX_UNDER - A_BIT

    List((cnp, CRITICALLY_NOT_PASS),
      (np, NOT_PASS),
      (bnp, BARELY_NOT_PASS),
      (p, PASS),
      (pp, PROPERLY_PASS),
      (vwp, VERY_WELL_PASS),
      (gp, GREAT_PASS)).foreach{
      case (v,cs) => assertEqualCurrent(v,cs)
    }
  }

  private def assertEqualPe(v: Double, cs: ConditionState): Unit = assert(peRatioValidation(v)==cs)
  private def assertEqualDe(v: Double, cs: ConditionState): Unit = assert(deRatioValidation(v)==cs)
  private def assertEqualPbv(v: Double, cs: ConditionState): Unit = assert(pbvRatioValidation(v)==cs)
  private def assertEqualPepbv(v: Double, cs: ConditionState): Unit = assert(pepbvConsolidatedRatioValidation(v)==cs)
  private def assertEqualCurrent(v: Double, cs: ConditionState): Unit = assert(currentRatioValidation(v)==cs)

}
