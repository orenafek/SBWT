import Main.Suffix

/**
  * Created by oren on 08/12/16.
  */
object Sandbox {

  class Triplet(chars: List[Char]) {
    assert(chars.length == 3)

    def <(other: Triplet): Boolean = {
      assert(other.chars.length == 3)
      chars.zip(other.chars).forall(p => p._1 < p._2)
    }

    def apply(i: Int): Char = chars(i)

  }

  def suffixes(word: List[Char], triplets: List[Triplet]): List[Suffix] = {

    def suffix(word: List[Char], triplet: Triplet, index: Int): Suffix = word match {
      case Nil => -1
      case c :: cs => if (triplet(index) == c) index else suffix(cs, triplet, index + 1)
    }

    def suffixesInner(triplets: List[Triplet]): List[Suffix] = triplets match {
      case List() => List()
      case t :: ts => suffix(word, t, 0) :: suffixesInner(ts)
    }

    suffixesInner(triplets)
  }

  def triplets(word: List[Char], suffixes: List[Suffix]): List[Triplet] = {

    def triplet(word: List[Char], suffix: Suffix): Triplet =
      new Triplet(List(word(suffix), word(suffix + 1), word(suffix + 2)))

    def allTripletsInner(suffixes: List[Suffix], triplets: List[Triplet]): List[Triplet] = suffixes match {
      case List() => triplets
      case s :: ss => triplet(word, s) :: allTripletsInner(ss, triplets)
    }

    allTripletsInner(suffixes, List())
  }

  def tripletsMergeSort(word: List[Char], g1: List[Suffix], g2: List[Suffix]): List[Suffix] = {
    val g1Triplets = triplets(word, g1)
    val g2Triplets = triplets(word, g2)

    def merge(g1: List[Triplet], g2: List[Triplet]): List[Triplet] = g1 match {
      case List() => g2
      case t1 :: ts1 => g2 match {
        case List() => g1
        case t2 :: ts2 =>
          if (t1 < t2) t1 :: merge(ts1, g2)
          else t2 :: merge(g1, ts2)
      }
    }

    suffixes(word, merge(g1Triplets, g2Triplets))

  }
}
