/**
  * Implementation of the Bijective Burrows Wheeler transform based on algorithm introduced
  * in http://bijective.dogma.net/00yyy.pdf (Gil:Scott:09).
  * Check it out.
  *
  * @author Oren Afek & Ori Marcovitch
  * @since 05/02/17
  */
object GilScottBijectiveTransform extends BurrowsWheelerTransform {

  override def transformSlow(w: Word): Word = takeLast(sort(wordsRotations(factorize(w))))

  def wordsRotations(ωs: List[Word]): List[Word] = {
    ωs.flatMap(ω => rotations(ω))
  }

  def factorize(S: Word): List[Word] = {
    def factorize(S: Word, m: Int, k: Int): List[Word] =
      if (S.isEmpty) Nil
      else if (m >= S.size || S(m) < S(k)) S.slice(0, m - k) :: factorize(S.slice(m - k, S.size), 1, 0)
      else if (S(m) == S(k)) factorize(S, m + 1, k + 1)
      else factorize(S, m + 1, 0)

    factorize(S, 1, 0)
  }

  /**
    * The inverse of Gil Scott Burrows Wheeler transform running in O(n), algorithm according to Gil:Scott:09
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
