import Bijective.Word
import TripletSorter.{IndexedTriplet, Triplet}

import scala.runtime.Nothing$

/**
  * @author Oren Afek
  * @since 22/02/17
  */
object TripletSorter {
  type IntList = List[Int]
  type IndexedIntList = List[(Int,Int)]
  type Triplet = (Int, IntList)
  type IndexedTriplet = (Int, IntList)
  type TripList = List[IndexedTriplet]

  def mkWord(src: TripList): Word = src.flatMap(_._2._2.map(_.toChar))

  def mkTriplets(src: Word): TripList = mkTriplets_aux(src.map(_.toInt))

  def mkTriplets_aux(src: IntList): TripList = Range(0, src.size - 2)
    .map(i => (-1,(i, List(src(i), src(i + 1), src(i + 2))))).toList

  def sort(src: Word): Word = ???

  def merge(g0: TripList, g1_2: TripList): TripList = ???

  def sort_aux(src: IndexedIntList): TripList = {
    if(src.size < 3)
      ???
    else{
      val g0 = ???
      val g1_2 = ???
      merge(radixSort(g0), oneTwoHandler(g1_2))
    }
  }

  def oneTwoHandler(src: TripList): TripList = {
    val $ = radixSort(src)
    val o = new Ordinal
    sort_aux($.map(x => o.next(x))).map(x => $(x._1))
  }


  def sortTriplets(src: TripList) = {

  }

  def radixSort(src:TripList): TripList = {
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
  var last: IndexedTriplet = (-1,List(-1,-1,-1))

  def next(current: IndexedTriplet): (Int,Int) = {
    if (last != current)
      i += 1
    j += 1
    last = current
    (i,j)
  }
}

