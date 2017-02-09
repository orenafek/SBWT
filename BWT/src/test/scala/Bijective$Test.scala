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
    assertResult(List("abc" toList))(Bijective.factorize("abc" toList))
    assertResult(List("or".toList, "en".toList))(Bijective.factorize("oren".toList))
    assertResult(List("gr".toList, "een".toList))(Bijective.factorize("green".toList))
    assertResult(List("o".toList, "iuy".toList, "c".toList, "aasffff".toList))(Bijective.factorize("oiuycaasffff".toList))
    //    assertResult(List(List('n', 'o', 'w'), List(' ', 'i', 's'), List(' ', 't', 'h', 'e'), List(' ', 't', 'i', 'm', 'e'), List(' ', 'f', 'o', 'r'), List(' ', 't', 'h', 'e'), List(' ', 't', 'r', 'u', 'l', 'y'), List(' ', 'n', 'i', 'c', 'e'), List(' ', 'p', 'e', 'o', 'p', 'l', 'e'), List(' ', 't', 'o'), List(' ', 'c', 'o', 'm', 'e'), List(' ', 't', 'o'), List(' ', 't', 'h', 'e'), List(' ', 'p', 'a', 'r', 't', 'y')))(Bijective.factorize("now is the time for the truly nice people to come to the party".toList))
  }

  test("testRotations") {
    assertResult(List("ABC", "BCA", "CAB").map(_.toList))(Bijective.sort(Bijective.rotations(List("ABC".toList))))
  }

  test("testRotation") {
    assertResult("BCA".toList)(Bijective.rotation("ABC".toList))
    assertResult("CCAB".toList)(Bijective.rotation("BCCA".toList))
    assertResult("CC AB".toList)(Bijective.rotation("BCC A".toList))
  }

  test("takeLast") {
    assertResult("CGJ".toList)(Bijective.takeLast(List("ABC", "EFG", "HIJ").map(_.toList)))
    assertResult("CG J".toList)(Bijective.takeLast(List("ABC", "EFG", "HI ", "HIJ").map(_.toList)))

  }

  test("transform") {
    assertResult("yoeyeeosreeeepi mhchlmhp tttnt puio wttcefn  ooati       rrotl")(Bijective.transform("now is the time for the truly nice people to come to the party".toList).mkString)
  }

  test("inverse") {
    assertResult("now is the time for the truly nice people to come to the party")(Bijective.inverse("yoeyeeosreeeepi mhchlmhp tttnt puio wttcefn  ooati       rrotl".toList).mkString)
  }
}
