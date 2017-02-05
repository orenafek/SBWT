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
    assertResult("ABC".toList :: Nil)(Bijective.factorize("ABC".toList))
    assertResult("OR".toList :: "EN".toList :: Nil)(Bijective.factorize("OREN".toList))
    assertResult("OR".toList :: "E".toList :: "EN".toList :: Nil)(Bijective.factorize("OREEN".toList))
  }

}
