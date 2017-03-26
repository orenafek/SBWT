
/** Implementation of the Burrows Wheeler transform.
  * There are two implementations here:
  * transformSlow - running in n square.
  * transformLinear - running in O(n).
  *
  * @author Oren Afek & Ori Marcovitch
  * @since 15/03/17
  */

class BurrowsWheelerTransform {
  type Word = List[Char]
  type π = Array[Int] //permutation


  def rotation(w: Word): Word = w match {
    case Nil => Nil
    case c :: cs => cs ::: List(c)
  }

    private def lt(s1: String, s2: String): Boolean = s1 <= s2

  //  private def ltCyclic(s1: String, s2: String): Boolean = (s2 * s1.length) >= (s1 * s2.length)

  def sort(l: List[Word]): List[Word] =
    l.map(_.mkString).sortWith(lt).map(_.toList)

  def takeLasts(words: List[Word]): Word = words.map(_.last)

  def rotations(ω: Word): List[Word] = {
    def rotations(α: Word, i: Int): List[Word] = {
      def concatRotationAndContinue(α: Word, i: Int): List[Word] = α :: rotations(α, i - 1)

      if (i == 0) Nil else concatRotationAndContinue(rotation(α), i)
    }

    rotations(ω, ω.length)
  }

  /**
    * Burrows Wheeler transform, sorting like loons
    *
    * @param w word to transform
    * @return
    */
  def transformSlow(w: Word): Word = takeLasts(sort(rotations(w)))

  /**
    * Burrows Wheeler transform running in O(n), using the Skew algorithm to sort suffixes
    *
    * @param w word to transform
    * @return
    */
  def transformLinear(w: Word): Word = SuffixesSorter.suffixesIndexesSorted(w).map(i => w((w.size + i - 1) % w.size))


}

