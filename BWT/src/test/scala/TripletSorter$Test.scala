import TripletSorter._
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

/**
  * Created by sorimar on 2/22/2017.
  */
class TripletSorter$Test extends FunSuite {

  val word = "fghyabcdeifa".toList.to[ListBuffer]
  val aba = "aba".toList.to[ListBuffer]
  test("testMkTriplets") {
    // println(TripletSorter.mkWord(TripletSorter.mkTriplets(word)))
  }

  test("testRadixSort") {
    //  println(TripletSorter.mkWord(TripletSorter.radixSort(TripletSorter.mkTriplets(word))))
  }

  test("sortLessThen3letters") {
    //println(sort("aba".toList))
    val o = new Ordinal()
    println(sort_aux(aba.map(c => Marco(c.toInt, o.next().ord))))
  }

  test("makeGroups") {
    val o = new Ordinal()
    val (g0, g1_2) = mkGroups(word.map(c => Marco(c.toInt, o.next().ord)) += Marco(∞, -1) += Marco(∞, -1))
    println(g0)
    println(g1_2)
  }

  test("radix") {
    val o = new Ordinal()
    val (_, g1_2) = mkGroups(word.map(c => Marco(c.toInt, o.next().ord)) += Marco(∞, -1) += Marco(∞, -1))
    println(radixSort(g1_2))
  }

  //  test("sort_aux") {
  //    val o1 = new Ordinal()
  //    val (_, src) = mkGroups(word.map(c => Marco(c.toInt, o1.next().ord)) ::: List(Marco(∞, -1), Marco(∞, -1)))
  //    val $ = radixSort(src)
  //    val o2 = new Ordinal()
  //    var li = List(Marco(∞, -1), Marco(∞, -1)) //two dollars for the end
  //    $.foreach(x => li = o2.next(x) :: li)
  //    println($)
  //    println(li)
  //    println(sort_aux(li))
  //  }
  //
  //
    test("sort") {
      println(sort("fghyabcdeifa".toList))
    }

}
