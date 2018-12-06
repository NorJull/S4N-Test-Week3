import com.vamosaprogramar.entity.{PartyTicket, Person}
import com.vamosaprogramar.exception.UnderAgeException
import com.vamosaprogramar.service.PartyTicketService
import org.scalatest.FunSuite

import scala.util.{Failure, Success, Try}
class TryTest extends FunSuite {

  test("adultTryingToBuyATicket"){

    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren",18)))

    assert(Success(PartyTicket("Official seller")) === actual)
  }

  test("youngerTryingToBuyATicket"){
    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren",16)))

    assert(Failure(UnderAgeException("The party is only for adults"))=== actual)
  }



}
