import org.scalatest.FunSuite

/**
  * @author Oren Afek
  * @since 05/02/17
  */
class Bijective$Test extends FunSuite {

  test("testTransform") {

  }

  test("testInverse") {

  }

  test("testFactorize") {
    assertResult("OR".toList :: "EN".toList :: Nil)(Bijective.factorize("OREN".toList))
  }

}
