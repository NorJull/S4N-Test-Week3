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

  /**************************************************
    Converting Either to Option type
    ************************************************/

  test("convertingRightToOption"){
    val actual = PartyService.getPartyTypeByDay("Monday").toOption  //Right

    assert(Some(0) === actual)

  }
  test("convertingLeftToOption"){
    val actual = PartyService.getPartyTypeByDay("Friday").toOption  //Left

    assert(None === actual)

  }

  /***********************************************
     EITHER IS NOW RIGHT-BIASED
    **********************************************/

  test("simpleEitherLeftWithMapOldVersion"){
    val actual = PartyService.getPartyTypeByDay("Friday").left.map(x => x.toUpperCase())

    assert(Left("DANCE PARTY") === actual)
  }

  test("simpleEitherLeftWithMapNewVersion"){
    val actual = PartyService.getPartyTypeByDay("Friday").map(x => x + 2) //return the Left value unchanged

    assert(Left("Dance Party") === actual)
  }

  test("simpleEitherRightWithMapOldVersion"){
    val actual = PartyService.getPartyTypeByDay("Monday").right.map(x => x + 2)

    assert(Right(2) === actual)
  }
  test("simpleEitherRightWithMapNewVersion"){
    val actual = PartyService.getPartyTypeByDay("Monday").map(x => x + 2) // Right is assumed to be the default case to operate on

    assert(Right(2) === actual)
  }

}
