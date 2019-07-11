package com.calc.domain.companycalculation

import java.time.Year
import java.util.logging.Logger

import com.calc.domain.companycalculation.criticalconditions.CriticalconditionsResults.ConditionState
import com.calc.domain.companycalculation.entities.{CashflowAnalitics, CashflowParameters, CompanyCriticalConditionsStates, CompanyRatios}
import com.calc.domain.companycalculation.criticalconditions.ConditionValidator._
import com.calc.domain.companycalculation.cashflow.CashflowPerformance.checkParameter
import com.calc.domain.companycalculation.cashflow.{FinancingActivities, InvestingActivities, OperatingActivities}
object Calculate {
  val log = Logger.getLogger("Root")

  //TODO make proper logger
//  TODO make report what exactlz was wrong np pa ratio to big or one of change in cashlow not positive
  def main(args: Array[String]): Unit = {
//TODO replace with web
    log.info("Provide critical conditions ratios:")

    val pe = readRatio("Pe")
    val de = readRatio("Debt to Equity")
    val pbv = readRatio("P/BV")
    val pepbv = pe * pbv
    val c = readRatio("Current")
    val cr = CompanyRatios(pe,de, pbv, pepbv, c)

    log.info("Provide cashflow ratios:")
    val oa = readCashFlowParam("Operating Activities")
    val ia = readCashFlowParam("Investing Activities")
    val fa = readCashFlowParam("Financing Activities")
    val cp = CashflowParameters(oa, ia, fa)
    //TODO add Cashlow calc

    val ccstates = validateCompany(cr)
    val ca = validateCashflow(cp)
//    add cashlow calculation

    //TODO replace with 1. presantation layer and 2.saving to db
    presentResults(("Pe", ccstates.peRatioState),
                   ("Debt to Equity",ccstates.deRatioState),
                   ("P/BV", ccstates.pbvRatioState),
                   ("Pe to P/BV", ccstates.pepbvConsolidatedRatioState),
                   ("Current", ccstates.currentRatioState))


  }

  def readRatio(name: String): Double = {
    log.info(s"Read ${name} Ratio")
    scala.io.StdIn.readDouble()
  }

  def readCashFlowParam(name: String): List[(Int, Double)] = {
    val CURRENT_YEAR = Year.now.getValue
    (CURRENT_YEAR-4 to CURRENT_YEAR).map(
      y => {
        log.info(s"Provide ${name} for year ${y}:")
        (y, scala.io.StdIn.readDouble())
      }
    ).toList
  }

  def validateCompany(c: CompanyRatios): CompanyCriticalConditionsStates = {
    CompanyCriticalConditionsStates(
      peRatioValidation(c.peRatio),
      deRatioValidation(c.deRatio),
      pbvRatioValidation(c.pbvRatio),
      pepbvConsolidatedRatioValidation(c.pepbvRatio),
      currentRatioValidation(c.cRatio)
    )
  }

  def validateCashflow(cp: CashflowParameters): CashflowAnalitics = {
    CashflowAnalitics(
      checkParameter(cp.oa, OperatingActivities),
      checkParameter(cp.ia, InvestingActivities),
      checkParameter(cp.fa, FinancingActivities)
    )
  }

  def presentResults(res: (String, ConditionState)*): Unit = {
    res.foreach(nToRes => log.info(s"${nToRes._1} Ratio validation returned result: ${nToRes._2}"))
  }
}
