import Constants.{∅, ∞}
import GilScottBijectiveTransform.Word
import SuffixesSorter.IndexedTriplet

import scala.collection.mutable.ListBuffer

/** The purpose of this decent class is just to kindly sort suffixes of a given string. In O(n).
  * Which is not trivial at all and is implemented according to the Skew algorithm.
  * Check it out - http://www.cs.cmu.edu/~ckingsf/bioinfo-lectures/suffixarrays.pdf
  *
  * @author Oren Afek & Ori Marcovitch
  * @since 22/02/17
  */


case class OrderedToken(tkn: Int, ord: Int) {}

object SuffixesSorter {

  case class Triplet(wordIdx: Int, letters: IntList) {}

  case class IndexedTriplet(i: Int, wordIdx: Int, letters: IntList)

  type IntList = ListBuffer[Int]
  type IndexedIntWord = ListBuffer[OrderedToken]
  type TripList = List[IndexedTriplet]

  def mkWord(src: TripList): Word = src.flatMap(_.letters.map(_.toChar))

  def mkGroups(src: IndexedIntWord): (TripList, TripList) = {
    val $ = Range(0, src.size - 2)
      .map(i => IndexedTriplet(src(i).ord, i, ListBuffer(src(i).tkn, src(i + 1).tkn, src(i + 2).tkn))).toList

    def group0(x: IndexedTriplet) = x.wordIdx % 3 == 0
    def group1(x: IndexedTriplet) = x.wordIdx % 3 == 1
    def group2(x: IndexedTriplet) = x.wordIdx % 3 == 2

    ($.filter(group0), $.filter(group1) ::: $.filter(group2))
  }

  def mkTriplets(src: IndexedIntWord): TripList = mkTriplets_aux(src)

  def mkTriplets_aux(src: IndexedIntWord): TripList = Range(0, src.size - 2)
    .map(i => IndexedTriplet(src(i).ord, i, ListBuffer(src(i).tkn, src(i + 1).tkn, src(i + 2).tkn))).toList

  def suffixesIndexesSorted(src: Word): List[Int] = sort(src).map(_.i)


  def suffixesSorted(src: Word): List[Word] = sort(src).map(t => src.takeRight(src.length - t.i))


  private def sort(src: GilScottBijectiveTransform.Word): TripList = {
    val o = new WordGenerator
    sort_aux(src.map(c => OrderedToken(c.toInt, o.next().ord)).to[ListBuffer] += defaultToken += defaultToken)
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
        val S_i = g1_2.head.letters.head
        val S_j = g0.head.letters.head
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
            val S_i_1 = g1_2.head.letters(1)
            val S_j_1 = g0.head.letters(1)
            if (S_i_1 < S_j_1)
              proceedWithG12
            else if (S_i_1 > S_j_1)
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

  def radixSort(g0: TripList, suffixes: OrderedSuffixes): TripList = {
    val groupedBy: Map[Int, List[IndexedTriplet]] = g0.groupBy(_.letters.head)
    val sortedMap = groupedBy.map(x => (x._1, x._2.sortBy(y => suffixes.get(y.i + 1)))).toList.sortBy(_._1)
    sortedMap.flatten(l => l._2)
  }

  def sort_aux(src: IndexedIntWord): TripList = {
    if (src.size <= 3)
      radixSort(mkTriplets(src += defaultToken += defaultToken))
    else {
      val (g0, g1_2) = mkGroups(src)
      val g1_2Sorted: TripList = g1_2Sort(g1_2)
      val orderedSuffixes = new OrderedSuffixes(g1_2Sorted)
      val x = merge(radixSort(g0, orderedSuffixes), g1_2Sorted)
      x
    }
  }

  def g1_2Sort(src: TripList): TripList = {
    def withoutEnd(sorted: TripList): List[IndexedTriplet] = {
      sorted.take(sorted.size - 2)
    }
    val o = new Ordinal
    val reIndexed: TripList = src.map(x => IndexedTriplet(o.next(), x.wordIdx, x.letters))
    val $ = radixSort(reIndexed)
    val g = new WordGenerator
    val arr: Array[OrderedToken] = new Array($.size)
    $.foreach(x => arr(x.i) = g.next(x))
    if (g.equaled) {
      var li: ListBuffer[OrderedToken] = arr.to[ListBuffer]
      li += defaultToken += defaultToken
      val sorted = sort_aux(li)
      sorted.map(x => src(x.i))
    } else
      $.map(x => src(x.i))
  }

  def defaultToken: OrderedToken = {
    OrderedToken(∞, ∅)
  }

  def radixSort(src: TripList): TripList = {
    def radixSort_aux(src: TripList, i: Int): TripList = {
      if (i == 3) src
      else src.groupBy(_.letters(i)).map(x => (x._1, radixSort_aux(x._2, i + 1))).toList.sortBy(_._1).flatten(_._2)
    }

    radixSort_aux(src, 0)
  }

  class OrderedSuffixes() {
    val recursiveFactor = 2
    var data: Array[Int] = _

    def this(lst: TripList) = {
      this()
      val temp = lst.toArray
      val len = scala.math.ceil(recursiveFactor * lst.size + 2).toInt
      data = new Array(len)
      for (i: Int <- temp.indices) {
        data(temp(i).wordIdx) = i
      }
      data(len - 2) = ∞
      data(len - 1) = ∞
    }

    def get(i: Int): Int = data(i)
  }

}


class Ordinal {
  var idx: Int = -1

  def next(): Int = {
    idx += 1
    idx
  }
}

class WordGenerator {
  var tkn: Int = -1
  var last: ListBuffer[Int] = ListBuffer(∅, ∅, ∅)
  var equaled: Boolean = false
  val ord: Ordinal = new Ordinal

  def next(): OrderedToken = OrderedToken(∅, ord.next())

  def next(current: IndexedTriplet): OrderedToken = {
    if (last != current.letters)
      tkn += 1
    else
      equaled = true
    last = current.letters
    OrderedToken(tkn, current.i)
  }
}
