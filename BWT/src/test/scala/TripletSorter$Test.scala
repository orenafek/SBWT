import org.scalatest.FunSuite

/**
  * Created by sorimar on 2/22/2017.
  */
class TripletSorter$Test extends FunSuite {

  val word = "fghyabcdeifa".toList
  test("testMkTriplets"){
    println(TripletSorter.mkTriplets(word))

  }

  test("testRadixSort"){
    println(TripletSorter.radixSort(TripletSorter.mkTriplets(word)))
  }

}
