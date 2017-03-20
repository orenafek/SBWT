
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

  private def lt(s1: String, s2: String): Boolean = (s2 * s1.length) >= (s1 * s2.length)

  def sort(l: List[Word]): List[Word] =
    l.map(_.mkString).sortWith(lt).map(_.toList)

  def takeLast(words: List[Word]): Word = words.map(_.last)

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
  def transformSlow(w: Word): Word = takeLast(sort(rotations(w)))

  /**
    * Burrows Wheeler transform running in O(n), using the Skew algorithm to sort suffixes
    *
    * @param w word to transform
    * @return
    */
  def transformLinear(w: Word): Word = SuffixesSorter.suffixesIndexesSorted(w).map(i => w((w.size + i - 1) % w.size))

  /**
    * The inverse of Burrows Wheeler transform running in O(n), algorithm according to Gil:Scott:09
    *
    * @param η word to inverse
    * @return
    */
  def inverse(η: Word): Word = {

    def Match(η: Word): π = {
      val n = η.size
      val Σ = 256
      val ϑ = new Array[Int](n)
      val counts = new Array[Int](Σ)
      for (i <- 0 until n)
        counts(η(i)) += 1
      val before = new Array[Int](Σ)
      before(0) = counts(0)
      for (c <- 1 until Σ)
        before(c) = before(c - 1) + counts(c - 1)
      val seen = new Array[Int](Σ)
      for (i <- 0 until n) {
        val c = η(i)
        ϑ(i) = before(c) + seen(c)
        seen(c) += 1
      }
      ϑ
    }

    def multiThread(η: Word, ϑ: π) = {
      val n = η.size
      val ⊥ = -17
      val T = new Array[Int](n)
      for (i <- 0 until n)
        T(i) = ϑ(i)
      var α: Word = Nil
      for (j <- 0 until n)
        if (T(j) != ⊥) {
          var k = j
          do {
            α = η(k) :: α
            val t = k
            k = T(k)
            T(t) = ⊥
          } while (T(k) != ⊥)
        }
      α
    }

    val ϑ = Match(η)

    multiThread(η, ϑ)

  }

}

