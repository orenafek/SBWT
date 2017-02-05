/**
  * @author Oren Afek & Ori Marcovitch
  * @since 05/02/17
  */
object Bijective {

  type Word = List[Char]

  def factorize(S: Word): List[Word] = {
    def factorize(S: Word, m: Int, k: Int): List[Word] =
      if (S isEmpty) Nil
      else if (m >= S.size || S(m) < S(k)) S.slice(0, m - k) :: factorize(S.slice(m - k, S.size), 1, 0)
      else if (S(m) == S(k)) factorize(S, m + 1, k + 1)
      else factorize(S, m + 1, 0)
    factorize(S, 1, 0)
  }

  def rotation(word: Word): Word = word match {
    case Nil => Nil
    case c :: cs => cs ::: List(c)
  }

  def lt(s1: String, s2: String): Boolean = (s2 * s1.length) >= (s1 * s2.length)

  def sort(l: List[Word]): List[Word] = l.map(_.mkString).sortWith(lt).map(_.toList)

  def takeLast(words: List[Word]): Word = words.map(_.last)

  def rotations(ωs: List[Word]): List[Word] = {

    def concatRotationAndContinue(α: Word, i: Int): List[Word] = {
      α :: rotations(α, i - 1)
    }

    def rotations(α: Word, i: Int): List[Word] = {
      if (i == 0) Nil
      else concatRotationAndContinue(rotation(α), i)
    }

    ωs.flatMap(w => rotations(w, w.length))
  }

  def transform(w: Word): Word = {
    takeLast(sort(rotations(factorize(w))))
  }

  def inverse(η: Word): Word = {

    def Match(η: Word): Word = ???

    def multiThread(η: Word, θ: Word) = ???

    val θ = Match(η)

    multiThread(η, θ)

  }

}
