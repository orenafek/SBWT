import Bijective.Word
import TripletSorter.{IndexedTriplet, Triplet}

import scala.runtime.Nothing$

/**
  * @author Oren Afek
  * @since 22/02/17
  */
object TripletSorter {
  type IntList = List[Int]
  type IndexedIntList = List[(Int, Int)]
  type Triplet = (Int, IntList)
  type IndexedTriplet = (Int, Triplet)
  type TripList = List[IndexedTriplet]

  def mkWord(src: TripList): Word = src.flatMap(_._2._2.map(_.toChar))

  def mkGroups(src: IndexedIntList): (TripList, TripList) = {
    val $ = Range(0, src.size - 2)
      .map(i => (src(i)._2, (i, List(src(i)._1, src(i + 1)._1, src(i + 2)._1)))).toList
    def group0Cond(x: IndexedTriplet) = x._2._1 % 3 == 0
    ($.filter(group0Cond), $.filter(!group0Cond(_)))
  }

  def mkTriplets(src: IntList): TripList = mkTriplets_aux(src.map(_.toInt))

  def mkTriplets_aux(src: IntList): TripList = Range(0, src.size - 2)
    .map(i => (-1, (i, List(src(i), src(i + 1), src(i + 2))))).toList

  def sort(src: Word): Word = ???

  def merge(g0: TripList, g1_2: TripList): TripList = {


  }

  def radixSort(g0: TripList, handler: TripList): TripList = ???

  def sort_aux(src: IndexedIntList): TripList = {
    if (src.size == 1)
      ???
//      List(src.head._2,(-1,???))
//    else if (src.isEmpty)
//      Nil
    else {
      val (g0, g1_2) = mkGroups(src)
      val handler: TripList = oneTwoHandler(g1_2)
      merge(radixSort(g0, handler), handler)
    }
  }

  def oneTwoHandler(src: TripList): TripList = {
    val $ = radixSort(src)
    val o = new Ordinal
    sort_aux($.map(o.next)).map(x => $(x._1))
  }


  def sortTriplets(src: TripList) = {

  }

  def radixSort(src: TripList): TripList = {
    def radixSort_aux(src: TripList, i: Int): TripList = {
      if (i == 3) src
      else src.groupBy(_._2._2(i)).map(x => (x._1, radixSort_aux(x._2, i + 1))).toList.sortBy(_._1).flatten(_._2)
    }
    radixSort_aux(src, 0)
  }


}

class Ordinal {
  var i: Int = -1
  var j: Int = -1
  var last: IndexedTriplet = (-1, (-1, List(-1, -1, -1)))

  def next(current: IndexedTriplet): (Int, Int) = {
    if (last != current)
      i += 1
    j += 1
    last = current
    (i, j)
  }
}

