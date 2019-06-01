package com.calc.domain.companycalculation.entities

/*
pe -  P/E ratio <= 15 (Market Price - price of stock/Earnings - how much company earn per share) (the lower the best)
de - Debt to equity ratio < 0.5
pbv - P/BV <= 1.5 (Market Price - price of stock/ Book Value - how much equity company have per share) (the lower the best)
pepbv - P/E * P/BV <= 22.5
c - Current ratio (how company handle debt in next 12 months) > 1.50 (balance sheet) (below 1.0 is crap)
 */
case class Company(pe: Double, de: Double, pbv:Double, pepbv: Double, c: Double)
