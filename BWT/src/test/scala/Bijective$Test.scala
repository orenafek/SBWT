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
    assertResult("o".toList :: "iuy".toList :: "c".toList :: "a".toList :: "asffff".toList :: Nil)(Bijective.factorize("oiuycaasffff".toList))
  }

  test("testRotations") {
    assertResult(List("ABC", "BCA", "CAB").map(_.toList))(Bijective.sort(Bijective.rotations(List("ABC".toList))))
  }

  test("testRotation") {
    assertResult("BCA".toList)(Bijective.rotation("ABC".toList))
    assertResult("CCAB".toList)(Bijective.rotation("BCCA".toList))
  }

  test("takeLast") {
    assertResult("CGJ".toList)(Bijective.takeLast(List("ABC", "EFG", "HIJ").map(_.toList)))

  }

  test("transform") {
    assertResult("yoeyeeosreeeepi mhchlmhp tttnt puio wttcefn  ooati       rrotl".toList)(Bijective.transform("now is the time for the truly nice people to come to the party".toList))
  }

}
