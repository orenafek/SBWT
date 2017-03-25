import SuffixesSorter._
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

/**
  * Created by sorimar on 2/22/2017.
  */
class TripletSorter$Test extends FunSuite {

  val aba = "aba".toList.to[ListBuffer]

  test("sortLessThen3letters") {
    val o = new WordGenerator()
    println(sort_aux(aba.map(c => OrderedToken(c.toInt, o.next().ord))))
  }

  test("sort") {
    println(suffixesSorted("fghyfabcdeifa".toList).map(x => x.mkString))
  }

  test("sort simple") {
    println(suffixesSorted("cab".toList).map(x => x.mkString))
  }

  test("sort simple2") {
    println(suffixesSorted("dcab".toList).map(x => x.mkString))
  }

  test("sort simple3") {
    println(suffixesSorted("acab".toList).map(x => x.mkString))
  }
  test("sort simple4") {
    println(suffixesSorted("acaba".toList).map(x => x.mkString))
  }
  test("sort2") {
    val src = "mississippi".toList
    println(suffixesSorted(src).map(l => l.mkString + "\n"))
  }

  test("sort nowIs") {
    val src = "now is the time for the truly nice people to come to the party".toList
    println(suffixesSorted(src).map(l => l.mkString + "\n"))
  }

  test("sort cpp") {
    val src = "cpp".toList
    assertResult(List("cpp", "pp", "p"))(suffixesSorted(src).map(l => l.mkString))
  }

  test("sort ddddda") {
    val src = "ddddda".toList
    assertResult(List("a", "da", "dda", "ddda", "dddda", "ddddda"))(suffixesSorted(src).map(l => l.mkString))
  }

}
