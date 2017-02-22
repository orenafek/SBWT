import Bijective.Word

/**
  * @author Oren Afek
  * @since 22/02/17
  */
object TripletSorter {

  type Triplet = (Int, Word)

  def mkTriplets(src: Word): List[Triplet] = Range(0, src.size - 2)
    .map(i => (i, List(src(i), src(i + 1), src(i + 2)))).toList

  def sort(src: Word): Word = ???

  def sortTriplets(src: List[Triplet]) = {
  }

  def radixSort(src: List[Triplet]): List[Triplet] = {
    def radixSort_aux(src: List[Triplet], i: Int): List[Triplet] = {
      if (i == 3) src
      else src.groupBy(_._2(i)).map(x => (x._1, radixSort_aux(x._2, i + 1))).toList.sortBy(_._1).flatten(_._2)

    }
    radixSort_aux(src, 0)
  }


}
