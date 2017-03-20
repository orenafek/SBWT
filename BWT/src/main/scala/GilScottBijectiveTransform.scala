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

}
