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

  test("checkingTryWithIsSuccess"){

    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren",18)))

    assert(actual.isSuccess)
  }
  test("checkingTryWithIsFailure"){
    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren",16)))

    assert(actual.isFailure)
  }

  test("simpleTryWithGetOrElse"){
    val try01 = Try(PartyTicketService.buyPartyTicket(Person("Naren",16)))

    val actual = try01 getOrElse PartyTicket("Street seller")

    assert(PartyTicket("Street seller") === actual)
  }

  /*************************************************
    Chaining operations(Mapping and flat mapping)
    **********************************************/

  test("usingMap"){

    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren David", 20))).map(x => x.origin)

    assert(Success("Official seller") === actual)
  }

  test("nestedTryStructureWithMap"){
    val dividendString = "24";
    val divisor = 6;

    val actual = Try(Integer.parseInt(dividendString)).map(x => Try( x / divisor))

    assert(Success(Success(4)) === actual)
  }

  test("nestedTryStructureWithFaltMap"){
    val dividendString = "24";
    val divisor = 6;

    val actual = Try(Integer.parseInt(dividendString)).flatMap(x => Try( x / divisor))

    assert(Success(4) === actual)
  }

  /*************************************************
    Chaining operations(Filter)
    **********************************************/

  test("filteringATryThenSuccess"){

    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren",18))).filter(x => x.origin.equals("Official seller"))

    assert(Success(PartyTicket("Official seller")) === actual)
  }

  test("filteringATryThenFailure"){

    val actual = Try(PartyTicketService.buyPartyTicket(Person("Naren",18))).filter(x => x.origin.equals("Unknown seller"))

    assert(actual.isFailure)
  }

  /*************************************************
    Pattern Matching
    **********************************************/

  test("simpleTryWithPatternMatchingThenSuccess"){

    val try01 = Try(PartyTicketService.buyPartyTicket(Person("Naren",18)))

    val actual: String = try01 match {
      case Success(PartyTicket("Official seller")) => "Do something here!"
      case Failure(UnderAgeException("The party is only for adults")) => "Do another thing here!"
    }

    assert("Do something here!" === actual)
  }


  test("simpleTryWithPatternMatchingThenFailure"){

    val try01 = Try(PartyTicketService.buyPartyTicket(Person("Naren",14)))

    val actual: String = try01 match {
      case Success(PartyTicket("Official seller")) => "Do something here!"
      case Failure(UnderAgeException("The party is only for adults")) => "Do another thing here!"
    }

    assert("Do another thing here!" === actual)
  }

}
