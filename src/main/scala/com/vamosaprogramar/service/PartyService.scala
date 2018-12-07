package com.vamosaprogramar.service

object PartyService {

  def getPartyTypeByDay(day: String): Either[String, Int] = {

    day match {
      case "Friday"   => Left("Dance Party")
      case "Saturday" => Left("Dinner Party")
      case "Sunday"   => Left("Beach Party")
      case _ => Right(0)
    }
  }
}
