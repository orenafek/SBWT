import Bijective.Word
import TripletSorter.IndexedTriplet

import scala.collection.mutable.ListBuffer

/**
  * @author Oren Afek
  * @since 22/02/17
  */
object TripletSorter {
  val ∞ = Int.MaxValue

  case class Triplet(wordIdx: Int, ltrs: IntList) {}

  case class IndexedTriplet(i: Int, wordIdx: Int, ltrs: IntList)

  type IntList = ListBuffer[Int]
  type IndexedIntWord = ListBuffer[Marco]
  type TripList = List[IndexedTriplet]

  def mkWord(src: TripList): Word = src.flatMap(_.ltrs.map(_.toChar))

  def mkGroups(src: IndexedIntWord): (TripList, TripList) = {
    val $ = Range(0, src.size - 2)
      .map(i => IndexedTriplet(src(i).ord, i, ListBuffer(src(i).tkn, src(i + 1).tkn, src(i + 2).tkn))).toList
    def group0Cond(x: IndexedTriplet) = x.wordIdx % 3 == 0
    ($.filter(group0Cond), $.filter(!group0Cond(_)))
  }

  def mkTriplets(src: IndexedIntWord): TripList = mkTriplets_aux(src)

  def mkTriplets_aux(src: IndexedIntWord): TripList = Range(0, src.size - 2)
    .map(i => IndexedTriplet(src(i).ord, i, ListBuffer(src(i).tkn, src(i + 1).tkn, src(i + 2).tkn))).toList

  def sort(src: Word): Word = {
    val o = new Ordinal()
    sort_aux(src.map(c => Marco(c.toInt, o.next().ord)).to[ListBuffer]).map(t => src(t.i))
  }


  def merge(g0: TripList, g1_2: TripList): TripList = {

    def merge_inner(g0: TripList, g1_2: TripList, suffixes: OrderedSuffixes): TripList = {
      def proceedWithG0: TripList = g0.head :: merge_inner(g0.tail, g1_2, suffixes)
      def proceedWithG12: TripList = g1_2.head :: merge_inner(g0, g1_2.tail, suffixes)
      if (g0.isEmpty) g1_2
      else if (g1_2.isEmpty) g0
      else {
        val i = g1_2.head.wordIdx
        val j = g0.head.wordIdx
        val S_i = g1_2.head.ltrs.head
        val S_j = g0.head.ltrs.head
        if (S_i < S_j)
          proceedWithG12
        else if (S_i > S_j)
          proceedWithG0
        else {
          if (i % 3 == 1)
          // group 1
            if (suffixes.get(i + 1) < suffixes.get(j + 1))
              proceedWithG12
            else proceedWithG0
          else {
            // group 2
            val S_i_1 = g1_2.head.ltrs(1)
            val S_j_1 = g0.head.ltrs(1)
            if (S_i_1 < S_j_1)
              proceedWithG12
            else if (S_j_1 < S_i_1)
              proceedWithG0
            else if (suffixes.get(i + 2) < suffixes.get(j + 2))
              proceedWithG12
            else proceedWithG0
          }
        }
      }
    }
    merge_inner(g0, g1_2, new OrderedSuffixes(g1_2))
  }

  def radixSort(g0: TripList, suffixes: OrderedSuffixes): TripList =
    g0.groupBy(_.ltrs.head).flatMap(x => x._2.sortBy(y => suffixes.get(y.wordIdx + 1))).toList

  def sort_aux(src: IndexedIntWord): TripList = {
    if (src.size <= 3)
      radixSort(mkTriplets(src += Marco(∞, -1) += Marco(∞, -1)))
    else {
      val (g0, g1_2) = mkGroups(src)
      val handler: TripList = oneTwoHandler(g1_2)
      val orderedSuffixes = new OrderedSuffixes(g1_2)
      merge(radixSort(g0, orderedSuffixes), handler)
    }
  }

  def oneTwoHandler(src: TripList): TripList = {
    val $ = radixSort(src)
    val o = new Ordinal
    var li = ListBuffer(Marco(∞, -1), Marco(∞, -1)) //two dollars for the end
    $.foreach(x => li += o.next(x))
    val sorted = sort_aux(li)
    sorted.take(sorted.size - 2).map(x => $(x.i))
  }

  def radixSort(src: TripList): TripList = {
    def radixSort_aux(src: TripList, i: Int): TripList = {
      if (i == 3) src
      else src.groupBy(_.ltrs(i)).map(x => (x._1, radixSort_aux(x._2, i + 1))).toList.sortBy(_._1).flatten(_._2)
    }
    radixSort_aux(src, 0)
  }

  class OrderedSuffixes() {
    val magicNo = 1.5
    var data: Array[Int] = _

    def this(lst: TripList) = {
      this()
      val temp = lst.toArray
      val len = scala.math.ceil(magicNo * lst.size + 2).toInt
      data = new Array(len)
      for (i <- temp.indices) {
        data(temp(i).wordIdx) = i
      }
      data(len - 2) = ∞
      data(len - 1) = ∞
    }

    def get(i: Int): Int = data(i)
  }

}

case class Marco(tkn: Int, ord: Int) {}

class Ordinal {
  var i: Int = -1
  var j: Int = -1
  var last: IndexedTriplet = IndexedTriplet(-1, -1, ListBuffer(-1, -1, -1))

  def next(): Marco = next(last)

  def next(current: IndexedTriplet): Marco = {
    if (last != current)
      i += 1
    j += 1
    last = current
    Marco(i, j)
  }
}
