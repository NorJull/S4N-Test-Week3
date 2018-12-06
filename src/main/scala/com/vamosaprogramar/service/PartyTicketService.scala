package com.vamosaprogramar.service

import com.vamosaprogramar.entity.{PartyTicket, Person}
import com.vamosaprogramar.exception.UnderAgeException

object PartyTicketService {

  def buyPartyTicket(person: Person) = {
    if(person.age >= 18)
      new PartyTicket("Official seller")
    else
      throw UnderAgeException("The party is only for adults")
  }

}
