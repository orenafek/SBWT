import Bijective.Word
import TripletSorter.Triplet

/**
  * @author Oren Afek
  * @since 22/02/17
  */
object TripletSorter {
  type IntWord = List[Int]
  type permutation = List[Int]
  type Triplet = (Int, IntWord)
  type TripList = List[Triplet]

  def mkWord(src: TripList): Word = src.flatMap(_._2.map(_.toChar))

  def mkTriplets(src: Word): TripList = mkTriplets_aux(src.map(_.toInt))

  def mkTriplets_aux(src: IntWord): TripList = Range(0, src.size - 2)
    .map(i => (i, List(src(i), src(i + 1), src(i + 2)))).toList

  def sort(src: Word): Word = ???

  def sort_aux(src: IntWord): permutation = ???

  def oneTwoHandler(src: TripList): permutation = {
    val $ = radixSort(src)
    val o = new Ordinal
    sort_aux($.map(x => o.next(x))).map()
  }


  def sortTriplets(src: TripList) = {

  }

  def radixSort(src: TripList): TripList = {
    def radixSort_aux(src: TripList, i: Int): TripList = {
      if (i == 3) src
      else src.groupBy(_._2(i)).map(x => (x._1, radixSort_aux(x._2, i + 1))).toList.sortBy(_._1).flatten(_._2)
    }
    radixSort_aux(src, 0)
  }


}

class Ordinal {
  var i: Int = -1
  var last: Triplet = (-1,List(-1,-1,-1))

  def next(current: Triplet): (Int,Triplet) = {
    if (last != current)
      i += 1
    last = current
    (i,current)
  }
}

