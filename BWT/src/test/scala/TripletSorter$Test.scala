import TripletSorter._
import org.scalatest.FunSuite

/**
  * Created by sorimar on 2/22/2017.
  */
class TripletSorter$Test extends FunSuite {

  val word = "fghyabcdeifa".toList
  val aba = "aba".toList
  test("testMkTriplets") {
    // println(TripletSorter.mkWord(TripletSorter.mkTriplets(word)))
  }

  test("testRadixSort") {
    //  println(TripletSorter.mkWord(TripletSorter.radixSort(TripletSorter.mkTriplets(word))))
  }

  test("Marcoyada") {
    println(marcoyada(word))

  }
  test("softLessThen3letters"){
    //println(sort("aba".toList))
    val o = new Ordinal()
    println(sort_aux(aba.map(c => Marco(c.toInt, o.next().ord))))
  }
  test("sort") {
    println(sort(word))
  }

}
