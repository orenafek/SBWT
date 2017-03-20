import org.scalatest.FunSuite

/**
  * @author Oren Afek
  * @since 05/02/17
  */
class LyndonBijective$Test extends FunSuite {

  test("testTransform") {

  }

  test("testInverse") {

  }

  test("testFactorize") {
    assertResult(List("abc" toList))(GilScottBijectiveTransform.factorize("abc" toList))
    assertResult(List("or".toList, "en".toList))(GilScottBijectiveTransform.factorize("oren".toList))
    assertResult(List("gr".toList, "een".toList))(GilScottBijectiveTransform.factorize("green".toList))
    assertResult(List("o".toList, "iuy".toList, "c".toList, "aasffff".toList))(GilScottBijectiveTransform.factorize("oiuycaasffff".toList))
    //    assertResult(List(List('n', 'o', 'w'), List(' ', 'i', 's'), List(' ', 't', 'h', 'e'), List(' ', 't', 'i', 'm', 'e'), List(' ', 'f', 'o', 'r'), List(' ', 't', 'h', 'e'), List(' ', 't', 'r', 'u', 'l', 'y'), List(' ', 'n', 'i', 'c', 'e'), List(' ', 'p', 'e', 'o', 'p', 'l', 'e'), List(' ', 't', 'o'), List(' ', 'c', 'o', 'm', 'e'), List(' ', 't', 'o'), List(' ', 't', 'h', 'e'), List(' ', 'p', 'a', 'r', 't', 'y')))(Bijective.factorize("now is the time for the truly nice people to come to the party".toList))
  }

  test("testRotations") {
    assertResult(List("ABC", "BCA", "CAB").map(_.toList))(GilScottBijectiveTransform.sort(GilScottBijectiveTransform.rotations("ABC".toList)))
  }

  test("testRotation") {
    assertResult("BCA".toList)(GilScottBijectiveTransform.rotation("ABC".toList))
    assertResult("CCAB".toList)(GilScottBijectiveTransform.rotation("BCCA".toList))
    assertResult("CC AB".toList)(GilScottBijectiveTransform.rotation("BCC A".toList))
  }

  test("takeLast") {
    assertResult("CGJ".toList)(GilScottBijectiveTransform.takeLast(List("ABC", "EFG", "HIJ").map(_.toList)))
    assertResult("CG J".toList)(GilScottBijectiveTransform.takeLast(List("ABC", "EFG", "HI ", "HIJ").map(_.toList)))

  }

  private val NowIs: String = "now is the time for the truly nice people to come to the party"
  private val NowIsGSTransformed: String = "yoeyeeosreeeepi mhchlmhp tttnt puio wttcefn  ooati       rrotl"
  private val NowIsBWTransformed: String = "oewyeesroeeeepi mhchmhlp tttnt puio yttcefn  ooati       rrolt"
  private val BWT = new BurrowsWheelerTransform()
  test("bijectiveTransform") {
    assertResult(NowIsGSTransformed)(GilScottBijectiveTransform.transformSlow(NowIs.toList).mkString)
  }

  test("linearTransform") {
    assertResult(NowIsBWTransformed)(BWT.transformLinear(NowIs.toList).mkString)

  }

  test("inverse") {
    assertResult(NowIs)(GilScottBijectiveTransform.inverse(NowIsGSTransformed.toList).mkString)
  }

  test("final") {
    assertResult("This paper presents the notion of Nano-Patterns, offers criteria for evaluating\ntheir quality, and describes how they might be used for code design, effective\ncommunication between programmers, a concrete documentation aides, and in\nconsideration of extension of the base programming language.")(GilScottBijectiveTransform.inverse(GilScottBijectiveTransform.transformSlow("This paper presents the notion of Nano-Patterns, offers criteria for evaluating\ntheir quality, and describes how they might be used for code design, effective\ncommunication between programmers, a concrete documentation aides, and in\nconsideration of extension of the base programming language.".toList)).mkString)
  }

  test("final2") {
    val s: String = "Lorem ipsum dolor sit amet, pri ad probo populo doctus, ei mel brute dolores. Ei labore euismod qui. Ei natum appareat salutandi mea. Meliore dolores ex nam, mea elit urbanitas ut, pro ea aliquip delectus repudiandae. Nec ei exerci graecis. No vis ullum iudico explicari, nominavi torquatos vim no, et eam pertinax moderatius.\n\nVis id quot pertinacia. At omnis reprehendunt nec, ex debet nullam vix. Aliquid sanctus posidonium eum in, illum utinam at nec. Nam audiam volumus id, has ne legimus mediocritatem.\n\nVel et ponderum consulatu. Ad est principes moderatius, ea qui quis cetero conclusionemque, ne has paulo soleat adolescens. Ut nam tale eruditi volutpat, mea ex stet voluptaria, democritum omittantur pro ex. Eos at adhuc appareat ullamcorper.\n\nQuo ne aeque epicuri intellegam, dolore abhorreant sit ex. Est an iracundia reprehendunt. Sit viris legere numquam ad, alii partem ex qui. Sed ea quem esse pertinax, no est posse liber mandamus, eum probo fugit no. Qui ex vitae euismod reprehendunt, quot putant persius eam te. Dignissim dissentias his et, id usu probo zril.\n\nPer illum liber et. Facer vidisse ex vim. No postea suscipiantur duo. Porro tritani nam eu."
    assertResult(s)(BWT.inverse(BWT.transformSlow(s.toList)).mkString)
  }

}
