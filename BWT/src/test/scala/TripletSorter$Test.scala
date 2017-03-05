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


  test("oneTwoHandler") {
    val inp: IndexedIntWord = input
    val (_, src): (_, TripList) = mkGroups(inp)
    println(src)
    val $ = radixSort(src)
    println("$: " + $)
    val o = new Ordinal
    var li = new ListBuffer[Marco]
    $.foreach(x => li += o.next(x))
    li += Marco(∞, -1) += Marco(∞, -1)
    println("li: " + li)
    val sorted = sort_aux(li)
    println("sorted: " + sorted)
    sorted.take(sorted.size - 2).map(x => $(x.i))
  }

  test("sort_aux") {
    val src: IndexedIntWord = input
    val (g0, g1_2) = mkGroups(src)
    println(g1_2)
    val handler: TripList = oneTwoHandler(g1_2)
    println(handler)
    val orderedSuffixes = new OrderedSuffixes(handler)
    println(orderedSuffixes.data.toList)
    //    merge(radixSort(g0, orderedSuffixes), handler)
  }


  def input: IndexedIntWord = {
    val o1 = new Ordinal()
    val src: IndexedIntWord = word.map(c => Marco(c.toInt, o1.next().ord)) += Marco(∞, -1) += Marco(∞, -1)
    src
  }

  test("sort") {
    println(sort("fghyabcdeifa".toList))
  }

}
