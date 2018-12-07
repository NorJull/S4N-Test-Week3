import com.vamosaprogramar.service.PartyService
import org.scalatest.FunSuite

class EitherTest extends FunSuite{

  test("simpleEitherThenLeft"){

    val actual: Either[String, Int] = PartyService.getPartyTypeByDay("Friday")

    assert(Left("Dance Party") === actual)
  }

  test("simpleEitherThenRight"){

    val actual: Either[String, Int] = PartyService.getPartyTypeByDay("Monday")

    assert(Right(0) === actual)
  }

  test("checkingIfLeftThenTrue"){
    val actual: Either[String, Int] = PartyService.getPartyTypeByDay("Saturday")

    assert(actual.isLeft)
  }

  test("checkingIfLeftThenFalse"){
    val actual: Either[String, Int] = PartyService.getPartyTypeByDay("Tuesday")

    assert(!actual.isLeft)
  }
  test("checkingIfRightThenTrue"){
    val actual: Either[String, Int] = PartyService.getPartyTypeByDay("Monday")

    assert(actual.isRight)
  }

  test("checkingIfRightThenFalse"){
    val actual: Either[String, Int] = PartyService.getPartyTypeByDay("Friday")

    assert(!actual.isRight)
  }

  /***********************************************
     Using  pattern matching
    *******************************************+*/

  test("patterMatchingWithEither"){
    PartyService.getPartyTypeByDay("Monday") match {
      case Left(a) => println("Working with the left side!")
      case Right(b) => println("Working with the right side!...")
    }
  }

  test("patternMatchingWithEitherThenLeft"){

    val actual = PartyService.getPartyTypeByDay("Friday") match {
      case Left(a) => a
      case Right(b) => b
    }

    assert("Dance Party" === actual)
  }

  test("patternMatchingWithEitherThenRight"){

    val actual = PartyService.getPartyTypeByDay("Wednesday") match {
      case Left(a) => a
      case Right(b) => b
    }

    assert(0 === actual)
  }


}
