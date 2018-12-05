import org.scalatest.FunSuite

class OptionTest extends FunSuite{

  test("simpleOption01"){

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x < 2)

    assert(actual === None)
  }

  test("simpleOption02"){

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x > 2)

    assert(actual === Some(4))

  }

  test("optionValueWithGetThenThrowException"){

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x < 2)

    intercept[NoSuchElementException]{
      actual.get
    }

  }

  test("optionValueWithGet"){

    val list = List(2, 4, 6, 8)

    val actual = list.find(x => x > 2)

    assert(4 === actual.get)
  }
  test("optionValueWithGetOrElse"){

    val map = Map(1 -> "Microsoft Windows", 2 -> "GNU/Linux", 3 -> "OSX", 4 -> "Chrome OS")

    val actual = map.get(2).getOrElse("No OS found!")

    assert("GNU/Linux" === actual)
  }

  test("optionValueWithGetOrElseThenReturnDefaultValue"){

    val map = Map(1 -> "Microsoft Windows", 2 -> "GNU/Linux", 3 -> "OSX", 4 -> "Chrome OS")

    val actual = map.get(6).getOrElse("No OS found!")

    assert("No OS found!" === actual)
  }


  test("checkingOptionValueWithIsEmptyThenTrue"){

    val option: Option[Int] = None
    assert(option.isEmpty)

  }

  test("checkingOptionValueWithIsEmptyThenFalse"){

    val option: Option[Int] = Some(10)
    assert(!option.isEmpty)
  }

  test("checkingOptionValueWithIsDefinedThenTrue"){

    val option: Option[Int] = Some(3)
    assert(option.isDefined)

  }

  test("checkingOptionValueWithIsDefinedThenFalse"){

    val option: Option[Int] = None
    assert(!option.isDefined)

  }

}
