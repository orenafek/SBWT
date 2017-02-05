/**
  * @author Oren Afek & Ori Marcovitch
  * @since 05/02/17
  */
object Bijective {

  type Word = List[Char]

  def factorize(w: Word): List[Word] = {

    def nextFactor(w: Word): (Word, Word) = {

      def unpack(c: Char, tuple: (Word, Word)): (Word, Word) = (tuple._1, c :: tuple._2)

      def next_factor_aux(w: Word, k: Char): (Word, Word) = w match {
        case Nil => (Nil, Nil)
        case c :: cs => if (k < c) unpack(c, next_factor_aux(cs, k)) else (c :: cs, Nil)
      }

      w match {
        case Nil => (Nil, Nil)
        case c :: cs => unpack(c, next_factor_aux(cs, c))
      }
    }

    def factorize_aux(tuple: (Word, Word)) = tuple._2 :: factorize(tuple._1)

    w match {
      case Nil => Nil
      case _ => factorize_aux(nextFactor(w))
    }
  }

  def transform(w: Word): Word = {

    def rotations(factors: List[Word]): List[Word] = {

      def wordRotations(word: Word): List[Word] = {
        val r = rotation(word)
        if (r == word) Nil else r :: wordRotations(r)
      }

      def rotation(word: Word): Word = word match {
        case Nil => Nil
        case c :: cs => cs ::: List(c)
      }

      factors.flatMap(wordRotations)

    }

    def takeLast(factors: List[Word]): Word = ???

    takeLast(rotations(factorize(w)))
  }

  def inverse(η: Word): Word = {

    def bijectiveMatch(η: Word): Word = ???

    def multiThread(η: Word, θ: Word) = ???

    val θ = bijectiveMatch(η)

    multiThread(η, θ)

  }

}
