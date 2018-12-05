import org.scalatest.FunSuite

class CubeCalculatorTest extends FunSuite {

  test("CubeCalculator.cube"){
    assert(CubeCalculator.cube(3) === 27)

    assert(CubeCalculator.cube(0)===0)
  }
  test("Prueba1"){
    val s = "hi"
    intercept[IndexOutOfBoundsException] {
      s.charAt(-1)
    }

  }

  test("Prueba2"){
    val s = "hi"
    val thrown = intercept[IndexOutOfBoundsException] {
      s.charAt(-1)
    }
    println(thrown.getMessage)
    assert(thrown.getMessage === "String index out of range: -1")

  }

}
