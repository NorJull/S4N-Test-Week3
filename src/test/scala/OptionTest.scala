import org.scalatest.FunSuite

class OptionTest extends FunSuite {

  test("simpleOption01") {

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x < 2)

    assert(actual === None)
  }

  test("simpleOption02") {

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x > 2)

    assert(actual === Some(4))

  }

  test("optionValueWithGetThenThrowException") {

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x < 2)

    intercept[NoSuchElementException] {
      actual.get
    }

  }

  test("optionValueWithGet") {

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x > 2)

    assert(4 === actual.get)
  }
  test("optionValueWithGetOrElse") {

    val map = Map(1 -> "Microsoft Windows", 2 -> "GNU/Linux", 3 -> "OSX", 4 -> "Chrome OS")

    val actual = map.get(2).getOrElse("No OS found!")

    assert("GNU/Linux" === actual)
  }

  test("optionValueWithGetOrElseThenReturnDefaultValue") {

    val map = Map(1 -> "Microsoft Windows", 2 -> "GNU/Linux", 3 -> "OSX", 4 -> "Chrome OS")

    val actual = map.get(6).getOrElse("No OS found!")

    assert("No OS found!" === actual)
  }


  test("checkingOptionValueWithIsEmptyThenTrue") {

    val option: Option[Int] = None
    assert(option.isEmpty)

  }

  test("checkingOptionValueWithIsEmptyThenFalse") {

    val option: Option[Int] = Some(10)
    assert(!option.isEmpty)
  }

  test("checkingOptionValueWithIsDefinedThenTrue") {

    val option: Option[Int] = Some(3)
    assert(option.isDefined)

  }

  test("checkingOptionValueWithIsDefinedThenFalse") {

    val option: Option[Int] = None
    assert(!option.isDefined)

  }

  /** ***************************************************
    * Using Laptop class to test Option
    * ***************************************************/

  test("optionWithPatternMatchingThenNone") {

    val laptop = Laptop(1, "LaptopXL", "Quad core Intel APOLLO", None)

    val originPlace = laptop.originPlace match {
      case Some(originPlace) => originPlace
      case None => "Without origin place"
    }

    assert("Without origin place" === originPlace)

  }

  test("optionWithPatternMatchingThenSome") {
    val laptop = Laptop(1, "LaptopXL", "Quad core Intel APOLLO", Some("Colombia"))

    val originPlace = laptop.originPlace match {
      case Some(originPlace) => originPlace
      case None => "Without origin place"
    }
    assert("Colombia" === originPlace)
  }


  //Mapping with Map and FlatMap


  test("mappingAnOptionWithMap") {
    val name = LaptopRepository.findById(1).map(x => x.name.toUpperCase())

    assert(Some("LAPTOPXL") === name)
  }

  test("mappingAnOptionOfOptionWithMap"){
    val originPlace = LaptopRepository.findById(1).map(x => x.originPlace)
    assert(Some(Some("Colombia")) === originPlace)
  }

  test("mappingOptionOfOptionWithFlatMapThenSome"){
    val originPlace = LaptopRepository.findById(1).flatMap(x => x.originPlace)

    assert(Some("Colombia") === originPlace)
  }

  test("mappingOptionOfOptionWithFlatMapThenNone"){
    val originPlace = LaptopRepository.findById(3).flatMap(x => x.originPlace)
    assert(None === originPlace)
  }

  test("mappingListOfOptionWithFlatMap"){
    val names: List[Option[String]] = List(Some("Johanna"), None, Some("Daniel"))

    val firstLetters = names.flatMap(x => x.map( y => y.charAt(0)))

    assert(List('J', 'D') === firstLetters)
  }


  //Filtering and option with filter

  test("filteringOptionThenSome"){

    val actual = LaptopRepository.findById(1).filter(x => x.cpu.length > 3)

    assert(Some(Laptop(1, "LaptopXL", "Quad core Intel APOLLO", Some("Colombia"))) === actual)
  }

  test("filteringOptionThenNone"){

    val actual = LaptopRepository.findById(1).filter(x => x.cpu.length > 100)

    assert(None === actual)
  }

  //Chaining options

  test("chainingOptions"){
    val option1 = LaptopRepository.findById(6)
    val option2 = LaptopRepository.findById(1)

    val actual = option1 orElse option2

    assert(Some(Laptop(1, "LaptopXL", "Quad core Intel APOLLO", Some("Colombia"))) === actual)
  }



}
