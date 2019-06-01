package com.calc.domain.companycalculation

import java.util.logging.Logger

import com.calc.domain.companycalculation.criticalconditions.RatioConditionStates.ConditionState
import com.calc.domain.companycalculation.entities.Company
import com.calc.domain.companycalculation.criticalconditions.ConditionValidator._

object Calculate {
  val log = Logger.getLogger("Root")//TODO make proper logger
  def main(args: Array[String]): Unit = {
//TODO replace with web
    val pe = readRatio("Pe")
    val de = readRatio("Debt to Equity")
    val pbv = readRatio("P/BV")
    val pepbv = pe * pbv
    val c = readRatio("Current")

    val company = Company(pe,de, pbv, pepbv, c)
    val (peRes, deRes, pbvRes, pepbvRes, currRes) = validateCompany(company)

//TODO replace with 1. presantation layer and 2.saving to db
    presentResults(("Pe", peRes),
                   ("Debt to Equity",deRes),
                   ("P/BV", pbvRes),
                   ("Pe to P/BV", pepbvRes),
                   ("Current", currRes))
  }
  def readRatio(name: String): Double = {
    log.info(s"Read ${name} Ratio")
    scala.io.StdIn.readDouble()
  }

  def validateCompany(c: Company): (ConditionState, ConditionState , ConditionState, ConditionState, ConditionState) = {
    (
      peRatioValidation(c.pe),
      deRatioValidation(c.de),
      pbvRatioValidation(c.pbv),
      pepbvConsolidatedRatioValidation(c.pepbv),
      currentRatioValidation(c.c)
    )
  }

  def presentResults(res: (String, ConditionState)*): Unit = {
    res.foreach(nToRes => log.info(s"${nToRes._1} Ratio validation returned result: ${nToRes._2}"))
  }
}
