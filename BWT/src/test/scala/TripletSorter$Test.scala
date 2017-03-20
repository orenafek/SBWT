import TripletSorter._
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

/**
  * Created by sorimar on 2/22/2017.
  */
class TripletSorter$Test extends FunSuite {

  val aba = "aba".toList.to[ListBuffer]

  test("sortLessThen3letters") {
    val o = new Ordinal()
    println(sort_aux(aba.map(c => OrderedToken(c.toInt, o.next().ord))))
  }

  test("sort") {
    println(sort("fghyfabcdeifa".toList).map(x => x.mkString))
  }

  test("sort simple") {
    println(sort("cab".toList).map(x => x.mkString))
  }

  test("sort simple2") {
    println(sort("dcab".toList).map(x => x.mkString))
  }

  test("sort simple3") {
    println(sort("acab".toList).map(x => x.mkString))
  }
  test("sort simple4") {
    println(sort("acaba".toList).map(x => x.mkString))
  }
  test("sort2") {
    val src = "mississippi".toList
    println(sort(src).map(l => l.mkString + "\n"))
  }

}
